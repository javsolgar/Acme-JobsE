
package acme.features.administrator.noncomercialbanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.noncomercialbanner.Noncomercialbanner;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorNoncomercialbannerCreateService implements AbstractCreateService<Administrator, Noncomercialbanner> {

	@Autowired
	private AdministratorNoncomercialbannerRepository repository;


	@Override
	public boolean authorise(final Request<Noncomercialbanner> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Noncomercialbanner> request, final Noncomercialbanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Noncomercialbanner> request, final Noncomercialbanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "urlPicture", "slogan", "urlTarget");
	}

	@Override
	public Noncomercialbanner instantiate(final Request<Noncomercialbanner> request) {
		Administrator administrator;
		Principal principal;
		Noncomercialbanner result;
		int principalId;

		principal = request.getPrincipal();
		principalId = principal.getAccountId();

		administrator = this.repository.findAdministratorByUserAccountId(principalId);

		result = new Noncomercialbanner();
		result.setFinalMode(false);
		result.setAdministrator(administrator);
		return result;
	}

	@Override
	public void validate(final Request<Noncomercialbanner> request, final Noncomercialbanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Noncomercialbanner> request, final Noncomercialbanner entity) {
		assert request != null;
		assert entity != null;

		entity.setFinalMode(true);
		this.repository.save(entity);
	}

}
