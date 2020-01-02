
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.Configuration;
import acme.entities.descriptor.Descriptor;
import acme.entities.duties.Duty;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDutyRepository extends AbstractRepository {

	@Query("select d from Duty d where d.descriptor.id =?1")
	Collection<Duty> findManyByDescriptorId(int id);

	@Query("select d from Duty d where d.id =?1")
	Duty findDutyById(int id);

	@Query("select a from Configuration a where a.id=6")
	Configuration findConfiguration();

	@Query("select d from Descriptor d where d.id=?1")
	Descriptor findOneById(int id);
}
