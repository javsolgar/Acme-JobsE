
package acme.features.authenticated.participates;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messagethreads.Messagethread;
import acme.entities.participates.Participates;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedParticipatesCreateService implements AbstractCreateService<Authenticated, Participates> {

	@Autowired
	private AuthenticatedParticipatesRepository repository;


	@Override
	public boolean authorise(final Request<Participates> request) {
		assert request != null;
		boolean result = false;
		Messagethread mt = this.repository.findMTById(request.getModel().getInteger("mtid"));
		Authenticated creator = mt.getCreator();
		if (creator.getId() == request.getPrincipal().getActiveRoleId()) {
			result = true;
		}
		return result;
	}

	@Override
	public void bind(final Request<Participates> request, final Participates entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "messagethread", "authenticated");

	}

	@Override
	public void unbind(final Request<Participates> request, final Participates entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "messagethread");

	}

	@Override
	public Participates instantiate(final Request<Participates> request) {
		Participates result = new Participates();
		result.setMessagethread(this.repository.findMTById(request.getModel().getInteger("mtid")));
		Authenticated a = new Authenticated();
		result.setAuthenticated(a);
		return result;
	}

	@Override
	public void validate(final Request<Participates> request, final Participates entity, final Errors errors) {
		boolean hasUsernames, existUsers, containUser;
		hasUsernames = !request.getModel().getString("userName").isEmpty();
		errors.state(request, hasUsernames, "userName", "authenticated.participates.error.users-must-not-be-null");

		if (hasUsernames) {
			existUsers = this.existAllUsernames(request.getModel().getString("userName"));
			errors.state(request, existUsers, "userName", "authenticated.participates.error.not-found-user");

			if (existUsers) {
				containUser = false;
				String[] usuarios = request.getModel().getString("userName").split(",");
				Collection<Participates> participantes = this.repository.findParticipatesByMTId(request.getModel().getInteger("mtid"));
				Collection<String> ses = participantes.stream().map(x -> x.getAuthenticated().getUserAccount().getUsername()).collect(Collectors.toList());
				for (String s : usuarios) {
					if (ses.contains(s.trim())) {
						containUser = true;
						break;
					}
				}
				errors.state(request, !containUser, "userName", "authenticated.participates.error.repeated-users");
			}
		}

	}

	@Override
	public void create(final Request<Participates> request, final Participates entity) {
		assert request != null;
		assert entity != null;

		String[] usuarios = request.getModel().getString("userName").split(",");
		for (String s : usuarios) {
			Participates p = new Participates();
			p.setMessagethread(this.repository.findMTById(request.getModel().getInteger("mtid")));
			p.setAuthenticated(this.repository.findUserByUserName(s.trim()).getRole(Authenticated.class));
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
