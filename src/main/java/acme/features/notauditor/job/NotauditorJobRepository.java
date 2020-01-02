
package acme.features.notauditor.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface NotauditorJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select job from Participatein p where p.job.finalMode=true and p.job.id not in (select job.id from Participatein p2 where p2.auditor.id=?1)")
	Collection<Job> findNotJobs(int AuditorId);
}
