
package acme.features.authenticated.auditrecord;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditrecord.Auditrecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAuditrecordShowService implements AbstractShowService<Authenticated, Auditrecord> {

	@Autowired
	private AuthenticatedAuditrecordRepository repository;


	@Override
	public boolean authorise(final Request<Auditrecord> request) {
		assert request != null;

		boolean res;
		Integer id;
		Auditrecord result;
		Calendar calendar = Calendar.getInstance();

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAuditrecordById(id);

		res = result.getIsFinalMode() && result.getJob().getDeadline().after(calendar.getTime());
		return res;
	}

	@Override
	public void unbind(final Request<Auditrecord> request, final Auditrecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "isFinalMode", "moment", "body", "job", "jobTitle", "auditorUser");
	}

	@Override
	public Auditrecord findOne(final Request<Auditrecord> request) {
		assert request != null;

		Auditrecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAuditrecordById(id);
		return result;
	}

}
