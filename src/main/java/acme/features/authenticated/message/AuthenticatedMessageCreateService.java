
package acme.features.authenticated.message;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.messages.Message;
import acme.features.utiles.ConfigurationRepository;
import acme.features.utiles.Spamfilter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	//Internal state --------------------------------------------------------------------------------------------------

	@Autowired
	private AuthenticatedMessageRepository	repository;

	@Autowired
	private ConfigurationRepository			configurationRepository;


	// AbstractCreateService<Authenticated, Message> -------------------------------------------------------------

	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		boolean result = false;
		Collection<Authenticated> users = this.repository.findUsersFromMTId(request.getModel().getInteger("id"));
		for (Authenticated au : users) {
			if (au.getId() == request.getPrincipal().getActiveRoleId()) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "messageThread");

	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "tags", "body");

	}

	@Override
	public Message instantiate(final Request<Message> request) {
		Message result;

		result = new Message();

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);
		int messageThread = request.getModel().getInteger("id");
		result.setMessageThread(this.repository.findMTById(messageThread));
		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean hasTitle, hasSpamTitle, hasTags, hasTagsSpam, hasBody, hasBodySpam;
		Configuration configuration;
		String spamWords;
		Double spamThreshold;
		configuration = this.configurationRepository.findConfiguration();
		spamWords = configuration.getSpamWords();
		spamThreshold = configuration.getSpamThreshold();

		if (!errors.hasErrors("accept")) {
			boolean isAccepted = request.getModel().getString("accept") != "" && request.getModel().getString("accept") != null;
			errors.state(request, isAccepted, "accept", "authenticated.message.error.must-accept");
		}

		if (!errors.hasErrors("title")) {

			hasTitle = entity.getTitle() != null;
			if (hasTitle) {

				hasSpamTitle = Spamfilter.spamThreshold(entity.getTitle(), spamWords, spamThreshold);
				errors.state(request, !hasSpamTitle, "title", "authenticated.messagethread.error.spam-title");

			}

		}

		if (!errors.hasErrors("tags")) {

			hasTags = entity.getTags() != null;
			if (hasTags) {

				hasTagsSpam = Spamfilter.spamThreshold(entity.getTags(), spamWords, spamThreshold);
				errors.state(request, !hasTagsSpam, "tags", "authenticated.messagethread.error.spam-tags");
			}

		}

		if (!errors.hasErrors("body")) {

			hasBody = entity.getBody() != null;
			if (hasBody) {

				hasBodySpam = Spamfilter.spamThreshold(entity.getBody(), spamWords, spamThreshold);
				errors.state(request, !hasBodySpam, "body", "authenticated.messagethread.error.spam-body");
			}

		}

	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);

	}

}
