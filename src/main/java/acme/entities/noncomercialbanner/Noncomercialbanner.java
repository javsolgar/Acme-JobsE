
package acme.entities.noncomercialbanner;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.URL;

import acme.entities.banner.Banner;
import acme.entities.roles.Sponsor;
import acme.framework.entities.Administrator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Noncomercialbanner extends Banner {

	// Serialization Identify ------------------------------------------------------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//	Atributes	----------------------------------------------------------------------------------------------------------

	@URL
	private String				jingle;

	//	Relationships -------------------------------------------------------------------------

	@Valid
	@ManyToOne(optional = true)
	private Sponsor				sponsor;

	@Valid
	@ManyToOne(optional = true)
	private Administrator		administrator;

}
