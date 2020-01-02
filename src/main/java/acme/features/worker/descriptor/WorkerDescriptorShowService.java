
package acme.features.worker.descriptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptor.Descriptor;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerDescriptorShowService implements AbstractShowService<Worker, Descriptor> {

	@Autowired
	private WorkerDescriptorRepository repository;


	@Override
	public boolean authorise(final Request<Descriptor> request) {
		assert request != null;
		boolean res;
		Integer id;
		Descriptor result;

		id = request.getModel().getInteger("jobId");
		result = this.repository.findOneByJobId(id);

		res = result.getJob().isFinalMode();
		return res;
	}

	@Override
	public void unbind(final Request<Descriptor> request, final Descriptor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "description", "job.title", "jobTitle", "jobId");

	}

	@Override
	public Descriptor findOne(final Request<Descriptor> request) {
		assert request != null;
		Descriptor result;
		int id;

		id = request.getModel().getInteger("jobId");
		result = this.repository.findOneByJobId(id);
		return result;
	}

}
