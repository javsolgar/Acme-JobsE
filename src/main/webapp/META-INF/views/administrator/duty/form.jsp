<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textarea code="employer.duty.form.label.Jobtitle" path="title"/>
	
	<acme:form-textarea code="employer.duty.form.label.title" path="title"/>
	<acme:form-textbox code="employer.duty.form.label.percentage" path="percentage"/>
	<acme:form-textarea code="employer.duty.form.label.description" path="description"/>
	


	<acme:form-return code="employer.job.form.label.button.return"/>
</acme:form>