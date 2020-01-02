
package acme.features.authenticated.messagethread;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messagethreads.Messagethread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessagethreadShowService implements AbstractShowService<Authenticated, Messagethread> {

	@Autowired
	private AuthenticatedMessagethreadRepository repository;


	@Override
	public boolean authorise(final Request<Messagethread> request) {
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
	public void unbind(final Request<Messagethread> request, final Messagethread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "usernames", "creator");
	}

	@Override
	public Messagethread findOne(final Request<Messagethread> request) {
		assert request != null;

		Messagethread result = new Messagethread();
		int id;

		String users = "";
		int i = 0;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneMessagethreadById(id);
		Collection<Authenticated> usuarios = this.repository.findUsersFromMTId(id);

		for (Authenticated us : usuarios) {
			if (i == 0) {
				if (usuarios.size() == 1) {
					users = us.getUserAccount().getUsername();
				} else {
					users = us.getUserAccount().getUsername() + ", ";
				}
			} else {
				if (i == usuarios.size() - 1) {
					users = users + us.getUserAccount().getUsername();

				} else {
					users = users + us.getUserAccount().getUsername() + ", ";
				}
			}
			i++;
		}

		/*
		 * for (Message ms : result.getMessages()) {
		 * if (a == 0) {
		 * if (result.getMessages().size() == 1) {
		 * messages = ms.getTitle();
		 * } else {
		 * messages = ms.getTitle() + ", ";
		 * }
		 * } else {
		 * if (i == result.getMessages().size() - 1) {
		 * messages = messages + ms.getTitle();
		 * } else {
		 * messages = messages + ms.getTitle() + ", ";
		 * }
		 * }
		 * a++;
		 * }
		 */

		result.setUsernames(users);
		return result;
	}

}
