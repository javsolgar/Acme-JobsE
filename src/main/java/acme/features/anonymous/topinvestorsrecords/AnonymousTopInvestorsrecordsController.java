
package acme.features.anonymous.topinvestorsrecords;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.investorsrecords.Investorsrecords;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/topinvestorsrecords/")
public class AnonymousTopInvestorsrecordsController extends AbstractController<Anonymous, Investorsrecords> {

	@Autowired
	private AnonymousTopInvestorsrecordsListService	listService;

	@Autowired
	private AnonymousTopInvestorsrecordsShowService	showService;


	//Constructors

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
