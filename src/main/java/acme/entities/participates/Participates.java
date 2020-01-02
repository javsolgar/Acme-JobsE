
package acme.entities.participates;

import java.beans.Transient;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import acme.entities.messagethreads.Messagethread;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Participates extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotNull
	@ManyToOne
	private Messagethread		messagethread;

	@NotNull
	@ManyToOne
	private Authenticated		authenticated;


	@Transient
	public String getUserName() {
		return this.authenticated.getUserAccount().getUsername();
	}

}
