
package acme.features.worker.job;

import java.util.Calendar;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class WorkerJobListService implements AbstractListService<Worker, Job> {

	@Autowired
	private WorkerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline");

	}

	@Override
	public Collection<Job> findMany(final Request<Job> request) {
		assert request != null;
		Collection<Job> result;
		result = this.repository.findManyAll();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);

		return result.stream().filter(x -> !(x.getDeadline().compareTo(calendar.getTime()) < 0)).collect(Collectors.toList());
	}

}
