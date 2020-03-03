
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.entities.auditrecord.Auditrecord;
import acme.entities.descriptor.Descriptor;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.participatein.Participatein;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerJobDeleteService implements AbstractDeleteService<Employer, Job> {

	//	Internal State -------------------------------------------------------------------------------------------------------------------
	@Autowired
	private EmployerJobRepository repository;


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

		res = principalId == job.getEmployer().getUserAccount().getId() && !job.isHasApplication();

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
		request.unbind(entity, model, "reference", "title", "deadline", "textRolenta", "symbol", "hasRolenta", "hasSymbol");

	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;
		Job result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);
		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;
		Collection<Duty> d = this.repository.findDutiesByJobId(entity.getId());
		for (Duty duty : d) {
			this.repository.delete(duty);
		}

		Descriptor dt = this.repository.findDescriptorByJobId(entity.getId());
		Collection<Auditrecord> ar = this.repository.findManyAuditrecordByJobId(entity.getId());
		for (Auditrecord audit : ar) {
			this.repository.delete(audit);
		}

		Collection<Participatein> participates = this.repository.findParticipates(entity.getId());
		for (Participatein pi : participates) {
			this.repository.delete(pi);
		}

		Collection<Application> ap = this.repository.findManyApplicationByJobId(entity.getId());
		for (Application appli : ap) {
			this.repository.delete(appli);
		}

		this.repository.delete(dt);
		this.repository.delete(entity);

	}

}
