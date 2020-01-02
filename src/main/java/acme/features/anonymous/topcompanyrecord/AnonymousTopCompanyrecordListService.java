
package acme.features.anonymous.topcompanyrecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companyrecords.Companyrecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousTopCompanyrecordListService implements AbstractListService<Anonymous, Companyrecord> {

	@Autowired
	private AnonymousTopCompanyrecordRepository repository;


	@Override
	public boolean authorise(final Request<Companyrecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Companyrecord> request, final Companyrecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "sector", "website", "email");

	}

	@Override
	public Collection<Companyrecord> findMany(final Request<Companyrecord> request) {
		assert request != null;

		Collection<Companyrecord> result;

		result = this.repository.findManyAll();
		return result;
	}

}
