
package acme.features.administrator.noncomercialbanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.noncomercialbanner.Noncomercialbanner;
import acme.framework.entities.Administrator;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorNoncomercialbannerRepository extends AbstractRepository {

	@Query("select a from Noncomercialbanner a where a.id=?1")
	Noncomercialbanner findOneById(int id);

	@Query("select a from Noncomercialbanner a where a.finalMode= true")
	Collection<Noncomercialbanner> findManyAll();
	@Query("select s from Administrator s where s.userAccount.id = ?1")
	Administrator findAdministratorByUserAccountId(int id);
}
