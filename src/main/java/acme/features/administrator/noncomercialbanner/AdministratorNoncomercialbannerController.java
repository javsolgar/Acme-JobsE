
package acme.features.administrator.noncomercialbanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.noncomercialbanner.Noncomercialbanner;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/noncomercialbanner/")
public class AdministratorNoncomercialbannerController extends AbstractController<Administrator, Noncomercialbanner> {

	@Autowired
	private AdministratorNoncomercialbannerListService		listService;

	@Autowired
	private AdministratorNoncomercialbannerShowService		showService;

	@Autowired
	private AdministratorNoncomercialbannerCreateService	createService;

	@Autowired
	private AdministratorNoncomercialbannerUpdateService	updateService;

	@Autowired
	private AdministratorNoncomercialbannerDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}
}
