
package acme.features.auditor.auditrecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditrecord.Auditrecord;
import acme.entities.jobs.Job;
import acme.entities.participatein.Participatein;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditrecordRepository extends AbstractRepository {

	@Query("select at from Auditrecord at where at.id = ?1")
	Auditrecord findOneAuditrecordById(int id);

	@Query("select at from Auditrecord at where at.job.id =?1 and at.isFinalMode=true")
	Collection<Auditrecord> findManyByJobId(int jobId);

	@Query("select at from Auditrecord at where at.auditor.id =?1")
	Collection<Auditrecord> findManyByAuditor(int auditorId);

	@Query("select j from Job j where j.id = ?1")
	Job findJobByRef(int id);

	@Query("select au from Auditor au where au.id = ?1")
	Auditor findAuditorById(int id);

	@Query("select at from Auditrecord at where at.job.id =?1")
	Collection<Auditrecord> findManyByJobIdAll(int jobId);

	@Query("select p from Participatein p where p.job.id =?1")
	Participatein getParticipateinByJobId(int job);

}
