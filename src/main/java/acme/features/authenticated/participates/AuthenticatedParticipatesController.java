
package acme.features.authenticated.participates;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.participates.Participates;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/participates/")
public class AuthenticatedParticipatesController extends AbstractController<Authenticated, Participates> {

	@Autowired
	private AuthenticatedParticipatesListService	listService;

	@Autowired
	private AuthenticatedParticipatesShowService	showService;

	@Autowired
	private AuthenticatedParticipatesCreateService	createService;

	@Autowired
	private AuthenticatedParticipatesDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
