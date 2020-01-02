<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<jstl:if test="${command == 'show'}">
		<acme:form-textbox code="authenticated.message.form.label.threadTitle" path="threadTitle" />
		<acme:form-integer code="authenticated.message.form.label.threadId" path="threadId" />
	</jstl:if>
	<jstl:if test="${command != 'create'}">
	<acme:form-moment code="authenticated.message.form.label.moment" path="moment" readonly="true" />
		</jstl:if>
	<acme:form-textbox code="authenticated.message.form.label.title" path="title" />
	<acme:form-textarea code="authenticated.message.form.label.tags" path="tags" />
	<acme:form-textarea code="authenticated.message.form.label.body" path="body" />
	<jstl:if test="${command == 'create'}">
		<acme:form-checkbox code="authenticated.message.form.checkbox.agree" path="accept"/>
	</jstl:if>
	<acme:form-submit test="${command == 'create'}" code="authenticated.message.form.button.create"
		action="/authenticated/message/create?id=${param.id}" />

	<acme:form-return code="authenticated.message.form.label.button.return" />

</acme:form>