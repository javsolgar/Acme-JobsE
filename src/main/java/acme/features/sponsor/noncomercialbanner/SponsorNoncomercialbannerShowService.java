
package acme.features.sponsor.noncomercialbanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.noncomercialbanner.Noncomercialbanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class SponsorNoncomercialbannerShowService implements AbstractShowService<Sponsor, Noncomercialbanner> {

	@Autowired
	private SponsorNoncomercialbannerRepository repository;


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

		res = sponsor.getId() == banner.getSponsor().getId() || banner.isFinalMode();

		return res;
	}

	@Override
	public void unbind(final Request<Noncomercialbanner> request, final Noncomercialbanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "urlPicture", "slogan", "urlTarget", "jingle", "finalMode");
	}

	@Override
	public Noncomercialbanner findOne(final Request<Noncomercialbanner> request) {
		assert request != null;

		Noncomercialbanner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
