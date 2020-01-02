
package acme.features.authenticated.messagethread;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.messagethreads.Messagethread;
import acme.entities.participates.Participates;
import acme.features.utiles.ConfigurationRepository;
import acme.features.utiles.Spamfilter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessagethreadCreateService implements AbstractCreateService<Authenticated, Messagethread> {

	// Internal state ----------------------------------------------------------------------------------------------------

	@Autowired
	private AuthenticatedMessagethreadRepository	repository;

	@Autowired
	private ConfigurationRepository					configurationRepository;


	// AbstractCreateService<Authenticated, Messagethread> interface ------------------------------------------------------

	@Override
	public boolean authorise(final Request<Messagethread> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Messagethread> request, final Messagethread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Messagethread> request, final Messagethread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "usernames", "creator");

	}

	@Override
	public Messagethread instantiate(final Request<Messagethread> request) {
		assert request != null;

		Date moment;

		Messagethread result = new Messagethread();
		moment = new Date(System.currentTimeMillis() - 1);

		result.setMoment(moment);
		result.setUsernames("");
		Authenticated creator = this.repository.findUserByUserName(request.getPrincipal().getUsername()).getRole(Authenticated.class);
		result.setCreator(creator);

		return result;
	}

	@Override
	public void validate(final Request<Messagethread> request, final Messagethread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean hasTitle, hasSpamTitle, hasUsernames, existAllUsernames;

		Configuration configuration;
		String spamWords;
		Double spamThreshold;

		configuration = this.configurationRepository.findConfiguration();
		spamWords = configuration.getSpamWords();
		spamThreshold = configuration.getSpamThreshold();

		if (!errors.hasErrors("title")) {
			hasTitle = entity.getTitle() != null;
			errors.state(request, hasTitle, "title", "authenticated.messagethread.error.must-have-title");

			if (hasTitle) {

				hasSpamTitle = Spamfilter.spamThreshold(entity.getTitle(), spamWords, spamThreshold);
				errors.state(request, !hasSpamTitle, "title", "authenticated.messagethread.error.must-not-have-spam-title");

			}
		}
		if (!errors.hasErrors("usernames")) {
			hasUsernames = entity.getUsernames() != null && !entity.getUsernames().equals("");
			errors.state(request, hasUsernames, "usernames", "authenticated.messagethread.error.must-have-usernames");

			if (hasUsernames) {

				existAllUsernames = this.existAllUsernames(entity.getUsernames());
				errors.state(request, existAllUsernames, "usernames", "authenticated.messagethread.error.not-found-user");
			}
		}

	}

	@Override
	public void create(final Request<Messagethread> request, final Messagethread entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		Authenticated creator = this.repository.findUserByUserName(request.getPrincipal().getUsername()).getRole(Authenticated.class);
		entity.setCreator(creator);

		moment = new Date(System.currentTimeMillis() - 1);

		entity.setMoment(moment);

		this.repository.save(entity);

		String[] usernames = entity.getUsernames().split(",");

		for (String i : usernames) {
			Participates p = new Participates();
			p.setMessagethread(entity);
			UserAccount user = this.repository.findUserByUserName(i.trim());
			p.setAuthenticated(user.getRole(Authenticated.class));
			this.repository.save(p);

		}

	}

	private Boolean existAllUsernames(final String usernamesString) {
		Boolean res = true;
		UserAccount user;
		String[] usernames = usernamesString.split(",");
		for (String i : usernames) {
			if (!i.trim().equals("")) {
				user = this.repository.findUserByUserName(i.trim());
				if (user == null) {
					res = false;
					break;
				}
			}

		}
		return res;
	}

}
