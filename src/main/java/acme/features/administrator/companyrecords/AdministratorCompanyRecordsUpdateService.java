
package acme.features.administrator.companyrecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companyrecords.Companyrecord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorCompanyRecordsUpdateService implements AbstractUpdateService<Administrator, Companyrecord> {

	@Autowired
	private AdministratorCompanyRecordsRepository repository;


	@Override
	public boolean authorise(final Request<Companyrecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Companyrecord> request, final Companyrecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Companyrecord> request, final Companyrecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "sector", "ceo", "description", "website", "phone", "email", "incorporated", "numberStars");
	}

	@Override
	public Companyrecord findOne(final Request<Companyrecord> request) {
		assert request != null;
		Companyrecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<Companyrecord> request, final Companyrecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Companyrecord> request, final Companyrecord entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
