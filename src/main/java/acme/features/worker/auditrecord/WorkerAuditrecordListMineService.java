
package acme.features.worker.auditrecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditrecord.Auditrecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class WorkerAuditrecordListMineService implements AbstractListService<Worker, Auditrecord> {

	@Autowired
	private WorkerAuditrecordRepository repository;


	@Override
	public boolean authorise(final Request<Auditrecord> request) {
		assert request != null;

		boolean res;
		Integer id;
		Job result;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		res = result.isFinalMode();

		return res;
	}

	@Override
	public void unbind(final Request<Auditrecord> request, final Auditrecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "isFinalMode", "moment", "body", "job", "auditorUser");

	}

	@Override
	public Collection<Auditrecord> findMany(final Request<Auditrecord> request) {
		assert request != null;
		Collection<Auditrecord> result;

		result = this.repository.findManyByJobId(request.getModel().getInteger("id"));
		return result;
	}

}
