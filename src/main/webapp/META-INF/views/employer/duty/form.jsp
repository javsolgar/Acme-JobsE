<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<jstl:if test="${command != 'create'}">
	<acme:form-textarea code="employer.duty.form.label.jobTitle" path="jobTitle" readonly="true"/>
	<acme:form-integer code="employer.duty.form.label.jobId" path="jobId" readonly="true"/>
	</jstl:if>
	
	<acme:form-textarea code="employer.duty.form.label.title" path="title"/>
	<acme:form-textbox code="employer.duty.form.label.description" path="description"/>
	<acme:form-double code="employer.duty.form.label.percentage" path="percentage"/>

	<acme:form-submit test="${command == 'create'}" code="employer.duty.form.button.create" action="/employer/duty/create?idDescriptor=${param.idDescriptor}"/>
    <acme:form-submit test="${command == 'show' && finalMode == 'false'}" code="employer.duty.form.button.update" action="/employer/duty/update"/>
	<acme:form-submit test="${command == 'update' && finalMode == 'false'}" code="employer.duty.form.button.update" action="/employer/duty/update"/>
  	<acme:form-submit test ="${command == 'show' && finalMode == 'false'}" code="employer.duty.form.button.delete" action="/employer/duty/delete"/>
	<acme:form-submit test ="${command == 'delete' && finalMode == 'false'}" code="employer.duty.form.button.delete" action="/employer/duty/delete"/>
	<acme:form-return code="employer.job.form.label.button.return"/>
</acme:form>