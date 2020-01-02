
package acme.features.authenticated.offers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.offers.Offers;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/offers/")
public class AuthenticatedOffersController extends AbstractController<Authenticated, Offers> {

	//	Internal  state 	---------------------------------------------------------------------------

	@Autowired
	private AuthenticatedOffersListService	listService;

	@Autowired
	private AuthenticatedOffersShowService	showService;


	//	Constructors	-------------------------------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
