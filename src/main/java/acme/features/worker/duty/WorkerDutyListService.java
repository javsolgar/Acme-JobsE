
package acme.features.worker.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptor.Descriptor;
import acme.entities.duties.Duty;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class WorkerDutyListService implements AbstractListService<Worker, Duty> {

	@Autowired
	private WorkerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		boolean res;
		Integer id;
		Descriptor result;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneByDescriptorId(id);

		res = result.getJob().isFinalMode();

		return res;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		request.unbind(entity, model, "title", "description", "percentage");

	}

	@Override
	public Collection<Duty> findMany(final Request<Duty> request) {
		assert request != null;
		Collection<Duty> result;

		result = this.repository.findManyByDescriptorId(request.getModel().getInteger("id"));

		return result;
	}

}
