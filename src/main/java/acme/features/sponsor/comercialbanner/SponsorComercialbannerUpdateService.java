
package acme.features.sponsor.comercialbanner;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.comercialbanner.Comercialbanner;
import acme.entities.configuration.Configuration;
import acme.entities.roles.Sponsor;
import acme.features.utiles.ConfigurationRepository;
import acme.features.utiles.Spamfilter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class SponsorComercialbannerUpdateService implements AbstractUpdateService<Sponsor, Comercialbanner> {

	// Internal state ----------------------------------------------------------------------------------------

	@Autowired
	private SponsorComercialbannerRepository	repository;

	@Autowired
	private ConfigurationRepository				ConfigurationRepository;


	@Override
	public boolean authorise(final Request<Comercialbanner> request) {
		assert request != null;

		Integer id;
		boolean res;
		Principal principal;
		Comercialbanner banner;
		Sponsor sponsor;

		id = request.getModel().getInteger("id");

		principal = request.getPrincipal();

		sponsor = this.repository.findOneSponsorByUserAccountId(principal.getAccountId());

		banner = this.repository.findOneById(id);

		res = sponsor.getId() == banner.getSponsor().getId();

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

		request.unbind(entity, model, "urlPicture", "slogan", "urlTarget", "finalMode", "creditNumber", "name", "surname", "expiration", "securityCode", "type");

	}

	@Override
	public Comercialbanner findOne(final Request<Comercialbanner> request) {
		assert request != null;

		Comercialbanner result;
		Integer id;

		id = request.getModel().getInteger("id");

		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<Comercialbanner> request, final Comercialbanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Configuration configuration;
		String spamWords;
		Double spamThreshold;

		boolean hasSlogan, hasSpamSlogan, hasExpiration;
		boolean isFuture, hasNumber, hasNameOwner, hasSurname, hasSecurityCode;
		Date now;
		now = new Date(System.currentTimeMillis() - 1);

		// Slogan validation ---------------------------------------------------------------------------------

		if (!errors.hasErrors("slogan")) {
			hasSlogan = entity.getSlogan() != null && !entity.getSlogan().isEmpty();
			errors.state(request, hasSlogan, "slogan", "sponsor.comercialbanner.error.must-have-slogan");

			if (hasSlogan) {

				configuration = this.ConfigurationRepository.findConfiguration();
				spamWords = configuration.getSpamWords();
				spamThreshold = configuration.getSpamThreshold();

				hasSpamSlogan = Spamfilter.spamThreshold(entity.getSlogan(), spamWords, spamThreshold);
				errors.state(request, !hasSpamSlogan, "slogan", "sponsor.comercialbanner.error.must-have-not-spam-slogan");
			}
		}

		// Expiration validation ------------------------------------------------------------------------------------

		if (!errors.hasErrors("expiration")) {
			hasExpiration = entity.getExpiration() != null;
			errors.state(request, hasExpiration, "expiration", "sponsor.comercialbanner.error.must-have-expiration");
			if (hasExpiration) {
				isFuture = now.before(entity.getExpiration());
				errors.state(request, isFuture, "expiration", "sponsor.comercialbanner.error.expirated");
			}

			// Number validation ----------------------------------------------------------------------------------------

			if (!errors.hasErrors("creditNumber")) {
				hasNumber = entity.getCreditNumber() != null;
				errors.state(request, hasNumber, "creditNumber", "sponsor.comercialbanner.error.must-have-creditNumber");
			}
			// Name validation ------------------------------------------------------------------------------------------

			if (!errors.hasErrors("name")) {
				hasNameOwner = entity.getName() != null;
				errors.state(request, hasNameOwner, "name", "sponsor.comercialbanner.error.must-have-name");
			}

			// Surname validation ---------------------------------------------------------------------------------------

			if (!errors.hasErrors("surname")) {
				hasSurname = entity.getSurname() != null;
				errors.state(request, hasSurname, "surname", "sponsor.comercialbanner.error.must-have-surname");
			}

			// Security code validation ----------------------------------------------------------------------------------

			if (!errors.hasErrors("securityCode")) {
				hasSecurityCode = entity.getSecurityCode() != null;
				errors.state(request, hasSecurityCode, "securityCode", "sponsor.comercialbanner.error.must-have-securityCode");
			}
			boolean hasSecurityCodeP = entity.getSecurityCode().matches("^[0-9]{3}$");
			errors.state(request, hasSecurityCodeP, "securityCode", "sponsor.comercialbanner.error.pattern-securityCode");
			boolean ErrorPatterntype = entity.getType().matches("^(Dinners Club)|(Visa)|(Master Card)|(American Express)$");
			errors.state(request, ErrorPatterntype, "type", "sponsor.comercialbanner.error.pattern-type");

		}
	}

	@Override
	public void update(final Request<Comercialbanner> request, final Comercialbanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
