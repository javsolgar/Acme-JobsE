
package acme.features.administrator.comercialbanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.comercialbanner.Comercialbanner;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorComercialbannerUpdateService implements AbstractUpdateService<Administrator, Comercialbanner> {

	@Autowired
	private AdministratorComercialbannerRepository repository;


	@Override
	public boolean authorise(final Request<Comercialbanner> request) {
		assert request != null;

		boolean res;
		Integer id;
		Comercialbanner result;
		Principal principal;
		Integer idPrincipal;
		Administrator administrator;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		principal = request.getPrincipal();
		idPrincipal = principal.getAccountId();

		administrator = this.repository.findAdministratorByUserAccountId(idPrincipal);

		res = result.isFinalMode() || administrator.getId() == result.getAdministrator().getId();

		return res;
	}

	@Override
	public void bind(final Request<Comercialbanner> request, final Comercialbanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Comercialbanner> request, final Comercialbanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "urlPicture", "slogan", "urlTarget", "creditCard");
	}

	@Override
	public Comercialbanner findOne(final Request<Comercialbanner> request) {
		assert request != null;
		Comercialbanner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<Comercialbanner> request, final Comercialbanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<Comercialbanner> request, final Comercialbanner entity) {
		this.repository.save(entity);

	}

}
