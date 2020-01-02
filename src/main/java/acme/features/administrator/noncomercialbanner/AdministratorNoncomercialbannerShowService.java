
package acme.features.administrator.noncomercialbanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.noncomercialbanner.Noncomercialbanner;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorNoncomercialbannerShowService implements AbstractShowService<Administrator, Noncomercialbanner> {

	@Autowired
	private AdministratorNoncomercialbannerRepository repository;


	@Override
	public boolean authorise(final Request<Noncomercialbanner> request) {
		assert request != null;

		Integer id;
		Noncomercialbanner result;
		id = request.getModel().getInteger("id");

		result = this.repository.findOneById(id);

		return result.isFinalMode();
	}

	@Override
	public void unbind(final Request<Noncomercialbanner> request, final Noncomercialbanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "urlPicture", "slogan", "urlTarget", "jingle");
	}

	@Override
	public Noncomercialbanner findOne(final Request<Noncomercialbanner> request) {
		assert request != null;

		Noncomercialbanner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
