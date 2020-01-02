
package acme.features.employer.auditrecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditrecord.Auditrecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class EmployerAuditrecordListMineService implements AbstractListService<Employer, Auditrecord> {

	@Autowired
	private EmployerAuditrecordRepository repository;


	@Override
	public boolean authorise(final Request<Auditrecord> request) {
		assert request != null;

		boolean res;
		Integer id;
		Job result;
		Principal principal;
		int principalId;

		principal = request.getPrincipal();
		principalId = principal.getActiveRoleId();

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		res = result.isFinalMode() && result.getEmployer().getId() == principalId;

		return res;
	}

	@Override
	public void unbind(final Request<Auditrecord> request, final Auditrecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "isFinalMode", "moment", "body", "job", "auditorUser");

	}

	@Override
	public Collection<Auditrecord> findMany(final Request<Auditrecord> request) {
		assert request != null;
		Collection<Auditrecord> result;

		result = this.repository.findManyByJobId(request.getModel().getInteger("id"));
		return result;
	}

}
