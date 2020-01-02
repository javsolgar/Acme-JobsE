
package acme.entities.auditorrequest;

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

import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Auditorrequest extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	//Attributes

	@NotBlank
	private String				firm;

	@NotBlank
	private String				respStatement;

	@NotBlank
	@Column(length = 1024)
	private String				description;

	@NotNull
	@Pattern(regexp = "^(pending)|(accepted)|(rejected)$", message = "Error")
	private String				status;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				moment;

	//RelationShips

	@Valid
	@ManyToOne
	private Authenticated		user;


	// Derivated properties

	@Transient
	public String getUsername() {
		return this.user.getUserAccount().getUsername();
	}
}
