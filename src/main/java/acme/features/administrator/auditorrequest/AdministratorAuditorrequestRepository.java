
package acme.features.administrator.auditorrequest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditorrequest.Auditorrequest;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorAuditorrequestRepository extends AbstractRepository {

	@Query("select ar from Auditorrequest ar")
	Collection<Auditorrequest> findAllAuditorsRequest();

	@Query("select ar from Auditorrequest ar where ar.id=?1")
	Auditorrequest findOneAuditorRequestById(int id);

}
