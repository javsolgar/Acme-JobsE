<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<jstl:set var="fm" value="${finalMode}" />
	<jstl:set var="read" value="true" />
	<jstl:if test="${command == 'create'}">
		<jstl:set var="read" value="false" />
	</jstl:if>

	<acme:form-textbox code="employer.job.form.label.reference" path="reference" readonly="${read}" />
	<acme:form-textbox code="employer.job.form.label.title" path="title" />

	<jstl:if test="${command == 'create'}">
		<acme:form-textarea code="employer.job.form.label.description" path="description" />
	</jstl:if>

	<acme:form-moment code="employer.job.form.label.deadline" path="deadline" />
	<acme:form-money code="employer.job.form.label.salary" path="salary" />
	<acme:form-textbox code="employer.job.form.label.moreInfo" path="moreInfo" />

	<jstl:if test="${command == 'create'}">
		<h4>
			<acme:message code="employer.job.form.message.challenge" />
		</h4>
		<acme:form-textbox code="employer.job.form.label.textChallenge" path="textRolenta" />
		<acme:form-textbox code="employer.job.form.label.moreInfoChallenge" path="symbol" />
	</jstl:if>
	<!-- ---------------------------------------------------------------------------------- -->
	<jstl:if test="${command == 'show'}">
		<jstl:if test="${hasRolenta == true}">
			<h4>
				<acme:message code="employer.job.form.message.yourchallenge" />
			</h4>
			<acme:form-textbox code="employer.job.form.label.textChallenge" path="textRolenta" />
			<acme:form-textbox code="employer.job.form.label.moreInfoChallenge" path="symbol" />
		</jstl:if>

		<jstl:if test="${hasRolenta == false && finalMode == false}">
			<h4>
				<acme:message code="employer.job.form.message.challenge" />
			</h4>
			<acme:form-textbox code="employer.job.form.label.textChallenge" path="textRolenta" />
			<acme:form-textbox code="employer.job.form.label.moreInfoChallenge" path="symbol" />
		</jstl:if>
	</jstl:if>


	<!-- dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd -->
	<jstl:if test="${command == 'update'}">
			<h4>
				<acme:message code="employer.job.form.message.yourchallenge" />
			</h4>
			<acme:form-textbox code="employer.job.form.label.textChallenge" path="textRolenta" />
			<acme:form-textbox code="employer.job.form.label.moreInfoChallenge" path="symbol" />
	</jstl:if>



	<!-- ---------------------------------------------------------------------------------- -->
	<jstl:if test="${finalMode == false && command != 'create'}">
		<acme:form-select code="employer.job.form.label.finalMode" path="finalMode">
			<acme:form-option code="employer.job.form.label.finalMode.false" value="false" />
			<acme:form-option code="employer.job.form.label.finalMode.true" value="true" />
		</acme:form-select>
	</jstl:if>

	<jstl:if test="${finalMode == true && command == 'update'}">
		<acme:form-select code="employer.job.form.label.finalMode" path="finalMode">
			<acme:form-option code="employer.job.form.label.finalMode.true" value="true" />
			<acme:form-option code="employer.job.form.label.finalMode.false" value="false" />
		</acme:form-select>
	</jstl:if>

	<jstl:if test="${finalMode == true && command == 'show'}">
		<acme:form-select code="employer.job.form.label.finalMode" path="finalMode">
			<acme:form-option code="employer.job.form.label.finalMode.true" value="true" />
		</acme:form-select>
	</jstl:if>

	<jstl:set var="idJob" value="${id}" />
	<jstl:set var="jobId" value="${id}" />
	<jstl:if test="${command != 'create'}">
		<jstl:if test="${command == 'update'}">
			<h4>
				<acme:menu-suboption code="employer.job.form.label.duties" action="/employer/descriptor/show?jobId=${jobId}&fm=false" />
			</h4>
		</jstl:if>
		<jstl:if test="${command != 'update'}">
			<h4>
				<acme:menu-suboption code="employer.job.form.label.duties" action="/employer/descriptor/show?jobId=${jobId}&fm=${fm}" />
			</h4>
		</jstl:if>
		<jstl:if test="${finalMode == true && command == 'show'}">
			<h4>
				<acme:menu-suboption code="employer.job.form.label.auditRecords" action="/employer/auditrecord/list_mine?id=${idJob}" />
			</h4>
		</jstl:if>
	</jstl:if>




	<acme:form-submit test="${command == 'create'}" code="employer.job.form.button.create" action="/employer/job/create" />

	<acme:form-submit test="${command == 'show' && finalMode == false}" code="employer.job.form.button.update"
		action="/employer/job/update" />
	<acme:form-submit test="${command == 'update'}" code="employer.job.form.button.update" action="/employer/job/update" />


	<jstl:if test="${hasApplication == false}">
		<acme:form-submit test="${command != 'create'}" code="employer.job.form.button.delete" action="/employer/job/delete" />
	</jstl:if>
	<acme:form-return code="employer.job.form.label.button.return" />
</acme:form>