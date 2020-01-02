package acme.features.administrator.comercialbanner;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.comercialbanner.Comercialbanner;
import acme.entities.configuration.Configuration;
import acme.features.utiles.ConfigurationRepository;
import acme.features.utiles.Spamfilter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorComercialbannerCreateService implements AbstractCreateService<Administrator, Comercialbanner> {

	@Autowired
	AdministratorComercialbannerRepository	repository;

	@Autowired
	private ConfigurationRepository			ConfigurationRepository;


	@Override
	public boolean authorise(final Request<Comercialbanner> request) {
		assert request != null;
		return true;
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
	public Comercialbanner instantiate(final Request<Comercialbanner> request) {
		assert request != null;

		Administrator administrator;
		Principal principal;
		Comercialbanner result;
		Integer principalId;

		principal = request.getPrincipal();
		principalId = principal.getAccountId();

		administrator = this.repository.findAdministratorByUserAccountId(principalId);

		result = new Comercialbanner();
		result.setFinalMode(false);
		result.setAdministrator(administrator);
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
			errors.state(request, hasSlogan, "slogan", "administrator.comercialbanner.error.must-have-slogan");

			if (hasSlogan) {

				configuration = this.ConfigurationRepository.findConfiguration();
				spamWords = configuration.getSpamWords();
				spamThreshold = configuration.getSpamThreshold();

				hasSpamSlogan = Spamfilter.spamThreshold(entity.getSlogan(), spamWords, spamThreshold);
				errors.state(request, !hasSpamSlogan, "slogan", "administrator.comercialbanner.error.must-have-not-spam-slogan");
			}
		}

		// Expiration validation ------------------------------------------------------------------------------------

		if (!errors.hasErrors("expiration")) {
			hasExpiration = entity.getExpiration() != null;
			errors.state(request, hasExpiration, "expiration", "administrator.comercialbanner.error.must-have-expiration");
			if (hasExpiration) {
				isFuture = now.before(entity.getExpiration());
				errors.state(request, isFuture, "expiration", "administrator.comercialbanner.error.expirated");
			}
		}

		// Number validation ----------------------------------------------------------------------------------------

		if (!errors.hasErrors("creditNumber")) {
			hasNumber = entity.getCreditNumber() != null;
			errors.state(request, hasNumber, "creditNumber", "administrator.comercialbanner.error.must-have-creditNumber");
		}

		// Name validation ------------------------------------------------------------------------------------------

		if (!errors.hasErrors("name")) {
			hasNameOwner = entity.getName() != null;
			errors.state(request, hasNameOwner, "name", "administrator.comercialbanner.error.must-have-name");
		}

		// Surname validation ---------------------------------------------------------------------------------------

		if (!errors.hasErrors("surname")) {
			hasSurname = entity.getSurname() != null;
			errors.state(request, hasSurname, "surname", "administrator.comercialbanner.error.must-have-surname");
		}

		// Security code validation ----------------------------------------------------------------------------------

		if (!errors.hasErrors("securityCode")) {
			hasSecurityCode = entity.getSecurityCode() != null;
			errors.state(request, hasSecurityCode, "securityCode", "administrator.comercialbanner.error.must-have-securityCode");
		}
    boolean hasSecurityCodeP = entity.getSecurityCode().matches("^[0-9]{3}$");
		errors.state(request, hasSecurityCodeP, "securityCode", "administrator.comercialbanner.error.pattern-securityCode");
		boolean ErrorPatterntype = entity.getType().matches("^(Dinners Club)|(Visa)|(Master Card)|(American Express)$");
		errors.state(request, ErrorPatterntype, "type", "administrator.comercialbanner.error.pattern-type");

	}

	@Override
	public void create(final Request<Comercialbanner> request, final Comercialbanner entity) {
		assert request != null;
		assert entity != null;

		entity.setFinalMode(true);
		this.repository.save(entity);
	}

}

