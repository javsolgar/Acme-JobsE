
package acme.features.authenticated.investorsrecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investorsrecords.Investorsrecords;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedInvestorsrecordsShowService implements AbstractShowService<Authenticated, Investorsrecords> {

	@Autowired
	private AuthenticatedInvestorsrecordsRepository repository;


	@Override
	public boolean authorise(final Request<Investorsrecords> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Investorsrecords> request, final Investorsrecords entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "name", "sector", "statement", "numberStars");
	}

	@Override
	public Investorsrecords findOne(final Request<Investorsrecords> request) {
		assert request != null;

		Investorsrecords result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
