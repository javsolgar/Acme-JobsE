
package acme.features.sponsor.comercialbanner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.comercialbanner.Comercialbanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class SponsorComercialbannerListService implements AbstractListService<Sponsor, Comercialbanner> {

	@Autowired
	private SponsorComercialbannerRepository repository;


	@Override
	public boolean authorise(final Request<Comercialbanner> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Comercialbanner> request, final Comercialbanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "urlPicture", "slogan", "urlTarget", "creditNumber");
	}

	@Override
	public Collection<Comercialbanner> findMany(final Request<Comercialbanner> request) {
		assert request != null;

		Collection<Comercialbanner> result;
		Principal principal;

		principal = request.getPrincipal();

		result = this.repository.findManyBySponsor(principal.getActiveRoleId());
		return result;
	}

}
