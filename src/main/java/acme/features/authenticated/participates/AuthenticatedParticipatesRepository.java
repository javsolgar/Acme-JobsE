
package acme.features.authenticated.participates;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messagethreads.Messagethread;
import acme.entities.participates.Participates;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedParticipatesRepository extends AbstractRepository {

	@Query("select authenticated from Participates p where p.messagethread.id=?1")
	Collection<Authenticated> findUsersByMTId(int id);

	@Query("select p from Participates p where p.messagethread.id=?1")
	Collection<Participates> findParticipatesByMTId(int id);

	@Query("select p from Participates p where p.id=?1")
	Participates findParticipatesById(int id);

	@Query("select m from Messagethread m where m.id=?1")
	Messagethread findMTById(int id);

	@Query("select u from UserAccount u where u.username = ?1")
	UserAccount findUserByUserName(String username);
}
