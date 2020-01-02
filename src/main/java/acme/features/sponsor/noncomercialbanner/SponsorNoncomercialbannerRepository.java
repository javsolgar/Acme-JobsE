
package acme.features.sponsor.noncomercialbanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.noncomercialbanner.Noncomercialbanner;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorNoncomercialbannerRepository extends AbstractRepository {

	@Query("select a from Noncomercialbanner a where a.id=?1")
	Noncomercialbanner findOneById(int id);

	@Query("select a from Noncomercialbanner a where a.sponsor.id =?1")
	Collection<Noncomercialbanner> findManyBySponsor(int idSponsor);

	@Query("select s from Sponsor s where s.userAccount.id = ?1")
	Sponsor findOneSponsorByUserAccountId(int id);
}
