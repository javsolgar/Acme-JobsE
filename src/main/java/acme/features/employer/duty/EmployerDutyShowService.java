
package acme.features.employer.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerDutyShowService implements AbstractShowService<Employer, Duty> {

	@Autowired
	private EmployerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		Duty Duty;
		Principal principal;
		int idPrincipal, id;
		boolean res;

		principal = request.getPrincipal();
		idPrincipal = principal.getAccountId();
		id = request.getModel().getInteger("id");
		Duty = this.repository.findDutyById(id);

		res = idPrincipal == Duty.getDescriptor().getJob().getEmployer().getUserAccount().getId();

		return res;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "percentage", "jobTitle", "jobId", "finalMode");

	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		Duty result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findDutyById(id);
		return result;
	}

}
