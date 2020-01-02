
package acme.features.auditor.auditrecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditrecord.Auditrecord;
import acme.entities.configuration.Configuration;
import acme.entities.roles.Auditor;
import acme.features.utiles.ConfigurationRepository;
import acme.features.utiles.Spamfilter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuditorAuditUpdateService implements AbstractUpdateService<Auditor, Auditrecord> {

	// Internal State --------------------------------------------------------------------------------------

	@Autowired
	private AuditorAuditrecordRepository	repository;

	@Autowired
	private ConfigurationRepository			configurationRepository;

	// AbstractUpdateService<Auditor, Auditrecord> interface -----------------------------------------------


	@Override
	public boolean authorise(final Request<Auditrecord> request) {
		assert request != null;

		boolean result;
		int auditrecordId;
		Auditrecord auditrecord;
		Principal principal;

		auditrecordId = request.getModel().getInteger("id");
		auditrecord = this.repository.findOneAuditrecordById(auditrecordId);
		principal = request.getPrincipal();
		result = auditrecord.getAuditor().getId() == principal.getActiveRoleId() && !auditrecord.getIsFinalMode();
		return result;
	}

	@Override
	public void bind(final Request<Auditrecord> request, final Auditrecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "jobTitle", "auditorUser", "moment", "job");

	}

	@Override
	public void unbind(final Request<Auditrecord> request, final Auditrecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "isFinalMode", "body");

	}

	@Override
	public Auditrecord findOne(final Request<Auditrecord> request) {
		assert request != null;

		Auditrecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAuditrecordById(id);

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
		boolean hasSpamTitle, hasSpamBody;

		configuration = this.configurationRepository.findConfiguration();
		spamWords = configuration.getSpamWords();
		spamThreshold = configuration.getSpamThreshold();

		if (!errors.hasErrors("title") && entity.getTitle() != null) {

			hasSpamTitle = Spamfilter.spamThreshold(entity.getTitle(), spamWords, spamThreshold);
			errors.state(request, !hasSpamTitle, "title", "auditor.auditrecord.error.spam-title");
		}

		if (!errors.hasErrors("body") && entity.getBody() != null) {
			hasSpamBody = Spamfilter.spamThreshold(entity.getBody(), spamWords, spamThreshold);
			errors.state(request, !hasSpamBody, "body", "auditor.auditrecord.error.spam-body");

		}

	}

	@Override
	public void update(final Request<Auditrecord> request, final Auditrecord entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
