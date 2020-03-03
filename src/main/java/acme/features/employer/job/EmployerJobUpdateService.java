
package acme.features.employer.job;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.features.utiles.ConfigurationRepository;
import acme.features.utiles.Spamfilter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

	// Internal State --------------------------------------------------------------------------

	@Autowired
	private EmployerJobRepository	repository;

	@Autowired
	private ConfigurationRepository	confRepository;

	// AbstractUpdateService<Employer, Job> interface -----------------------------------------------------


	@Override

	public boolean authorise(final Request<Job> request) {
		assert request != null;

		Principal principal;
		int principalId, id;
		Job job;
		boolean res;

		principal = request.getPrincipal();
		principalId = principal.getAccountId();
		id = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(id);

		res = principalId == job.getEmployer().getUserAccount().getId();

		if (res && job.isFinalMode()) {
			res = false;
		}

		return res;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "moreInfo", "finalMode", "textRolenta", "symbol", "hasRolenta", "hasSymbol");

	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;
		int id;
		Job result;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean hasTitle, hasSpamTitle;
		boolean hasSalary, isEuro, hasDeadline, isFuture, is100, hasTextRolenta, hasSpamTextRolenta, hasSymbol;

		Configuration configuration = this.confRepository.findConfiguration();
		String spamWords = configuration.getSpamWords();
		Double spamThreshold = configuration.getSpamThreshold();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, 7);
		Collection<Duty> duties;

		// Validation title ----------------------------------------------------------------------------------------------------------

		if (!errors.hasErrors("title")) {
			hasTitle = entity.getTitle() != null;
			errors.state(request, hasTitle, "title", "employer.job.error.must-have-title");

			if (hasTitle) {
				hasSpamTitle = Spamfilter.spamThreshold(entity.getTitle(), spamWords, spamThreshold);
				errors.state(request, !hasSpamTitle, "title", "employer.job.error.must-not-have-spam");
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

		//Validation duties 100% --------------------------------------------------------------------------------------------------------

		if (!errors.hasErrors("finalMode")) {
			if (entity.isFinalMode()) {
				duties = this.repository.findDutiesByJobId(entity.getId());
				Double sum = 0.0;
				for (Duty d : duties) {
					sum += d.getPercentage();
				}
				is100 = sum == 100.0;
				errors.state(request, is100, "finalMode", "employer.job.error.must-100");
			}
		}

		// Validation textChallenge ------------------------------------------------------------------------------------------------------

		if (!errors.hasErrors("textRolenta"))

		{
			hasTextRolenta = entity.getTextRolenta() != null;
			if (hasTextRolenta) {
				hasSpamTextRolenta = Spamfilter.spamThreshold(entity.getTextRolenta(), spamWords, spamThreshold);
				errors.state(request, !hasSpamTextRolenta, "textRolenta", "employer.job.error.must-not-have-spam-textChallenge");
			}
		}

		// Validation linkInfo -----------------------------------------------------------------------------------------------------------

		if (!errors.hasErrors("symbol")) {

			hasSymbol = entity.getSymbol() != null && !entity.getSymbol().isEmpty();

			if (!errors.hasErrors("textRolenta") && hasSymbol) {

				hasTextRolenta = entity.getTextRolenta() != null && !entity.getTextRolenta().isEmpty();
				errors.state(request, hasTextRolenta, "symbol", "employer.job.error.must-have-textChallenge");

			}
		}

	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		boolean hasTextRolenta, hasSymbol;

		entity.setHasRolenta(false);
		hasTextRolenta = entity.getTextRolenta() != null && !entity.getTextRolenta().isEmpty();
		if (hasTextRolenta) {
			entity.setHasRolenta(true);
		}

		entity.setHasSymbol(false);
		hasSymbol = entity.getSymbol() != null && !entity.getSymbol().isEmpty();
		if (hasSymbol) {
			entity.setHasSymbol(true);
		}

		this.repository.save(entity);

	}

}
