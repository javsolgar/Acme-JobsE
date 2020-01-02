
package acme.features.sponsor.comercialbanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.comercialbanner.Comercialbanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/sponsor/comercialbanner/")
public class SponsorComercialbannerController extends AbstractController<Sponsor, Comercialbanner> {

	@Autowired
	private SponsorComercialbannerListService	listService;

	@Autowired
	private SponsorComercialbannerShowService	showService;

	@Autowired
	private SponsorComercialbannerCreateService	createService;

	@Autowired
	private SponsorComercialbannerUpdateService	updateService;

	@Autowired
	private SponsorComercialbannerDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}
}
