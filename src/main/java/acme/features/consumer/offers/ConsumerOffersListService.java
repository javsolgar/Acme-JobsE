
package acme.features.consumer.offers;

import java.util.Calendar;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offers;
import acme.entities.roles.Consumer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class ConsumerOffersListService implements AbstractListService<Consumer, Offers> {

	//	 Internal  state	-------------------------------------------------------------------------

	@Autowired
	private ConsumerOffersRepository repository;


	//	AbstractListService<Authenticated, Offers> interface ----------------------------------

	@Override
	public boolean authorise(final Request<Offers> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Offers> request, final Offers entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "deadline", "description", "lowerRange", "majorRange");
	}

	@Override
	public Collection<Offers> findMany(final Request<Offers> request) {
		assert request != null;

		Collection<Offers> result;
		result = this.repository.findManyAll();
		Calendar calendar = Calendar.getInstance();
		return result.stream().filter(x -> x.getDeadline().compareTo(calendar.getTime()) > 0).collect(Collectors.toList());
	}

}
