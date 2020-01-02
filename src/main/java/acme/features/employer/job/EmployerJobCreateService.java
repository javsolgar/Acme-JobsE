
package acme.features.employer.job;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.descriptor.Descriptor;
import acme.entities.jobs.Job;
import acme.entities.participatein.Participatein;
import acme.entities.roles.Employer;
import acme.features.utiles.ConfigurationRepository;
import acme.features.utiles.Spamfilter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	// Internal state ------------------------------------------------------------------------

	@Autowired
	private EmployerJobRepository	repository;

	@Autowired
	private ConfigurationRepository	repositoryConfiguration;


	// AbstractCreateService<Employer, Job> interface ----------------------------------------
	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "description");

	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "reference", "title", "deadline", "salary", "moreInfo", "textChallenge", "linkInfo");

	}

	@Override
	public Job instantiate(final Request<Job> request) {
		assert request != null;

		Employer employer;
		Principal principal;
		int employerId;

		Job result = new Job();

		result.setHasChallenge(false);
		principal = request.getPrincipal();
		employerId = principal.getActiveRoleId();
		employer = this.repository.findOneEmployerById(employerId);

		result.setEmployer(employer);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean hasTitle, hasSpamTitle, hasDescriptor, hasSpamDescriptor, hasReference, isDuplicated;
		boolean hasSalary, isEuro, hasDeadline, isFuture, hasTextChallenge, hasSpamTextChallenge, hasLinkInfo;

		String descripcion = request.getModel().getString("description").trim();
		Configuration configuration = this.repositoryConfiguration.findConfiguration();
		String spamWords = configuration.getSpamWords();
		Double spamThreshold = configuration.getSpamThreshold();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, 7);

		// Validation title ----------------------------------------------------------------------------------------------------------
		if (!errors.hasErrors("title")) {
			hasTitle = entity.getTitle() != null;
			errors.state(request, hasTitle, "title", "employer.job.error.must-have-title");

			if (hasTitle) {
				hasSpamTitle = Spamfilter.spamThreshold(entity.getTitle(), spamWords, spamThreshold);
				errors.state(request, !hasSpamTitle, "title", "employer.job.error.must-not-have-spam-title");
			}
		}

		// Validation salary ----------------------------------------------------------------------------------------------------------

		if (!errors.hasErrors("salary")) {
			hasSalary = entity.getSalary() != null;
			errors.state(request, hasSalary, "salary", "employer.job.error.must-have-salary");

			if (hasSalary) {
				Money euro = new Money();
				euro.setCurrency("€");

				isEuro = entity.getSalary().getCurrency().equals(euro.getCurrency());
				errors.state(request, isEuro, "salary", "employer.job.error.must-have-salary");

			}
		}

		// Validation deadline ----------------------------------------------------------------------------------------------------------

		if (!errors.hasErrors("deadline")) {
			hasDeadline = entity.getDeadline() != null;
			errors.state(request, hasDeadline, "deadline", "employer.job.error.must-have-deadline");

			if (hasDeadline) {
				isFuture = entity.getDeadline().after(now.getTime());
				errors.state(request, isFuture, "deadline", "employer.job.error.must-be-future");

			}
		}

		// Validation descriptor --------------------------------------------------------------------------------------------------------

		hasDescriptor = descripcion != null && descripcion != "";
		errors.state(request, hasDescriptor, "description", "employer.job.error.must-have-descriptor");

		if (!errors.hasErrors("deadline")) {
			if (hasDescriptor) {
				hasSpamDescriptor = Spamfilter.spamThreshold(descripcion, spamWords, spamThreshold);
				errors.state(request, !hasSpamDescriptor, "description", "employer.job.error.must-not-have-spam-descriptor");
			}
		}

		// Validation reference ----------------------------------------------------------------------------------------------------------
		if (!errors.hasErrors("title")) {
			hasReference = entity.getReference() != null;
			errors.state(request, hasReference, "reference", "employer.job.error.must-have-reference");
			if (hasReference) {
				isDuplicated = this.repository.findOneJobByReference(entity.getReference()) == null;
				errors.state(request, isDuplicated, "reference", "employer.job.error.must-be-not-duplicated-reference");
			}
		}

		// Validation textChallenge ------------------------------------------------------------------------------------------------------

		if (!errors.hasErrors("textChallenge")) {
			hasTextChallenge = entity.getTextChallenge() != null;
			if (hasTextChallenge) {
				hasSpamTextChallenge = Spamfilter.spamThreshold(entity.getTextChallenge(), spamWords, spamThreshold);
				errors.state(request, !hasSpamTextChallenge, "textChallenge", "employer.job.error.must-not-have-spam-textChallenge");
			}
		}

		// Validation linkInfo -----------------------------------------------------------------------------------------------------------

		if (!errors.hasErrors("linkInfo")) {

			hasLinkInfo = entity.getLinkInfo() != null && !entity.getLinkInfo().isEmpty();

			if (!errors.hasErrors("textChallenge") && hasLinkInfo) {

				hasTextChallenge = entity.getTextChallenge() != null && !entity.getTextChallenge().isEmpty();
				errors.state(request, hasTextChallenge, "linkInfo", "employer.job.error.must-have-textChallenge");

			}
		}

	}

	@Override
	public void create(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		boolean hasTextChallenge;
		Descriptor descriptor;
		String description;
		Participatein participatein = new Participatein();

		entity.setHasChallenge(false);
		hasTextChallenge = entity.getTextChallenge() != null && !entity.getTextChallenge().isEmpty();
		if (hasTextChallenge) {
			entity.setHasChallenge(true);
		}

		entity.setHasApplication(false);
		entity.setFinalMode(false);

		participatein.setJob(entity);

		this.repository.save(entity);
		this.repository.save(participatein);

		description = request.getModel().getString("description");
		descriptor = new Descriptor();

		descriptor.setJob(entity);
		descriptor.setDescription(description);

		this.repository.save(descriptor);

	}

}
