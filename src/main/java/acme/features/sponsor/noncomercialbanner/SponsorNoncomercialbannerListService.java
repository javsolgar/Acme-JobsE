
package acme.features.sponsor.noncomercialbanner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.noncomercialbanner.Noncomercialbanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class SponsorNoncomercialbannerListService implements AbstractListService<Sponsor, Noncomercialbanner> {

	@Autowired
	private SponsorNoncomercialbannerRepository repository;


	@Override
	public boolean authorise(final Request<Noncomercialbanner> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Noncomercialbanner> request, final Noncomercialbanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "urlPicture", "slogan", "urlTarget", "jingle");
	}

	@Override
	public Collection<Noncomercialbanner> findMany(final Request<Noncomercialbanner> request) {
		assert request != null;

		Collection<Noncomercialbanner> result;
		Principal principal;

		principal = request.getPrincipal();

		result = this.repository.findManyBySponsor(principal.getActiveRoleId());
		return result;
	}

}
