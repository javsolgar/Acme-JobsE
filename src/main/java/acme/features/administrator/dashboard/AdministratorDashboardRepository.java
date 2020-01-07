
package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.application.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(*) from Investorsrecords a")
	Integer countInvestorsRecords();

	@Query("select count(*) from Announcement a")
	Integer countAnnouncement();

	@Query("select count(*) from Companyrecord a")
	Integer countCompanyRecord();

	@Query("select cast(MIN(reward_amount)as double) AS PRICE FROM  Request")
	Double getMinRewardRequest();

	@Query("select cast(MAX(reward_amount)as double) AS PRICE FROM  Request")
	Double getMaxRewardRequest();

	@Query("select cast(MIN(lower_range_amount)as double) AS PRICE FROM  Offers")
	Double getMinRewardOffer();

	@Query("select cast(MAX(major_range_amount)as double) AS PRICE FROM  Offers")
	Double getMaxRewardOffer();

	@Query("select count(*) as Companyrecord from Companyrecord group by sector")
	List<Integer> getCompanysBySector();

	@Query("select sector as Companyrecord from Companyrecord group by sector")
	List<String> getSectorsOfCompanys();

	@Query("select count(*) as Investorsrecords from Investorsrecords group by sector")
	List<Integer> getInverstorsBySector();

	@Query("select sector as Investorsrecords from Investorsrecords group by sector")
	List<String> getSectorOfInverstors();

	@Query("select avg(a.reward.amount) from Request a")
	Double getMediaRequest();

	@Query("select avg((a.majorRange.amount + a.lowerRange.amount)/2) from Offers a")
	Double getMediaOffer();

	@Query("select count(*) as job from Job group by finalMode")
	List<Integer> getJobsByFinalMode();

	@Query("select status as Application from Application group by status")
	List<String> getStatusOfApplication();

	@Query("select count(*) as Application from Application group by status")
	List<Integer> getApplicationBystatus();

	@Query("select avg( select count(j) from Job j where j.employer.id = e.id ) from Employer e")
	Double getMediaJobsPerEmployer();

	@Query("select avg( select count(a) from Application a where a.job.employer.id = e.id ) from Employer e")
	Double getMediaApplicationPerEmployer();

	@Query("select avg( select count(a) from Application a where a.worker.id = w.id ) from Worker w")
	Double getMediaApplicationPerWorker();

	@Query("select a from Application a where a.status='pending'")
	List<Application> getPendingApplications();

	@Query("select a from Application a where a.status='accepted'")
	List<Application> getAcceptedApplications();

	@Query("select a from Application a where a.status='rejected'")
	List<Application> getRejectedApplications();

	@Query("select cast(((select count(jc)/cast(count(j) as float) from Job jc where jc.hasRolenta=true)) as float) from Job j")
	Double ratioJobsWithRolenta();

	@Query("select cast(((select count(js)/cast(count(jr) as float) from Job js where js.hasSymbol=true)) as float) from Job jr where jr.hasRolenta=true")
	Double ratioRolentasWithSymbol();

	@Query("select cast(((select count(ap)/cast(count(a) as float) from Application ap where ap.hasBeenProtected=true)) as float) from Application a")
	Double rationProtectedApplications();

}
