<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="employer.auditrecord.form.label.jobTitle" path="jobTitle"/>
	<acme:form-textbox code="employer.auditrecord.form.label.auditorUser" path="auditorUser"/>
	<acme:form-textbox code="employer.auditrecord.form.label.title" path="title"/>
	<acme:form-textbox code="employer.auditrecord.form.label.moment" path="moment"/>
	<acme:form-textarea code="employer.auditrecord.form.label.body" path="body"/>
	
	<acme:form-return code="employer.auditrecord.form.label.button.return"/>
</acme:form>