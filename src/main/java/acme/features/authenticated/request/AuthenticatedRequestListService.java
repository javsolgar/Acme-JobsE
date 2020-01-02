
package acme.features.authenticated.request;

import java.util.Calendar;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.request.Request;
import acme.framework.components.Model;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedRequestListService implements AbstractListService<Authenticated, Request> {

	//	 Internal  state	-------------------------------------------------------------------------

	@Autowired
	private AuthenticatedRequestRepository repository;


	//	AbstractListService<Authenticated, Announcement> interface ----------------------------------

	@Override
	public boolean authorise(final acme.framework.components.Request<Request> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final acme.framework.components.Request<Request> request, final Request entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "reward", "moment", "deadLine", "ticker");
	}

	@Override
	public Collection<Request> findMany(final acme.framework.components.Request<Request> request) {
		assert request != null;

		Collection<Request> result;

		result = this.repository.findManyAll();
		Calendar calendar = Calendar.getInstance();
		return result.stream().filter(x -> x.getDeadLine().compareTo(calendar.getTime()) > 0).collect(Collectors.toList());

	}

}
