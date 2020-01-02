
package acme.features.anonymous.investorsrecords;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investorsrecords.Investorsrecords;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousInvestorsrecordsRepository extends AbstractRepository {

	@Query("select a from Investorsrecords a where a.id = ?1")
	Investorsrecords findOneById(int id);

	@Query("select a from Investorsrecords a")
	Collection<Investorsrecords> findManyAll();

}
