
package acme.features.administrator.companyrecords;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.companyrecords.Companyrecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/companyrecord/")
public class AdministratorCompanyRecordsController extends AbstractController<Administrator, Companyrecord> {

	@Autowired
	private AdministratorCompanyRecordsListService		listService;

	@Autowired
	private AdministratorCompanyRecordsShowService		showService;

	@Autowired
	private AdministratorCompanyRecordsCreateService	createService;

	@Autowired
	private AdministratorCompanyRecordsUpdateService	updateService;

	@Autowired
	private AdministratorCompanyRecordsDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}
}
