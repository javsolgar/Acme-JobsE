
package acme.features.employer.descriptor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.Configuration;
import acme.entities.descriptor.Descriptor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDescriptorRepository extends AbstractRepository {

	@Query("select d from Descriptor d where job.id=?1")
	Descriptor findOneByJobId(int id);

	@Query("select d from Descriptor d where d.id=?1")
	Descriptor findOneById(int id);

	@Query("select a from Configuration a where a.id=6")
	Configuration findConfiguration();

}
