
package acme.features.notauditor.descriptor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.descriptor.Descriptor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface NotauditorDescriptorRepository extends AbstractRepository {

	@Query("select d from Descriptor d where job.id=?1")
	Descriptor findOneByJobId(int id);

}
