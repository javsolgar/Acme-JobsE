
package acme.features.authenticated.messagethread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messages.Message;
import acme.entities.messagethreads.Messagethread;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessagethreadRepository extends AbstractRepository {

	@Query("select m from Messagethread m where m.id = ?1")
	Messagethread findOneMessagethreadById(int id);

	@Query("select m from Message m where m.messageThread.id = ?1")
	Collection<Message> findMessagebyMessagethread(int id);

	@Query("select messagethread from Participates p where p.authenticated.id=?1")
	Collection<Messagethread> findMTByUserId(int id);

	@Query("select authenticated from Participates p where p.messagethread.id=?1")
	Collection<Authenticated> findUsersFromMTId(int id);

	@Query("select u from UserAccount u where u.username = ?1")
	UserAccount findUserByUserName(String username);

}
