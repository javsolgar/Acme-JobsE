
package acme.features.authenticated.messagethread;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.messagethreads.Messagethread;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/messagethread/")
public class AuthenticatedMessagethreadController extends AbstractController<Authenticated, Messagethread> {

	@Autowired
	private AuthenticatedMessagethreadShowService		showService;

	@Autowired
	private AuthenticatedMessagethreadListMineService	listMineService;

	@Autowired
	private AuthenticatedMessagethreadCreateService		createService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
	}
}
