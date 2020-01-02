
package acme.features.administrator.auditorrequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorrequest.Auditorrequest;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorAuditorrequestUpdateService implements AbstractUpdateService<Administrator, Auditorrequest> {

	// Internal state ---------------------------------------------------------------------------------------------------

	@Autowired
	private AdministratorAuditorrequestRepository repository;

	// AbstractUpdateService<Administrator, AuditorRequest> -------------------------------------------------------------


	@Override
	public boolean authorise(final Request<Auditorrequest> request) {
		assert request != null;

		boolean res;
		Integer id;

		Auditorrequest result;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAuditorRequestById(id);

		res = result.getStatus().equals("pending");

		return res;
	}

	@Override
	public void bind(final Request<Auditorrequest> request, final Auditorrequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Auditorrequest> request, final Auditorrequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "username", "description", "status", "firm", "respStatement", "moment");

	}

	@Override
	public Auditorrequest findOne(final Request<Auditorrequest> request) {
		assert request != null;

		Integer id;
		Auditorrequest result;

		id = request.getModel().getInteger("id");

		result = this.repository.findOneAuditorRequestById(id);

		return result;

	}

	@Override
	public void validate(final Request<Auditorrequest> request, final Auditorrequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("status")) {
			boolean ErrorPattern = entity.getStatus().matches("^(pending)|(accepted)|(rejected)$");
			errors.state(request, ErrorPattern, "status", "administrator.auditorrequest.error.pattern-status");
		}
	}

	@Override
	public void update(final Request<Auditorrequest> request, final Auditorrequest entity) {
		assert request != null;
		assert entity != null;

		if (entity.getStatus().equals("accepted")) {

			Auditor auditor;
			Authenticated user;

			user = entity.getUser();

			auditor = new Auditor();
			auditor.setFirm(entity.getFirm());
			auditor.setRespStatement(entity.getRespStatement());

			auditor.setUserAccount(user.getUserAccount());

			this.repository.save(auditor);

		}

		this.repository.save(entity);

	}

}
