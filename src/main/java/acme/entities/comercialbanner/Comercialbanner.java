
package acme.entities.comercialbanner;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import acme.entities.banner.Banner;
import acme.entities.roles.Sponsor;
import acme.framework.entities.Administrator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comercialbanner extends Banner {

	// Serialization Identify ------------------------------------------------------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//	Atributes	----------------------------------------------------------------------------------------------------------

	@CreditCardNumber
	private String				creditNumber;

	@NotBlank
	private String				name;

	@NotBlank
	private String				surname;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				expiration;

	@NotBlank
	@Pattern(regexp = "^[0-9]{3}$", message = "Error")
	private String				securityCode;

	@NotBlank
	@Pattern(regexp = "^(Dinners Club)|(Visa)|(Master Card)|(American Express)$", message = "Error")
	private String				type;

	//	Relationships -------------------------------------------------------------------------

	@Valid
	@ManyToOne(optional = true)
	private Sponsor				sponsor;
	@Valid
	@ManyToOne(optional = true)
	private Administrator		administrator;
}
