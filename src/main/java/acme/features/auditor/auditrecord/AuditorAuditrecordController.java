
package acme.features.auditor.auditrecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.auditrecord.Auditrecord;
import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/auditor/auditrecord/")
public class AuditorAuditrecordController extends AbstractController<Auditor, Auditrecord> {

	@Autowired
	private AuditorAuditrecordShowService		showService;

	@Autowired
	private AuditorAuditrecordListService		listService;

	@Autowired
	private AuditorAuditrecordListMineService	listMineService;

	@Autowired
	private AuditorAuditrecordCreateService		createService;

	@Autowired
	private AuditorAuditUpdateService			updateService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);

	}
}
