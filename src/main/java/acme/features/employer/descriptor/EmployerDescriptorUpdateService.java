
package acme.features.employer.descriptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.descriptor.Descriptor;
import acme.entities.roles.Employer;
import acme.features.utiles.Spamfilter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerDescriptorUpdateService implements AbstractUpdateService<Employer, Descriptor> {

	// Internal State --------------------------------------------------------------------------------------

	@Autowired
	private EmployerDescriptorRepository repository;

	// AbstractUpdateService<Employer, Descriptor> interface -----------------------------------------------


	@Override
	public boolean authorise(final Request<Descriptor> request) {
		assert request != null;

		Descriptor descriptor;
		Principal principal;
		int idPrincipal, id;
		boolean res;

		principal = request.getPrincipal();
		idPrincipal = principal.getAccountId();
		id = request.getModel().getInteger("id");
		descriptor = this.repository.findOneById(id);

		res = idPrincipal == descriptor.getJob().getEmployer().getUserAccount().getId();

		return res;
	}

	@Override
	public void bind(final Request<Descriptor> request, final Descriptor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "job");

	}

	@Override
	public void unbind(final Request<Descriptor> request, final Descriptor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "description");

	}

	@Override
	public Descriptor findOne(final Request<Descriptor> request) {
		assert request != null;

		Descriptor result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Descriptor> request, final Descriptor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Configuration configuration;
		String spamWords;
		Double spamThreshold;
		boolean hasDescription, isNotSpam;

		hasDescription = entity.getDescription() != null;
		errors.state(request, hasDescription, "description", "employer.descriptor.error.must-have-description");

		if (!errors.hasErrors("description")) {
			if (hasDescription) {

				configuration = this.repository.findConfiguration();
				spamWords = configuration.getSpamWords();
				spamThreshold = configuration.getSpamThreshold();

				isNotSpam = Spamfilter.spamThreshold(entity.getDescription(), spamWords, spamThreshold);
				errors.state(request, !isNotSpam, "description", "employer.descriptor.error.must-not-be-spam");
			}
		}

	}

	@Override
	public void update(final Request<Descriptor> request, final Descriptor entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
