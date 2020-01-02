
package acme.features.sponsor.comercialbanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.comercialbanner.Comercialbanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class SponsorComercialbannerShowService implements AbstractShowService<Sponsor, Comercialbanner> {

	@Autowired
	private SponsorComercialbannerRepository repository;


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

		res = sponsor.getId() == banner.getSponsor().getId() || banner.isFinalMode();

		return res;
	}

	@Override
	public void unbind(final Request<Comercialbanner> request, final Comercialbanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "urlPicture", "slogan", "urlTarget", "creditNumber", "finalMode");
	}

	@Override
	public Comercialbanner findOne(final Request<Comercialbanner> request) {
		assert request != null;

		Comercialbanner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
