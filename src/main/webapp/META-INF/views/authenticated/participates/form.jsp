<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<jstl:if test="${command!='create'}">
	<acme:form-textbox code="authenticated.participates.form.label.authenticated" path="userName"/>
	</jstl:if>
	<jstl:if test="${command=='create'}">
	<acme:form-textbox code="authenticated.participates.form.label.authenticatedCreate" path="userName"/>
	<acme:form-submit code="authenticated.participates.form.label.button.create" action="/authenticated/participates/create?mtid=${param.mtid}"/>
	</jstl:if>
	<acme:form-return code="authenticated.participates.form.label.button.return"/>
	<jstl:if test="${command!='create'}">
	<jstl:if test="${principal.activeRoleId==messagethread.creator.id}">
	<jstl:set var="id" value="${id}"/>
	<acme:form-submit code="authenticated.particiaptes.form.label.button.delete" action="/authenticated/participates/delete?id=${id}"/>
	</jstl:if>
	</jstl:if>
		
</acme:form>