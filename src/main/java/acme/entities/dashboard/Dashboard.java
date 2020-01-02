
package acme.entities.dashboard;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	//Serialization Identify -------------------------------------------------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//	Atributes	----------------------------------------------------------------------------------------------------

	private Integer				totalAnnouncement;

	private Integer				totalInvestorsRecord;

	private Integer				totalCompanyRecords;

	private Double				minRewardRequest;

	private Double				maxRewardRequest;

	private Double				minRewardOffers;

	private Double				maxRewardOffers;

	private List<Integer>		companysBySector;

	private List<String>		sectorsOfCompanys;

	private List<Integer>		inverstorsBySector;

	private List<String>		sectorsOfInverstors;

	private Double				mediaRequest;

	private Double				stdevRequest;

	private Double				mediaOffer;

	private Double				stdevOffer;

	private List<Double>		jobsByFinalMode;

	private List<String>		statusOfApplication;

	private List<Double>		applicationByStatus;

	private Double				avgJobEmployer;

	private Double				avgApplicationEmployer;

	private Double				avgApplicationWorker;

	private List<Integer>		diasPending;

	private List<Integer>		applicationPendingPerDay;

	private List<Integer>		applicationAcceptedPerDay;

	private List<Integer>		applicationRejectedPerDay;

}
