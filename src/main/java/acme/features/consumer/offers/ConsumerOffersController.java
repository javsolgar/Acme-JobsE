
package acme.features.consumer.offers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.offers.Offers;
import acme.entities.roles.Consumer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/consumer/offers/")
public class ConsumerOffersController extends AbstractController<Consumer, Offers> {

	//	Internal  state 	---------------------------------------------------------------------------

	@Autowired
	private ConsumerOffersListService	listService;

	@Autowired
	private ConsumerOffersShowService	showService;

	@Autowired
	private ConsumerOffersCreateService	createService;


	//	Constructors	-------------------------------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
