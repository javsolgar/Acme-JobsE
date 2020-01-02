
package acme.entities.descriptor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.jobs.Job;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Descriptor extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(length = 1024)
	private String				description;

	//relationships

	@NotNull
	@OneToOne
	private Job					job;


	@Transient
	public String getJobTitle() {
		return this.job.getTitle();
	}

	@Transient
	public Integer getJobId() {

		return this.job.getId();
	}

}
