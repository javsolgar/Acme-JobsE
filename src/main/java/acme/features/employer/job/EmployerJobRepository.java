
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.application.Application;
import acme.entities.auditrecord.Auditrecord;
import acme.entities.configuration.Configuration;
import acme.entities.descriptor.Descriptor;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j from Job j where j.reference = ?1")
	Job findOneJobByReference(String reference);

	@Query("select d From Duty d where d.descriptor.job.id= ?1")
	Collection<Duty> findDutiesByJobId(int jobId);

	@Query("select d from Descriptor d where d.job.id= ?1")
	Descriptor findDescriptorByJobId(int id);

	@Query("select j from Job j where j.employer.id = ?1")
	Collection<Job> findManyByEmployerId(int employerId);

	@Query("select e from Employer e where e.id = ?1")
	Employer findOneEmployerById(int employerId);

	@Query("select ar from Auditrecord ar where ar.job.id=?1")
	Collection<Auditrecord> findManyAuditrecordByJobId(int id);

	@Query("select ap from Application ap where ap.job.id=?1")
	Collection<Application> findManyApplicationByJobId(int id);

	@Query("select a from Configuration a where a.id=6")
	Configuration findConfiguration();

}
