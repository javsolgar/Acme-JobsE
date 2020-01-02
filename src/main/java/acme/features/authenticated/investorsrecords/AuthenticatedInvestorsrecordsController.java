
package acme.features.authenticated.investorsrecords;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.investorsrecords.Investorsrecords;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/investorsrecords/")
public class AuthenticatedInvestorsrecordsController extends AbstractController<Authenticated, Investorsrecords> {

	@Autowired
	private AuthenticatedInvestorsrecordsListService	listService;

	@Autowired
	private AuthenticatedInvestorsrecordsShowService	showService;


	//Constructors

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
