
package acme.features.authenticated.companyrecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.companyrecords.Companyrecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/companyrecord/")
public class AuthenticatedCompanyrecordController extends AbstractController<Authenticated, Companyrecord> {

	@Autowired
	private AuthenticatedCompanyrecordListService	listService;

	@Autowired
	private AuthenticatedCompanyrecordShowService	showService;


	//Constructors

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
