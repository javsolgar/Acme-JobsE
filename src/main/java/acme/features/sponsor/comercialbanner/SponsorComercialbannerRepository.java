
package acme.features.sponsor.comercialbanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.comercialbanner.Comercialbanner;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorComercialbannerRepository extends AbstractRepository {

	@Query("select a from Comercialbanner a where a.id=?1")
	Comercialbanner findOneById(int id);

	@Query("select a from Comercialbanner a where a.sponsor.id =?1")
	Collection<Comercialbanner> findManyBySponsor(int idSponsor);

	@Query("select s from Sponsor s where s.userAccount.id = ?1")
	Sponsor findOneSponsorByUserAccountId(int id);

}
