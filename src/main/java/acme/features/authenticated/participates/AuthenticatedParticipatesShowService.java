
package acme.features.authenticated.participates;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participates.Participates;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedParticipatesShowService implements AbstractShowService<Authenticated, Participates> {

	@Autowired
	private AuthenticatedParticipatesRepository repository;


	@Override
	public boolean authorise(final Request<Participates> request) {
		assert request != null;
		boolean result = false;
		Participates p = this.repository.findParticipatesById(request.getModel().getInteger("id"));
		Collection<Authenticated> users = this.repository.findUsersByMTId(p.getMessagethread().getId());
		for (Authenticated a : users) {

			if (request.getPrincipal().getActiveRoleId() == a.getId()) {
				result = true;
			}
		}
		return result;

	}

	@Override
	public void unbind(final Request<Participates> request, final Participates entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "userName", "messagethread");

	}

	@Override
	public Participates findOne(final Request<Participates> request) {
		assert request != null;
		Participates result = this.repository.findParticipatesById(request.getModel().getInteger("id"));
		return result;
	}

}
