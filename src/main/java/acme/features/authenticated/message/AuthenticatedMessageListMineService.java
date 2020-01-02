
package acme.features.authenticated.message;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageListMineService implements AbstractListService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


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
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "id");

	}

	@Override
	public Collection<Message> findMany(final Request<Message> request) {
		assert request != null;

		Collection<Message> result = new ArrayList<>();

		Integer id = request.getModel().getInteger("id");
		result = this.repository.findMessagebyMessagethread(id);

		return result;
	}

}
