
package acme.entities.messages;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.messagethreads.Messagethread;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Message extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				moment;

	@NotBlank
	private String				tags;

	@NotBlank
	@Column(length = 1024)
	private String				body;

	//Relationships

	@NotNull
	@ManyToOne
	private Messagethread		messageThread;


	@Transient
	public String getThreadTitle() {
		return this.messageThread.getTitle();
	}

	@Transient
	public Integer getThreadId() {
		return this.messageThread.getId();
	}
}
