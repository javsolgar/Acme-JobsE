
package acme.entities.application;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

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

	@Column(length = 1024)
	private String				answer;

	@URL
	private String				symbol;

	@Pattern(regexp = "^((?=(?:.*\\p{L}){1,})(?=(?:.*\\p{N}){1,})(?=(?:.*\\p{P}){1,}).{10,})?$", message = "Error")
	private String				password;

	private boolean				hasAnswer;

	private boolean				hasPassword;

	private boolean				hasBeenProtected;

	//private boolean				includeXXXXinXXXX;


	//Derivated atributes --------------------------------------------------------------------

	@Transient
	public boolean getHasRolenta() {
		return this.job.isHasRolenta();
	}


	//	Relationships -------------------------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Worker	worker;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Job		job;

}
