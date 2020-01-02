<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.investorsrecords.form.label.name" path="name"/>
	<acme:form-textbox code="authenticated.investorsrecords.form.label.sector" path="sector"/>
	<acme:form-textarea code="authenticated.investorsrecords.form.label.statement" path="statement"/>
	<acme:form-integer code="authenticated.investorsrecords.form.label.numberStars" path="numberStars"/>
	
	<acme:form-return code="authenticated.investorsrecords.form.label.button.return"/>
</acme:form>