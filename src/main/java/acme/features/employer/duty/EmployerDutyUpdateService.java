
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.duties.Duty;
import acme.entities.roles.Employer;
import acme.features.utiles.ConfigurationRepository;
import acme.features.utiles.Spamfilter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerDutyUpdateService implements AbstractUpdateService<Employer, Duty> {

	// Internal State --------------------------------------------------------------------------------------

	@Autowired
	private EmployerDutyRepository	repository;

	@Autowired
	private ConfigurationRepository	repositoryConfiguration;

	// AbstractUpdateService<Employer, Duty> interface -----------------------------------------------


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		Duty Duty;
		Principal principal;
		int idPrincipal, id;
		boolean res;

		principal = request.getPrincipal();
		idPrincipal = principal.getAccountId();
		id = request.getModel().getInteger("id");
		Duty = this.repository.findDutyById(id);

		res = idPrincipal == Duty.getDescriptor().getJob().getEmployer().getUserAccount().getId() && !Duty.getFinalMode();

		return res;
	}

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "descriptor");

	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "description");

	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;

		Duty result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findDutyById(id);

		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Configuration configuration;
		String spamWords;
		Double spamThreshold;
		boolean hasDescription;

		boolean hasTitle, hasSpamTitle, hasPercentage, isPercentage, percentageSumLess100;
		boolean hasSpamDescription;

		configuration = this.repositoryConfiguration.findConfiguration();
		spamWords = configuration.getSpamWords();
		spamThreshold = configuration.getSpamThreshold();

		// Title validation -------------------------------------------------------------------------------

		if (!errors.hasErrors("title")) {
			hasTitle = entity.getTitle() != null;
			errors.state(request, hasTitle, "title", "employer.duty.error.must-have-title");

			if (hasTitle) {
				hasSpamTitle = Spamfilter.spamThreshold(entity.getTitle(), spamWords, spamThreshold);
				errors.state(request, !hasSpamTitle, "title", "employer.duty.error.must-not-have-title-spam");
			}
		}

		// Description validation ------------------------------------------------------------------------

		if (!errors.hasErrors("description")) {
			hasDescription = entity.getDescription() != null;
			errors.state(request, hasDescription, "description", "employer.duty.error.must-have-description");

			if (hasDescription) {
				hasSpamDescription = Spamfilter.spamThreshold(entity.getDescription(), spamWords, spamThreshold);
				errors.state(request, !hasSpamDescription, "description", "employer.duty.error.must-not-be-spam");
			}
		}

		// Percentage validation -------------------------------------------------------------------------

		if (!errors.hasErrors("percentage")) {
			hasPercentage = entity.getPercentage() != null;
			errors.state(request, hasPercentage, "percentage", "employer.duty.error.must-not-have-percentage");
			if (hasPercentage) {
				isPercentage = entity.getPercentage() >= 0 && entity.getPercentage() <= 100;
				errors.state(request, isPercentage, "percentage", "employer.duty.error.must-be-a-percentage");

				if (isPercentage) {
					percentageSumLess100 = true;
					Collection<Duty> allDutys;
					int descriptorId;
					Double sum = 0.;

					descriptorId = entity.getDescriptor().getId();

					allDutys = this.repository.findManyByDescriptorId(descriptorId);

					for (Duty d : allDutys) {
						if (d.getId() != entity.getId()) {
							sum += d.getPercentage();
						}

					}
					sum += entity.getPercentage();

					if (sum > 100) {
						percentageSumLess100 = false;
					}

					errors.state(request, percentageSumLess100, "percentage", "employer.duty.error.must-be-less-or-equal-than-101");

				}
			}
		}

	}

	@Override
	public void update(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
