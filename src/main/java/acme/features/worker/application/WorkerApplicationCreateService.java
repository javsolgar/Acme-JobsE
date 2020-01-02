
package acme.features.worker.application;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	private WorkerApplicationRepository repository;


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

		request.unbind(entity, model, "reference", "status", "skills", "statement", "qualifications");

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
		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean hasReference, isDuplicated, hasStatus, hasSkills, hasStatement, hasQualifications, alreadyApplicated;
		Integer id;
		Principal principal;
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
			}

			if (!errors.hasErrors("statement")) {
				hasStatement = entity.getStatement() != null;
				errors.state(request, hasStatement, "statement", "worker.application.error.must-have-statement");
			}

			if (!errors.hasErrors("qualifications")) {
				hasQualifications = entity.getQualifications() != null;
				errors.state(request, hasQualifications, "qualifications", "worker.application.error.must-have-qualifications");
			}
		}
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		int jobId;
		Job job;

		jobId = request.getModel().getInteger("jobId");
		job = this.repository.findJobById(jobId);

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		job.setHasApplication(true);

		this.repository.save(job);
		this.repository.save(entity);

	}

}
