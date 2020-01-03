
package acme.features.worker.application;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.entities.configuration.Configuration;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.features.utiles.ConfigurationRepository;
import acme.features.utiles.Spamfilter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	private WorkerApplicationRepository	repository;

	@Autowired
	private ConfigurationRepository		confRepository;


	@Override
	public boolean authorise(final Request<Application> request) {

		assert request != null;

		boolean result;
		int jobId;
		Job job;

		jobId = request.getModel().getInteger("jobId");
		job = this.repository.findJobById(jobId);
		result = job.isFinalMode();

		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "worker");

	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "status", "skills", "statement", "qualifications", "answer", "optionalApplication", "password", "hasChallenge");

	}

	@Override
	public Application instantiate(final Request<Application> request) {
		assert request != null;
		Application result = new Application();
		Worker worker;
		Job job;
		Principal principal;
		int workerId;
		int jobId;
		Date moment = new Date(System.currentTimeMillis() - 1);

		principal = request.getPrincipal();
		workerId = principal.getActiveRoleId();
		worker = this.repository.findWorkerById(workerId);
		jobId = request.getModel().getInteger("jobId");
		job = this.repository.findJobById(jobId);
		result.setWorker(worker);
		result.setJob(job);
		result.setStatus("pending");
		result.setMoment(moment);
		result.setHasXXXX(false);
		result.setHasPassword(false);
		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean hasReference, isDuplicated, hasStatus, hasSkills, hasStatement, hasQualifications, alreadyApplicated, hasSpamSkills, hasSpamStatement, hasSpamQualifications;
		boolean hasAnswer, hasSpamAnswer, hasOptionalApplication, hasSpamOptionalApplication, hasPassword;
		Integer id;
		Principal principal;
		String spamWords;
		Double spamThreshold;
		Configuration configuration;

		configuration = this.confRepository.findConfiguration();
		spamWords = configuration.getSpamWords();
		spamThreshold = configuration.getSpamThreshold();
		Collection<Application> result;
		principal = request.getPrincipal();
		id = request.getModel().getInteger("jobId");
		result = this.repository.findManyByJobId(id);

		alreadyApplicated = true;

		for (Application a : result) {
			if (a.getWorker().getId() == principal.getActiveRoleId()) {
				alreadyApplicated = false;
				break;
			}

		}

		errors.state(request, alreadyApplicated, "reference", "worker.application.error.had-applicated");
		if (alreadyApplicated) {
			if (!errors.hasErrors("reference")) {
				hasReference = entity.getReference() != null;
				errors.state(request, hasReference, "reference", "worker.application.error.must-have-reference");
				if (hasReference) {
					isDuplicated = this.repository.findOneByReference(entity.getReference()) != null;
					errors.state(request, !isDuplicated, "reference", "worker.application.error.must-be-unique");
				}
			}
			if (!errors.hasErrors("status")) {
				hasStatus = entity.getStatus() != null;
				errors.state(request, hasStatus, "status", "worker.application.error.must-have-status");
				if (hasStatus) {
					boolean pending = entity.getStatus().equals("pending");
					errors.state(request, pending, "status", "worker.application.error.must-be-pending");
				}
			}

			boolean ErrorPattern = entity.getStatus().matches("^(pending)|(accepted)|(rejected)$");
			errors.state(request, ErrorPattern, "status", "worker.application.error.pattern-status");

			if (!errors.hasErrors("skills")) {
				hasSkills = entity.getSkills() != null;
				errors.state(request, hasSkills, "skills", "worker.application.error.must-have-skills");
				if (hasSkills) {
					hasSpamSkills = Spamfilter.spamThreshold(entity.getSkills(), spamWords, spamThreshold);
					errors.state(request, !hasSpamSkills, "skills", "worker.application.error.must-not-have-spam-skills");
				}
			}

			if (!errors.hasErrors("statement")) {
				hasStatement = entity.getStatement() != null;
				errors.state(request, hasStatement, "statement", "worker.application.error.must-have-statement");
				if (hasStatement) {
					hasSpamStatement = Spamfilter.spamThreshold(entity.getStatement(), spamWords, spamThreshold);
					errors.state(request, !hasSpamStatement, "statement", "worker.application.error.must-not-have-spam-statement");
				}
			}

			if (!errors.hasErrors("qualifications")) {
				hasQualifications = entity.getQualifications() != null;
				errors.state(request, hasQualifications, "qualifications", "worker.application.error.must-have-qualifications");
				if (hasQualifications) {
					hasSpamQualifications = Spamfilter.spamThreshold(entity.getQualifications(), spamWords, spamThreshold);
					errors.state(request, !hasSpamQualifications, "qualifications", "worker.application.error.must-not-have-spam-qualifications");
				}
			}

			// Validation answer -------------------------------------------------------------------------------------------------------------

			if (!errors.hasErrors("answer")) {

				hasAnswer = entity.getAnswer() != null && !entity.getAnswer().isEmpty();

				if (hasAnswer) {
					hasSpamAnswer = Spamfilter.spamThreshold(entity.getAnswer(), spamWords, spamThreshold);
					errors.state(request, !hasSpamAnswer, "answer", "worker.application.error.must-not-have-spam-answer");
				}

			}

			// Validation optionalApplication ------------------------------------------------------------------------------------------------

			if (!errors.hasErrors("optionalApplication")) {

				hasOptionalApplication = entity.getOptionalApplication() != null && !entity.getOptionalApplication().isEmpty();

				if (hasOptionalApplication && !errors.hasErrors("answer")) {

					hasAnswer = entity.getAnswer() != null && !entity.getAnswer().isEmpty();
					errors.state(request, hasAnswer, "optionalApplication", "worker.application.error.must-have-answer");

					if (hasAnswer) {

						hasSpamOptionalApplication = Spamfilter.spamThreshold(entity.getOptionalApplication(), spamWords, spamThreshold);
						errors.state(request, !hasSpamOptionalApplication, "optionalApplication", "worker.application.error.must-not-have-spam-optionalApplication");
					}

				}

			}

			// Validation password -----------------------------------------------------------------------------------------------------------

			if (!errors.hasErrors("password")) {

				hasPassword = entity.getPassword() != null && !entity.getPassword().isEmpty();

				if (hasPassword) {
					hasOptionalApplication = entity.getOptionalApplication() != null && !entity.getOptionalApplication().isEmpty();
					errors.state(request, hasOptionalApplication, "password", "worker.application.error.must-have-optional");
				}

			}
		}
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		boolean hasPassword, hasXXXX;
		int jobId;
		Job job;

		jobId = request.getModel().getInteger("jobId");
		job = this.repository.findJobById(jobId);

		entity.setHasXXXX(false);
		entity.setHasPassword(false);
		//entity.setIncludeXXXXinXXXX(false);
		entity.setHasBeenProtected(false);

		// Si incluye contresale las aplicaciones -----------------------------------------------------------------------------
		hasPassword = entity.getPassword() != null && !entity.getPassword().isEmpty();
		hasXXXX = entity.getAnswer() != null && !entity.getAnswer().isEmpty();
		if (hasPassword) {
			entity.setHasPassword(true);
			entity.setHasBeenProtected(true);
		}

		// Si incluye XXXX las aplicaciones -----------------------------------------------------------------------------
		if (hasXXXX) {
			entity.setHasXXXX(true);
			/*
			 * hasoptionalApplication = entity.getOptionalApplication() != null && !entity.getOptionalApplication().isEmpty();
			 * if (hasoptionalApplication) {
			 * entity.setIncludeXXXXinXXXX(true);
			 * }
			 */
		}

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		job.setHasApplication(true);

		this.repository.save(job);
		this.repository.save(entity);

	}

}
