
package acme.entities.banner;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Banner extends DomainEntity {

	// Serialization Identify ------------------------------------------------------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//	Atributes	----------------------------------------------------------------------------------------------------------

	@NotBlank
	@URL
	private String				urlPicture;

	@NotBlank
	private String				slogan;

	@NotBlank
	@URL
	private String				urlTarget;

	@NotNull
	private boolean				finalMode;

}
