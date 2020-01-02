
package acme.features.authenticated.auditor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditorRepository extends AbstractRepository {

	@Query("select au from Auditor au where au.userAccount.id=?1")
	Auditor findOneAuditorByUserAccountId(int id);
}
