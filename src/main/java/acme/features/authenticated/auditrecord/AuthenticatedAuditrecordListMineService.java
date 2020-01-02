
package acme.features.authenticated.auditrecord;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditrecord.Auditrecord;
import acme.entities.jobs.Job;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedAuditrecordListMineService implements AbstractListService<Authenticated, Auditrecord> {

	@Autowired
	private AuthenticatedAuditrecordRepository repository;


	@Override
	public boolean authorise(final Request<Auditrecord> request) {
		assert request != null;

		boolean res;
		Integer id;
		Job result;
		Calendar calendar = Calendar.getInstance();

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		res = result.isFinalMode() && result.getDeadline().after(calendar.getTime());
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
