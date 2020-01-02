
package acme.features.sponsor.noncomercialbanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.noncomercialbanner.Noncomercialbanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class SponsorNonComercialbannerDeleteService implements AbstractDeleteService<Sponsor, Noncomercialbanner> {

	// Internal state ---------------------------------------------------------------------------------------------

	@Autowired
	private SponsorNoncomercialbannerRepository repository;


	// AbstractDeleteService<Sponsor, Noncomercialbanner> interface -----------------------------------------------

	@Override
	public boolean authorise(final Request<Noncomercialbanner> request) {
		assert request != null;

		Integer id;
		boolean res;
		Principal principal;
		Noncomercialbanner banner;
		Sponsor sponsor;

		id = request.getModel().getInteger("id");

		principal = request.getPrincipal();

		sponsor = this.repository.findOneSponsorByUserAccountId(principal.getAccountId());

		banner = this.repository.findOneById(id);

		res = sponsor.getId() == banner.getSponsor().getId();

		return res;
	}

	@Override
	public void bind(final Request<Noncomercialbanner> request, final Noncomercialbanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Noncomercialbanner> request, final Noncomercialbanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "urlPicture", "slogan", "urlTarget", "finalMode", "jingle");
	}

	@Override
	public Noncomercialbanner findOne(final Request<Noncomercialbanner> request) {
		Integer id;
		Noncomercialbanner result;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<Noncomercialbanner> request, final Noncomercialbanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<Noncomercialbanner> request, final Noncomercialbanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
