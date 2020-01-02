<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	
	<acme:form-textarea code="authenticated.duty.form.label.jobTitle" path="jobTitle"/>
	<acme:form-integer code="authenticated.duty.form.label.jobId" path="jobId"/>
	
	<acme:form-textarea code="authenticated.descriptor.form.label.description" path="description"/>
	<jstl:set var="id" value="${id}"/>
	<h4><acme:menu-suboption code="authenticated.descriptor.form.label.duties" action="/notauditor/duty/list?id=${id}"/></h4>
	
	<acme:form-return code="authenticated.descriptor.form.label.button.return"/>

</acme:form>