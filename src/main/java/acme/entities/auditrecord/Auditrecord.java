
package acme.entities.auditrecord;

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

import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Auditrecord extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@NotNull
	private Boolean				isFinalMode;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				moment;

	@NotBlank
	@Column(length = 1024)
	private String				body;


	@Transient
	public String getJobTitle() {
		return this.job.getTitle();
	}

	@Transient
	public String getJobId() {
		return this.job.getTitle();
	}

	@Transient
	public String getAuditorUser() {
		return this.auditor.getUserAccount().getUsername();
	}
	//	Relationships -------------------------------------------------------------------------


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Job		job;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Auditor	auditor;

}
