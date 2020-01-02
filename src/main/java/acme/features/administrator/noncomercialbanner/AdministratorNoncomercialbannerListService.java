
package acme.features.administrator.noncomercialbanner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.noncomercialbanner.Noncomercialbanner;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorNoncomercialbannerListService implements AbstractListService<Administrator, Noncomercialbanner> {

	@Autowired
	private AdministratorNoncomercialbannerRepository repository;


	@Override
	public boolean authorise(final Request<Noncomercialbanner> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Noncomercialbanner> request, final Noncomercialbanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "urlPicture", "slogan", "urlTarget", "jingle");
	}

	@Override
	public Collection<Noncomercialbanner> findMany(final Request<Noncomercialbanner> request) {
		assert request != null;

		Collection<Noncomercialbanner> result;
		result = this.repository.findManyAll();
		return result;
	}

}
