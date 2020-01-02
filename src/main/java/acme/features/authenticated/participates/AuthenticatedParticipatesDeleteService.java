
package acme.features.authenticated.participates;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participates.Participates;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedParticipatesDeleteService implements AbstractDeleteService<Authenticated, Participates> {

	@Autowired
	private AuthenticatedParticipatesRepository repository;


	@Override
	public boolean authorise(final Request<Participates> request) {
		assert request != null;
		boolean result = false;
		Participates p = this.repository.findParticipatesById(request.getModel().getInteger("id"));
		Authenticated creator = p.getMessagethread().getCreator();
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

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Participates> request, final Participates entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "userName");

	}

	@Override
	public Participates findOne(final Request<Participates> request) {
		assert request != null;
		Participates result = this.repository.findParticipatesById(request.getModel().getInteger("id"));
		return result;
	}

	@Override
	public void validate(final Request<Participates> request, final Participates entity, final Errors errors) {
		boolean participatesIn = false;
		String usuario = request.getModel().getString("userName");
		Participates p = this.repository.findParticipatesById(request.getModel().getInteger("id"));
		Collection<Participates> participantes = this.repository.findParticipatesByMTId(p.getMessagethread().getId());
		Collection<String> ses = participantes.stream().map(x -> x.getAuthenticated().getUserAccount().getUsername()).collect(Collectors.toList());
		if (ses.contains(usuario)) {
			participatesIn = true;

		}
		errors.state(request, participatesIn, "userName", "authenticated.participates.error.user-not-in-thread");

	}

	@Override
	public void delete(final Request<Participates> request, final Participates entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
