
package acme.features.provider.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.request.Request;
import acme.entities.roles.Provider;
import acme.framework.components.Model;
import acme.framework.services.AbstractShowService;

@Service
public class ProviderRequestShowService implements AbstractShowService<Provider, Request> {

	//	 Internal  state	-------------------------------------------------------------------------
	@Autowired
	private ProviderRequestRepository repository;


	//	AbstractShowService<provider, Request> Interface --------------------------------------

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
	public Request findOne(final acme.framework.components.Request<Request> request) {
		assert request != null;

		Request result;

		Integer id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
