
package acme.features.sponsor.comercialbanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.comercialbanner.Comercialbanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class SponsorComercialbannerDeleteService implements AbstractDeleteService<Sponsor, Comercialbanner> {

	// Internal state ------------------------------------------------------------------------------------------------

	@Autowired
	private SponsorComercialbannerRepository repository;


	// AbstractDeleteService<Sponsor, Comercialbanner> interface ------------------------------------------------------

	@Override
	public boolean authorise(final Request<Comercialbanner> request) {
		assert request != null;

		Integer id;
		boolean res;
		Principal principal;
		Comercialbanner banner;
		Sponsor sponsor;

		id = request.getModel().getInteger("id");

		principal = request.getPrincipal();

		sponsor = this.repository.findOneSponsorByUserAccountId(principal.getAccountId());

		banner = this.repository.findOneById(id);

		res = sponsor.getId() == banner.getSponsor().getId();

		return res;
	}

	@Override
	public void bind(final Request<Comercialbanner> request, final Comercialbanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Comercialbanner> request, final Comercialbanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "urlPicture", "slogan", "urlTarget", "finalMode");

	}

	@Override
	public Comercialbanner findOne(final Request<Comercialbanner> request) {
		assert request != null;

		Comercialbanner result;
		Integer id;

		id = request.getModel().getInteger("id");

		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<Comercialbanner> request, final Comercialbanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<Comercialbanner> request, final Comercialbanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
