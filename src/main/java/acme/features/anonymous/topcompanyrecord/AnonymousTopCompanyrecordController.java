
package acme.features.anonymous.topcompanyrecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.companyrecords.Companyrecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/topcompanyrecord/")
public class AnonymousTopCompanyrecordController extends AbstractController<Anonymous, Companyrecord> {

	@Autowired
	private AnonymousTopCompanyrecordListService	listService;

	@Autowired
	private AnonymousTopCompanyrecordShowService	showService;


	//Constructors

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
