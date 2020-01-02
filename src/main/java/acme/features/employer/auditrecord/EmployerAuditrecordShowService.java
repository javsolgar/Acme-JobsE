
package acme.features.employer.auditrecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditrecord.Auditrecord;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerAuditrecordShowService implements AbstractShowService<Employer, Auditrecord> {

	@Autowired
	private EmployerAuditrecordRepository repository;


	@Override
	public boolean authorise(final Request<Auditrecord> request) {
		assert request != null;

		boolean res;
		Integer id;
		Auditrecord result;
		Principal principal;
		int principalId;

		principal = request.getPrincipal();
		principalId = principal.getActiveRoleId();

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAuditrecordById(id);

		res = result.getIsFinalMode() && principalId == result.getJob().getEmployer().getId();

		return res;
	}

	@Override
	public void unbind(final Request<Auditrecord> request, final Auditrecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "isFinalMode", "moment", "body", "jobTitle", "auditorUser");
	}

	@Override
	public Auditrecord findOne(final Request<Auditrecord> request) {
		assert request != null;

		Auditrecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAuditrecordById(id);
		return result;
	}

}
