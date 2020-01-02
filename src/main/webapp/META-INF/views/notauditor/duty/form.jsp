<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textarea code="authenticated.duty.form.label.jobTitle" path="jobTitle"/>
	<acme:form-integer code="authenticated.duty.form.label.jobId" path="jobId"/>
	
	<acme:form-textarea code="authenticated.duty.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.duty.form.label.percentage" path="percentage"/>
	<acme:form-textarea code="authenticated.duty.form.label.description" path="description"/>
	


	<acme:form-return code="authenticated.job.form.label.button.return"/>
</acme:form>