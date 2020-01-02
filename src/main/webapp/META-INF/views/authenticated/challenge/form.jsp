<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.challenge.form.label.title" path="title"/>
	<acme:form-textarea code="authenticated.challenge.form.label.description" path="description"/>
	<acme:form-moment code="authenticated.challenge.form.label.deadline" path="deadline"/>
	<acme:form-textarea  code="authenticated.challenge.form.label.goalGold" path="goalGold"/>
	<acme:form-money code="authenticated.challenge.form.label.rewardGold" path="rewardGold"/>	
	<acme:form-textarea code="authenticated.challenge.form.label.goalSilver" path="goalSilver"/>
	<acme:form-money code="authenticated.challenge.form.label.rewardSilver" path="rewardSilver"/>	
	<acme:form-textarea  code="authenticated.challenge.form.label.goalBronze" path="goalBronze"/>	
	<acme:form-money code="authenticated.challenge.form.label.rewardBronze" path="rewardBronze"/>	

	
	
	<acme:form-return code="authenticated.challenge.form.label.button.return"/>
</acme:form>