
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptor.Descriptor;
import acme.entities.duties.Duty;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class EmployerDutyListService implements AbstractListService<Employer, Duty> {

	@Autowired
	EmployerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		Descriptor descriptor;
		Principal principal;
		int idPrincipal, id;
		boolean res;

		principal = request.getPrincipal();
		idPrincipal = principal.getAccountId();
		id = request.getModel().getInteger("idDescriptor");
		descriptor = this.repository.findOneById(id);

		res = idPrincipal == descriptor.getJob().getEmployer().getUserAccount().getId();

		return res;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		request.unbind(entity, model, "title", "description", "percentage");

	}

	@Override
	public Collection<Duty> findMany(final Request<Duty> request) {
		assert request != null;
		Collection<Duty> result;

		result = this.repository.findManyByDescriptorId(request.getModel().getInteger("idDescriptor"));
		return result;
	}

}
