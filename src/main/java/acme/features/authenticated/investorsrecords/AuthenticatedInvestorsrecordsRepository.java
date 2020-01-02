
package acme.features.authenticated.investorsrecords;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investorsrecords.Investorsrecords;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedInvestorsrecordsRepository extends AbstractRepository {

	@Query("select a from Investorsrecords a where a.id = ?1")
	Investorsrecords findOneById(int id);

	@Query("select a from Investorsrecords a")
	Collection<Investorsrecords> findManyAll();

	@Query("select count(*) from Investorsrecords a")
	Integer countInvestorsRecords();

}
