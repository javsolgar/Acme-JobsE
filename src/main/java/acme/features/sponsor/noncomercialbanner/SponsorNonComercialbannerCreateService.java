
package acme.features.sponsor.noncomercialbanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.noncomercialbanner.Noncomercialbanner;
import acme.entities.roles.Sponsor;
import acme.features.utiles.ConfigurationRepository;
import acme.features.utiles.Spamfilter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorNonComercialbannerCreateService implements AbstractCreateService<Sponsor, Noncomercialbanner> {

	//Internal state -----------------------------------------------------------------------------------------------

	@Autowired
	private SponsorNoncomercialbannerRepository	repository;

	@Autowired
	private ConfigurationRepository				ConfigurationRepository;


	// AbstractCreateService<Sponsor, Noncomercialbanner> interface ------------------------------------------------

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

		request.unbind(entity, model, "urlPicture", "slogan", "urlTarget", "finalMode", "jingle");

	}

	@Override
	public Noncomercialbanner instantiate(final Request<Noncomercialbanner> request) {
		assert request != null;

		Sponsor sponsor;
		Principal principal;
		Noncomercialbanner result;
		int principalId;

		principal = request.getPrincipal();
		principalId = principal.getAccountId();

		sponsor = this.repository.findOneSponsorByUserAccountId(principalId);

		result = new Noncomercialbanner();
		result.setFinalMode(false);
		result.setSponsor(sponsor);
		return result;
	}

	@Override
	public void validate(final Request<Noncomercialbanner> request, final Noncomercialbanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Configuration configuration;
		String spamWords;
		Double spamThreshold;

		boolean hasSlogan, hasSpamSlogan;

		if (!errors.hasErrors("slogan")) {
			hasSlogan = entity.getSlogan() != null && !entity.getSlogan().isEmpty();
			errors.state(request, hasSlogan, "slogan", "sponsor.noncomercialbanner.error.must-have-slogan");

			if (hasSlogan) {

				configuration = this.ConfigurationRepository.findConfiguration();
				spamWords = configuration.getSpamWords();
				spamThreshold = configuration.getSpamThreshold();

				hasSpamSlogan = Spamfilter.spamThreshold(entity.getSlogan(), spamWords, spamThreshold);
				errors.state(request, !hasSpamSlogan, "slogan", "sponsor.noncomercialbanner.error.must-have-not-spam-slogan");
			}
		}

	}

	@Override
	public void create(final Request<Noncomercialbanner> request, final Noncomercialbanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
