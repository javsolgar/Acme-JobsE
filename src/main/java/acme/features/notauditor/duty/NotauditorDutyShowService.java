
package acme.features.notauditor.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class NotauditorDutyShowService implements AbstractShowService<Auditor, Duty> {

	@Autowired
	private NotauditorDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		boolean res;
		Integer id;
		Duty result;

		id = request.getModel().getInteger("id");
		result = this.repository.findDutyById(id);

		res = result.getDescriptor().getJob().isFinalMode();

		return res;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "percentage", "jobTitle", "jobId");

	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		Duty result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findDutyById(id);
		return result;
	}

}
