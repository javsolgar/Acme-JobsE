
package acme.features.authenticated.auditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedAuditorUpdateService implements AbstractUpdateService<Authenticated, Auditor> {

	// Internal state ------------------------------------------------------------------------------------

	@Autowired
	private AuthenticatedAuditorRepository repository;


	// AbstractUpdateService<Authenticated, Auditor> interface -------------------------------------------

	@Override
	public boolean authorise(final Request<Auditor> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Auditor> request, final Auditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Auditor> request, final Auditor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "respStatement");

	}

	@Override
	public Auditor findOne(final Request<Auditor> request) {
		assert request != null;

		Principal principal;
		Integer id;
		Auditor result;

		principal = request.getPrincipal();
		id = principal.getAccountId();
		result = this.repository.findOneAuditorByUserAccountId(id);
		return result;
	}

	@Override
	public void validate(final Request<Auditor> request, final Auditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<Auditor> request, final Auditor entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
