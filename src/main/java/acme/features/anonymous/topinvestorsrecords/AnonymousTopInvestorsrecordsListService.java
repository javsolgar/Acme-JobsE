
package acme.features.anonymous.topinvestorsrecords;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investorsrecords.Investorsrecords;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousTopInvestorsrecordsListService implements AbstractListService<Anonymous, Investorsrecords> {

	@Autowired
	private AnonymousTopInvestorsrecordsRepository repository;


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

		request.unbind(entity, model, "name", "sector", "numberStars");

	}

	@Override
	public Collection<Investorsrecords> findMany(final Request<Investorsrecords> request) {
		assert request != null;

		Collection<Investorsrecords> result;
		result = this.repository.findManyAll();
		return result;
	}

}
