
package acme.features.administrator.investorsrecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investorsrecords.Investorsrecords;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractDeleteService;

@Service
public class AdministratorInvestorsrecordsDeleteService implements AbstractDeleteService<Administrator, Investorsrecords> {

	@Autowired
	private AdministratorInvestorsrecordsRepository repository;


	@Override
	public boolean authorise(final Request<Investorsrecords> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Investorsrecords> request, final Investorsrecords entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Investorsrecords> request, final Investorsrecords entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "sector", "statement", "numberStars");

	}

	@Override
	public void validate(final Request<Investorsrecords> request, final Investorsrecords entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public Investorsrecords findOne(final Request<Investorsrecords> request) {
		assert request != null;

		Investorsrecords result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void delete(final Request<Investorsrecords> request, final Investorsrecords entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
