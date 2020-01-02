
package acme.features.authenticated.challenge;

import java.util.Calendar;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenge.Challenge;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedChallengeListService implements AbstractListService<Authenticated, Challenge> {

	//	 Internal  state	-----------------------------------------------------------------------------------------------------------------

	@Autowired
	private AuthenticatedChallengeRepository repository;


	//	  AbstractListService<Authenticated, Challenge> Interface	-------------------------------------------------------------------------
	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "deadline", "rewardGold", "rewardSilver", "rewardBronze");
	}

	@Override
	public Collection<Challenge> findMany(final Request<Challenge> request) {
		assert request != null;
		Collection<Challenge> result;
		result = this.repository.findManyAll();
		Calendar calendar = Calendar.getInstance();
		return result.stream().filter(x -> x.getDeadline().compareTo(calendar.getTime()) > 0).collect(Collectors.toList());
	}

}
