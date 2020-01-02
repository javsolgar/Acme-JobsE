<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	
	
	<jstl:if test="${command == 'show'}">
	<acme:form-textbox code="employer.descriptor.form.label.jobTitle" path="jobTitle" readonly="true"/>
	<acme:form-integer code="employer.descriptor.form.label.jobId" path="jobId" readonly="true"/>
	</jstl:if>
	<acme:form-textarea code="employer.descriptor.form.label.description" path="description"/>
	<jstl:set var="id" value="${id}"/>
	<h4><acme:menu-suboption code="employer.descriptor.form.label.duties" action="/employer/duty/list?idDescriptor=${id}&fm=${param.fm}"/></h4>
	
	<acme:form-submit test="${command == 'show'}" code="employer.descriptor.form.button.update" action="/employer/descriptor/update"/>
	<acme:form-submit test="${command == 'update'}" code="employer.descriptor.form.button.update" action="/employer/descriptor/update"/>
	<acme:form-return code="employer.descriptor.form.label.button.return"/>

</acme:form>