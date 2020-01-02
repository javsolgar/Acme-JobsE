
package acme.entities.duties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.descriptor.Descriptor;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Duty extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@NotBlank
	@Column(length = 1024)
	private String				description;

	@NotNull
	private Double				percentage;


	@Transient
	public String getJobTitle() {
		return this.descriptor.getJob().getTitle();
	}

	@Transient
	public Integer getJobId() {
		return this.descriptor.getJob().getId();
	}

	@Transient
	public boolean getFinalMode() {
		return this.descriptor.getJob().isFinalMode();
	}


	//relationships

	@NotNull
	@ManyToOne
	private Descriptor descriptor;

}
