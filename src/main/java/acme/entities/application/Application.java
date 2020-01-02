
package acme.entities.application;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Application extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Column(unique = true)
	@NotBlank
	//	@Pattern(regexp = "^[a-zA-Z]{4}[-][a-zA-Z]{4}[:][a-zA-Z]{4}$", message = "application.pattern.error")
	private String				reference;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				moment;

	@NotBlank
	@Pattern(regexp = "^(pending)|(accepted)|(rejected)$", message = "Error")
	private String				status;

	@NotBlank
	@Column(length = 1024)
	private String				skills;

	@NotBlank
	@Column(length = 1024)
	private String				statement;

	@NotBlank
	@Column(length = 1024)
	private String				qualifications;

	private String				justification;

	//	Relationships -------------------------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Worker				worker;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Job					job;

}
