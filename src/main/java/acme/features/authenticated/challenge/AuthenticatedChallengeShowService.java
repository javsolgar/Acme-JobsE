
package acme.features.authenticated.challenge;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenge.Challenge;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedChallengeShowService implements AbstractShowService<Authenticated, Challenge> {

	//	Internal State -------------------------------------------------------------------------------------------------------------------
	@Autowired
	private AuthenticatedChallengeRepository repository;


	//	AbstractShowService<Authenticated, Challenge> Interface ---------------------------------------------------------------------------------------
	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		int id;
		Challenge result;
		boolean res;
		Calendar calendar;

		calendar = Calendar.getInstance();
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		res = result.getDeadline().after(calendar.getTime());

		return res;
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "deadline", "rewardGold", "goalGold", "goalSilver", "goalBronze", "rewardSilver", "rewardBronze");

	}

	@Override
	public Challenge findOne(final Request<Challenge> request) {
		assert request != null;
		Challenge result;
		Integer id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
