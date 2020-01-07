<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.job.form.label.reference" path="reference"/>
	<acme:form-textbox code="authenticated.job.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="authenticated.job.form.label.salary" path="salary"/>
	<acme:form-money code="authenticated.job.form.label.moreInfo" path="moreInfo"/>
	
	<jstl:if test="${hasRolenta == true}">
	<h4><acme:message code="worker.job.form.message.yourchallenge"/></h4>
	<acme:form-textbox code="worker.job.form.label.textChallenge" path="textRolenta"/>
	<acme:form-textbox code="worker.job.form.label.moreInfoChallenge" path="symbol"/>
	</jstl:if>
	
	<jstl:set var="idJob" value="${id}"/>
	<jstl:set var="jobId" value="${id}"/>
	<h4><acme:menu-suboption code="authenticated.job.form.label.duties" action="/worker/descriptor/show?jobId=${jobId}"/></h4>
  	<h4><acme:menu-suboption code="authenticated.job.form.label.auditRecords" action="/worker/auditrecord/list_mine?id=${idJob}"/></h4>
  	<h4><acme:menu-suboption code="worker.job.form.label.application" action="/worker/application/create?jobId=${jobId}&rolenta=${hasRolenta}"/> </h4>
	



	<acme:form-return code="authenticated.job.form.label.button.return"/>
</acme:form>