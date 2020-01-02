<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-textbox code="provider.request.form.label.title" path="title"/>
	<acme:form-textarea code="provider.request.form.label.description" path="description"/>
	<jstl:if test="${command != 'create'}">
	<acme:form-moment code="provider.request.form.label.moment" path="moment"  readonly="true"/>
	</jstl:if>
	
	<acme:form-moment code="provider.request.form.label.deadline" path="deadLine"/>
	
	<acme:form-money code="provider.request.form.label.reward" path="reward"/>

	<acme:form-textbox code="provider.request.form.label.ticker" path="ticker"/>
	<jstl:if test="${command == 'create'}">
	<acme:message code="provider.request.form.message.explanation"/>
	<acme:form-checkbox code="provider.request.form.checkbox.agree" path="accept"/>
	</jstl:if>
	
	
	
	<acme:form-submit test ="${command == 'create'}" code="provider.request.form.button.create" action="/provider/request/create"/>
	<acme:form-return code="provider.request.form.label.button.return"/>
</acme:form>