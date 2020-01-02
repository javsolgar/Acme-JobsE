
package acme.features.authenticated.messagethread;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messagethreads.Messagethread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessagethreadListMineService implements AbstractListService<Authenticated, Messagethread> {

	@Autowired
	private AuthenticatedMessagethreadRepository repository;


	@Override
	public boolean authorise(final Request<Messagethread> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Messagethread> request, final Messagethread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment");

	}

	@Override
	public Collection<Messagethread> findMany(final Request<Messagethread> request) {
		assert request != null;

		Collection<Messagethread> result;

		Principal principal;

		principal = request.getPrincipal();

		result = this.repository.findMTByUserId(principal.getActiveRoleId());

		return result;
	}

}
