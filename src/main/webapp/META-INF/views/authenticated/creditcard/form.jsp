<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	

	
	<acme:form-textbox code="authenticated.creditcard.form.label.creditNumber" path="creditNumber"/>
	<acme:form-textbox code="authenticated.creditcard.form.label.name" path="name"/>
	<acme:form-textbox code="authenticated.creditcard.form.label.surname" path="surname"/>
	<acme:form-moment code="authenticated.creditcard.form.label.expiration" path="expiration"/>
	<acme:form-textbox code="authenticated.creditcard.form.label.securityCode" path="securityCode"/>
	
	
	
	<acme:form-submit test="${command == 'update'}" code="authenticated.creditcard.form.button.update" action="/authenticated/creditcard/update?id=${sponsorId}"/>
	<acme:form-return code="authenticated.creditcard.form.label.button.return"/>
	
</acme:form>