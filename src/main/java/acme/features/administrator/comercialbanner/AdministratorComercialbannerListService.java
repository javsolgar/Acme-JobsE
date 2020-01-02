
package acme.features.administrator.comercialbanner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.comercialbanner.Comercialbanner;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorComercialbannerListService implements AbstractListService<Administrator, Comercialbanner> {

	@Autowired
	private AdministratorComercialbannerRepository repository;


	@Override
	public boolean authorise(final Request<Comercialbanner> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Comercialbanner> request, final Comercialbanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "urlPicture", "slogan", "urlTarget", "creditNumber");
	}

	@Override
	public Collection<Comercialbanner> findMany(final Request<Comercialbanner> request) {
		assert request != null;

		Collection<Comercialbanner> result;
		result = this.repository.findManyAll();
		return result;
	}

}
