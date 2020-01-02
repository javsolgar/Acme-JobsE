<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.offers.form.label.title" path="title"/>
	<acme:form-textarea code="authenticated.offers.form.label.description" path="description"/>
	<acme:form-moment code="authenticated.offers.form.label.moment" path="moment"/>
	<acme:form-moment code="authenticated.offers.form.label.deadline" path="deadline"/>
	<acme:form-textbox code="authenticated.offers.form.label.major" path="majorRange"/>
	<acme:form-textbox code="authenticated.offers.form.label.lower" path="lowerRange"/>
	<acme:form-textbox code="authenticated.offers.form.label.ticker" path="ticker"/>
	
	<acme:form-return code="authenticated.offers.form.label.button.return"/>
</acme:form>