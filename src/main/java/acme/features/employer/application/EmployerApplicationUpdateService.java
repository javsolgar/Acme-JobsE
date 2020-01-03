
package acme.features.employer.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.entities.configuration.Configuration;
import acme.entities.roles.Employer;
import acme.features.utiles.ConfigurationRepository;
import acme.features.utiles.Spamfilter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerApplicationUpdateService implements AbstractUpdateService<Employer, Application> {

	@Autowired
	private EmployerApplicationRepository	repository;

	@Autowired
	private ConfigurationRepository			confRepository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		boolean result;
		int applicationId;
		Application application;
		Employer employer;
		Principal principal;

		applicationId = request.getModel().getInteger("id");
		application = this.repository.findOneById(applicationId);
		employer = application.getJob().getEmployer();
		principal = request.getPrincipal();
		result = employer.getId() == principal.getActiveRoleId();
		result = result && application.getStatus().equals("pending");
		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "reference", "moment", "skills", "statement", "qualifications", "worker", "job", "answer", "optionalApplication", "answerEmployer", "hasXXXX", "hasPassword");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "status", "justification");
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;
		int id;
		Application result;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Configuration configuration;
		String spamWords;
		Double spamThreshold;

		//password = request.getModel().getString("answerEmployer");

		boolean isJustificated, hasSpamJustification, correctPassword, hasPassword, passwordNotNull;
		if (!errors.hasErrors("status")) {
			if (request.getModel().getString("status").equals("rejected")) {
				isJustificated = !request.getModel().getString("justification").trim().equals("");
				errors.state(request, isJustificated, "status", "employer.request.error.must-justificated");

			}

		}

		if (!errors.hasErrors("justification")) {
			isJustificated = !request.getModel().getString("justification").trim().equals("");

			if (isJustificated) {

				configuration = this.confRepository.findConfiguration();
				spamWords = configuration.getSpamWords();
				spamThreshold = configuration.getSpamThreshold();

				hasSpamJustification = Spamfilter.spamThreshold(entity.getJustification(), spamWords, spamThreshold);
				errors.state(request, !hasSpamJustification, "justification", "employer.application.error.must-not-have-spam-justification");
			}

		}

		boolean ErrorPattern = entity.getStatus().matches("^(pending)|(accepted)|(rejected)$");
		errors.state(request, ErrorPattern, "status", "employer.application.error.pattern-status");

		passwordNotNull = request.getModel().hasAttribute("passwordEmployer");
		if (passwordNotNull) {
			hasPassword = request.getModel().getString("passwordEmployer").trim() != null && !request.getModel().getString("passwordEmployer").trim().isEmpty();
			if (hasPassword) {
				correctPassword = request.getModel().getString("passwordEmployer").trim().equals(entity.getPassword());
				errors.state(request, correctPassword, "passwordEmployer", "employer.application.errors.incorrectPasword");
			}
		}

	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		boolean hasPassword, correctPassword, passwordNotNull;

		passwordNotNull = request.getModel().hasAttribute("passwordEmployer");
		if (passwordNotNull) {
			hasPassword = request.getModel().getString("passwordEmployer").trim() != null && !request.getModel().getString("passwordEmployer").trim().isEmpty();
			if (hasPassword) {
				correctPassword = request.getModel().getString("passwordEmployer").trim().equals(entity.getPassword());
				if (correctPassword) {
					entity.setHasPassword(false);
				}
			}
		}

		this.repository.save(entity);
	}
}
