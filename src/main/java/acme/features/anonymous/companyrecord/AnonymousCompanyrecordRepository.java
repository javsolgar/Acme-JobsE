
package acme.features.anonymous.companyrecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.companyrecords.Companyrecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousCompanyrecordRepository extends AbstractRepository {

	@Query("select c from Companyrecord c where c.id= ?1")
	Companyrecord findOneById(int id);

	@Query("select c from Companyrecord c")
	Collection<Companyrecord> findManyAll();

}
