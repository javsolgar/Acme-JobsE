
package acme.features.auditor.auditrecord;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditrecord.Auditrecord;
import acme.entities.configuration.Configuration;
import acme.entities.jobs.Job;
import acme.entities.participatein.Participatein;
import acme.entities.roles.Auditor;
import acme.features.utiles.ConfigurationRepository;
import acme.features.utiles.Spamfilter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuditorAuditrecordCreateService implements AbstractCreateService<Auditor, Auditrecord> {

	//Internal state --------------------------------------------------------------------------------------------------

	@Autowired
	private AuditorAuditrecordRepository	repository;

	@Autowired
	private ConfigurationRepository			configurationRepository;


	// AbstractCreateService<Auditor, Auditrecord> -------------------------------------------------------------

	@Override
	public boolean authorise(final Request<Auditrecord> request) {
		assert request != null;
		boolean res;
		Integer id;
		Job result;

		id = request.getModel().getInteger("idJob");
		result = this.repository.findJobByRef(id);

		res = result.isFinalMode();

		return res;
	}

	@Override
	public void bind(final Request<Auditrecord> request, final Auditrecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "job");

	}

	@Override
	public void unbind(final Request<Auditrecord> request, final Auditrecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "isFinalMode", "body");

	}

	@Override
	public Auditrecord instantiate(final Request<Auditrecord> request) {
		Auditrecord result;
		Date moment;
		Principal principal;
		Integer idUserAccount;
		Auditor auditor;

		principal = request.getPrincipal();
		idUserAccount = principal.getActiveRoleId();

		auditor = this.repository.findAuditorById(idUserAccount);
		result = new Auditrecord();

		moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);
		result.setAuditor(auditor);

		int job = request.getModel().getInteger("idJob");
		result.setJob(this.repository.findJobByRef(job));
		result.setIsFinalMode(false);
		return result;
	}

	@Override
	public void validate(final Request<Auditrecord> request, final Auditrecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Configuration configuration;
		String spamWords;
		Double spamThreshold;
		boolean hasSpamTitle, hasSpamBody, notAuditYet;
		int job = request.getModel().getInteger("idJob");
		configuration = this.configurationRepository.findConfiguration();
		spamWords = configuration.getSpamWords();
		spamThreshold = configuration.getSpamThreshold();

		notAuditYet = true;
		Collection<Auditrecord> audits = this.repository.findManyByJobIdAll(job);
		for (Auditrecord at : audits) {
			if (at.getAuditor().equals(entity.getAuditor())) {
				notAuditYet = false;
				break;
			}
		}
		errors.state(request, notAuditYet, "title", "auditor.auditrecord.error.already-auditor");
		if (notAuditYet) {
			boolean isJob = this.repository.findJobByRef(job) != null;
			errors.state(request, isJob, "job", "auditor.auditrecord.error.must-exists");

			if (!errors.hasErrors("title") && entity.getTitle() != null) {

				hasSpamTitle = Spamfilter.spamThreshold(entity.getTitle(), spamWords, spamThreshold);
				errors.state(request, !hasSpamTitle, "title", "auditor.auditrecord.error.spam-title");
			}

			if (!errors.hasErrors("body") && entity.getBody() != null) {
				hasSpamBody = Spamfilter.spamThreshold(entity.getBody(), spamWords, spamThreshold);
				errors.state(request, !hasSpamBody, "body", "auditor.auditrecord.error.spam-body");

			}
		}

	}

	@Override
	public void create(final Request<Auditrecord> request, final Auditrecord entity) {
		Date moment;
		Principal principal;
		Integer idUserAccount;
		Auditor auditor;
		Participatein participatein;

		principal = request.getPrincipal();
		idUserAccount = principal.getActiveRoleId();
		auditor = this.repository.findAuditorById(idUserAccount);

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		int job = request.getModel().getInteger("idJob");
		entity.setJob(this.repository.findJobByRef(job));
		entity.setAuditor(auditor);
		participatein = this.repository.getParticipateinByJobId(job);

		participatein.setAuditor(auditor);
		this.repository.save(entity);
		this.repository.save(participatein);

	}

}
