
package acme.features.authenticated.challenge;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.challenge.Challenge;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedChallengeRepository extends AbstractRepository {

	@Query("Select a from Challenge a")
	Collection<Challenge> findManyAll();

	@Query("Select a from Challenge a where a.id=?1")
	Challenge findOneById(Integer id);

}
