
package acme.entities.participatein;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Participatein extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@Valid
	@ManyToOne
	private Job					job;

	@Valid
	@ManyToOne
	private Auditor				auditor;

}
