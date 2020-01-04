<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>

<acme:form readonly="true">

<acme:form readonly="true">
	<acme:form-double code="administrator.dashboard.form.label.ratioJobsThatHaveChallengeProperty" path="ratioJobsThatHaveChallengeProperty" />
	<acme:form-double code="administrator.dashboard.form.label.ratioApplicationPasswordProtected" path="ratioApplicationPasswordProtected" />	
	<acme:form-integer code="administrator.dashboard.form.label.totalAnnouncement" path="totalAnnouncement" />
	<acme:form-integer code="administrator.dashboard.form.label.totalInvestorsRecord" path="totalInvestorsRecord" />	
	<acme:form-integer code="administrator.dashboard.form.label.totalCompanyRecords" path="totalCompanyRecords" />
	<acme:form-double code="administrator.dashboard.form.label.minRewardRequest" path="minRewardRequest" />
	<acme:form-double code="administrator.dashboard.form.label.maxRewardRequest" path="maxRewardRequest" />	
	<acme:form-double code="administrator.dashboard.form.label.minRewardOffers" path="minRewardOffers" />
	<acme:form-double code="administrator.dashboard.form.label.maxRewardOffers" path="maxRewardOffers"/>
	<acme:form-double code="administrator.dashboard.form.label.mediaRequest" path="mediaRequest"/>
	<acme:form-double code="administrator.dashboard.form.label.mediaOffer" path="mediaOffer"/>
	<acme:form-double code="administrator.dashboard.form.label.stdevRequest" path="stdevRequest"/>
	<acme:form-double code="administrator.dashboard.form.label.stdevOffer" path="stdevOffer"/>
	<acme:form-double code="administrator.dashboard.form.label.mediaJobsEmployer" path="avgJobEmployer"/>
	<acme:form-double code="administrator.dashboard.form.label.mediaApplicationWorker" path="avgApplicationWorker"/>
	 <acme:form-double code="administrator.dashboard.form.label.mediaApplicationEmployer" path="avgApplicationEmployer"/>
	
</acme:form>

				<!--	Chart of companies  -->	

	<h2><acme:message code="administrator.dashboard.form.title.chartCompanys"/></h2>
	<div><canvas id="canvas"></canvas></div>
	<script type="text/javascript">
	$(document).ready(function(){
		var data = {
				labels	:	[
					<jstl:forEach var="item" items="${sectorsOfCompanys}">
					"${item}",
					</jstl:forEach>
				],
				datasets	:	[ 
					{
						data : [
							<jstl:forEach var="item" items="${companysBySector}">
							"${item}",
							</jstl:forEach>
							
						],
						 backgroundColor: ["#0074D9", "#FF4136", "#2ECC40", "#FF851B", "#7FDBFF", "#B10DC9", "#FFDC00", "#001f3f", "#39CCCC", "#01FF70", "#85144b", "#F012BE", "#3D9970", "#111111", "#AAAAAA"]
					
				}
					]
		
		
	};
	
	var options =	{
			scales	:	{
				yAxes	:	[
					{
						ticks	:	{
							suggestedMin	:	0.0,
							suggestedMax	:	20.0
						}
					}
				]
			},
			legend	:	{
				display : false
			}
	};
	
	var canvas, context;
	
	canvas = document.getElementById("canvas");
	context = canvas.getContext("2d");
	new Chart(context, {
		type	:	"bar",
		data	:	data,
		options	:	options
	});
});
	</script>
	
	<!--	Chart of Inverstors  -->	
	
	<h2><acme:message code="administrator.dashboard.form.title.chartInverstors"/></h2>
	<div><canvas id="canvas2"></canvas></div>
	<script type="text/javascript">
	$(document).ready(function(){
		var data = {
				labels	:	[
					<jstl:forEach var="item" items="${sectorsOfInverstors}">
					"${item}",
					</jstl:forEach>
				],
				datasets	:	[ 
					{
						data : [
							<jstl:forEach var="item" items="${inverstorsBySector}">
							${item},
							</jstl:forEach>
							
						],
						 backgroundColor: ["#0074D9", "#FF4136", "#2ECC40", "#FF851B", "#7FDBFF", "#B10DC9", "#FFDC00", "#001f3f", "#39CCCC", "#01FF70", "#85144b", "#F012BE", "#3D9970", "#111111", "#AAAAAA"]
					
				}
					]
		
		
	};
	
	var options =	{
			scales	:	{
				yAxes	:	[
					{
						ticks	:	{
							suggestedMin	:	0.0,
							suggestedMax	:	20.0
						}
					}
				]
			},
			legend	:	{
				display : false
			}
	};
	
	var canvas, context;
	
	canvas = document.getElementById("canvas2");
	context = canvas.getContext("2d");
	new Chart(context, {
		type	:	"bar",
		data	:	data,
		options	:	options
	});
});
	
	
	</script>
	
	<!--	Chart of Jobs  -->	
	
	<h2><acme:message code="administrator.dashboard.form.title.chartJobs"/></h2>
	<div><canvas id="canvas3"></canvas></div>
	<script type="text/javascript">
	
	$(document).ready(function(){
		var data = {
				labels	:	["open","closed"],
				datasets	:	[ 
					{
						data : [
							<jstl:forEach var="item" items="${jobsByFinalMode}">
							"${item}",
							</jstl:forEach>
							
						],
						 backgroundColor: ["#0074D9", "#FF4136", "#2ECC40", "#FF851B", "#7FDBFF", "#B10DC9", "#FFDC00", "#001f3f", "#39CCCC", "#01FF70", "#85144b", "#F012BE", "#3D9970", "#111111", "#AAAAAA"]
					
				}
					]
		
		
	};
	
	var options =	{
			scales	:	{
				yAxes	:	[
					{
						ticks	:	{
							suggestedMin	:	0.0,
							suggestedMax	:	1.0
						}
					}
				]
			},
			legend	:	{
				display : false
			}
	};
	
	var canvas, context;
	
	canvas = document.getElementById("canvas3");
	context = canvas.getContext("2d");
	new Chart(context, {
		type	:	"pie",
		data	:	data,
		options	:	options
	});
	
});
	</script>
	
	

				<!--	Chart of Applications  -->	

	<h2><acme:message code="administrator.dashboard.form.title.chartApplications"/></h2>
	<div><canvas id="canvas4"></canvas></div>
	<script type="text/javascript">
	$(document).ready(function(){
		var data = {
				labels	:	[
					<jstl:forEach var="item" items="${statusOfApplication}">
					"${item}",
					</jstl:forEach>
				],
				datasets	:	[ 
					{
						data : [
							<jstl:forEach var="item" items="${applicationByStatus}">
							"${item}",
							</jstl:forEach>
							
						],
						 backgroundColor: ["#0074D9", "#FF4136", "#2ECC40", "#FF851B", "#7FDBFF", "#B10DC9", "#FFDC00", "#001f3f", "#39CCCC", "#01FF70", "#85144b", "#F012BE", "#3D9970", "#111111", "#AAAAAA"]
					
				}
					]
		
		
	};
	
	var options =	{
			scales	:	{
				yAxes	:	[
					{
						ticks	:	{
							suggestedMin	:	0.0,
							suggestedMax	:	1.0
						}
					}
				]
			},
			legend	:	{
				display : false
			}
	};
	
	var canvas, context;
	
	canvas = document.getElementById("canvas4");
	context = canvas.getContext("2d");
	new Chart(context, {
		type	:	"doughnut",
		data	:	data,
		options	:	options
	});
});
	</script>
	
	
	
	<!--	Chart of pending applications per day  -->	

	<h2><acme:message code="administrator.dashboard.form.title.applicationPendingPerDay"/></h2>
	<div><canvas id="canvas5"></canvas></div>
	<script type="text/javascript">
	$(document).ready(function(){
		
		
		
		var data = {
				labels	:	[
					<jstl:forEach var="item" items="${diasPending}">
					"${item}",
					</jstl:forEach>
				],
				datasets	:	[ 
					{
						data : [
							<jstl:forEach var="item" items="${applicationPendingPerDay}">
							"${item}",
							</jstl:forEach>
							
						],
						 
				}
					]
		
		
	};
	
	var options =	{
			scales	:	{
				yAxes	:	[
					{
						ticks	:	{
							suggestedMin	:	0.0,
							suggestedMax	:	10.0
						}
					}
				]
			},
			legend	:	{
				display : false
			}
	};
	
	var canvas, context;
	
	canvas = document.getElementById("canvas5");
	context = canvas.getContext("2d");
	new Chart(context, {
		type	:	"line",
		data	:	data,
		options	:	options
	});
});
	</script>
	
	<!--	Chart of accepted applications per day  -->	
	
	<h2><acme:message code="administrator.dashboard.form.title.applicationAcceptedPerDay"/></h2>
	<div><canvas id="canvas6"></canvas></div>
	<script type="text/javascript">
	$(document).ready(function(){
		
		
		
		var data = {
				labels	:	[
					<jstl:forEach var="item" items="${diasPending}">
					"${item}",
					</jstl:forEach>
				],
				datasets	:	[ 
					{
						data : [
							<jstl:forEach var="item" items="${applicationAcceptedPerDay}">
							"${item}",
							</jstl:forEach>
							
						],
						 
				}
					]
		
		
	};
	
	var options =	{
			scales	:	{
				yAxes	:	[
					{
						ticks	:	{
							suggestedMin	:	0.0,
							suggestedMax	:	10.0
						}
					}
				]
			},
			legend	:	{
				display : false
			}
	};
	
	var canvas, context;
	
	canvas = document.getElementById("canvas6");
	context = canvas.getContext("2d");
	new Chart(context, {
		type	:	"line",
		data	:	data,
		options	:	options
	});
});
	</script>
	
	
		<!--	Chart of rejected applications per day  -->	
	
	<h2><acme:message code="administrator.dashboard.form.title.applicationRejectedPerDay"/></h2>
	<div><canvas id="canvas7"></canvas></div>
	<script type="text/javascript">
	$(document).ready(function(){
		
		
		
		var data = {
				labels	:	[
					<jstl:forEach var="item" items="${diasPending}">
					"${item}",
					</jstl:forEach>
				],
				datasets	:	[ 
					{
						data : [
							<jstl:forEach var="item" items="${applicationRejectedPerDay}">
							"${item}",
							</jstl:forEach>
							
						],
						 
				}
					]
		
		
	};
	
	var options =	{
			scales	:	{
				yAxes	:	[
					{
						ticks	:	{
							suggestedMin	:	0.0,
							suggestedMax	:	10.0
						}
					}
				]
			},
			legend	:	{
				display : false
			}
	};
	
	var canvas, context;
	
	canvas = document.getElementById("canvas7");
	context = canvas.getContext("2d");
	new Chart(context, {
		type	:	"line",
		data	:	data,
		options	:	options
	});
});
	</script>
	
	
	
	
	
	<acme:form-return code="administrator.configuration.form.label.button.return"/>
</acme:form>