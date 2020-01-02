
package acme.features.administrator.investorsrecords;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.investorsrecords.Investorsrecords;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/investorsrecords/")
public class AdministratorInvestorsrecordsController extends AbstractController<Administrator, Investorsrecords> {

	@Autowired
	private AdministratorInvestorsrecordsListService	listService;

	@Autowired
	private AdministratorInvestorsrecordsShowService	showService;

	@Autowired
	private AdministratorInvestorsrecordsCreateService	createService;

	@Autowired
	private AdministratorInvestorsrecordsUpdateService	updateService;

	@Autowired
	private AdministratorInvestorsrecordsDeleteService	deleteService;


	//Constructors

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
