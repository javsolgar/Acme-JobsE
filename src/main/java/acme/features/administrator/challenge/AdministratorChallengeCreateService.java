
package acme.features.administrator.challenge;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenge.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorChallengeCreateService implements AbstractCreateService<Administrator, Challenge> {

	//	Internal State -------------------------------------------------------------------------------------------------------------------
	@Autowired
	private AdministratorChallengeRepository repository;


	//	AbstractShowService<Authenticated, Challenge> Interface ---------------------------------------------------------------------------------------

	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "description", "deadline", "goalGold", "goalSilver", "goalBronze", "rewardGold", "rewardSilver", "rewardBronze");

	}

	@Override
	public Challenge instantiate(final Request<Challenge> request) {
		Challenge result;

		result = new Challenge();
		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date dateNow, deadline;
		boolean isFuture, hasDeadline;
		boolean hasGoldGoal, hasSilverGoal, hasBronzeGoal;
		boolean goldGoalFirst, goldSilverSecond, goldBronzeThird;
		boolean hasGoldReward, hasSilverReward, hasBronzeReward;
		Money rewardGold, rewardSilver, rewardBronze;
		boolean goldEUR, silverEUR, bronzeEUR;
		String currentGold, currentSilver, currentBronze;

		dateNow = new Date(System.currentTimeMillis() - 1);

		if (!errors.hasErrors("deadline")) {
			hasDeadline = entity.getDeadline() != null;
			errors.state(request, hasDeadline, "deadline", "administrator.challenge.error.must-have-deadline");

			if (hasDeadline) {
				deadline = entity.getDeadline();
				isFuture = dateNow.before(deadline);
				errors.state(request, isFuture, "deadline", "administrator.challenge.error.must-be-future");

			}
		}
		if (!errors.hasErrors("goalGold")) {
			hasGoldGoal = entity.getGoalGold() != null;
			errors.state(request, hasGoldGoal, "goalGold", "administrator.challenge.error.must-have-goal");
		}

		if (!errors.hasErrors("goalSilver")) {
			hasSilverGoal = entity.getGoalSilver() != null;
			errors.state(request, hasSilverGoal, "goalSilver", "administrator.challenge.error.must-have-goal");
		}

		if (!errors.hasErrors("goalBronze")) {
			hasBronzeGoal = entity.getGoalBronze() != null;
			errors.state(request, hasBronzeGoal, "goalBronze", "administrator.challenge.error.must-have-goal");
		}

		hasGoldReward = entity.getRewardGold() != null;
		errors.state(request, hasGoldReward, "rewardGold", "administrator.challenge.error.must-have-reward");

		hasSilverReward = entity.getRewardSilver() != null;
		errors.state(request, hasSilverReward, "rewardSilver", "administrator.challenge.error.must-have-reward");

		hasBronzeReward = entity.getRewardBronze() != null;
		errors.state(request, hasBronzeReward, "rewardBronze", "administrator.challenge.error.must-have-reward");

		if (hasGoldReward && hasSilverReward && hasBronzeReward) {
			Money euro = new Money();
			euro.setCurrency("€");
			rewardGold = entity.getRewardGold();
			rewardSilver = entity.getRewardSilver();
			rewardBronze = entity.getRewardBronze();

			currentGold = rewardGold.getCurrency();
			currentSilver = rewardSilver.getCurrency();
			currentBronze = rewardBronze.getCurrency();

			goldEUR = currentGold.equals(euro.getCurrency());
			errors.state(request, goldEUR, "rewardGold", "administrator.challenge.error.must-be-eur");

			silverEUR = currentSilver.equals(euro.getCurrency());
			errors.state(request, silverEUR, "rewardSilver", "administrator.challenge.error.must-be-eur");

			bronzeEUR = currentBronze.equals(euro.getCurrency());
			errors.state(request, bronzeEUR, "rewardBronze", "administrator.challenge.error.must-be-eur");

			if (goldEUR && silverEUR && bronzeEUR) {

				goldGoalFirst = rewardGold.getAmount() >= rewardSilver.getAmount() && rewardGold.getAmount() >= rewardBronze.getAmount();
				errors.state(request, goldGoalFirst, "rewardGold", "administrator.challenge.error.goldFirst");

				if (goldGoalFirst) {

					goldSilverSecond = rewardGold.getAmount() >= rewardSilver.getAmount() && rewardSilver.getAmount() >= rewardBronze.getAmount();
					errors.state(request, goldSilverSecond, "rewardSilver", "administrator.challenge.error.silverSecond");

					if (goldSilverSecond) {

						goldBronzeThird = rewardGold.getAmount() >= rewardBronze.getAmount() && rewardSilver.getAmount() >= rewardBronze.getAmount();
						errors.state(request, goldBronzeThird, "rewardBronze", "administrator.challenge.error.bronzeThird");
					}
				}
			}
		}

	}

	@Override
	public void create(final Request<Challenge> request, final Challenge entity) {
		this.repository.save(entity);

	}

}
