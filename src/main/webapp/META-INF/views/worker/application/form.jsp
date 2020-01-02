<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="worker.application.form.label.reference" path="reference"/>
	<jstl:if test="${command != 'create'}">
	<acme:form-moment code="worker.application.form.label.moment" path="moment"/>
	</jstl:if>
	
	<acme:form-textarea code="worker.application.form.label.skills" path="skills"/>
	<acme:form-textarea code="worker.application.form.label.statement" path="statement"/>
	<acme:form-textarea code="worker.application.form.label.qualifications" path="qualifications"/>
	
	<jstl:if test="${hasChallenge == true || challenge == true }">
	<jstl:if test="${command == 'create'}">
	<h4><acme:message code="worker.application.form.message.answer"/></h4>
	</jstl:if>
	<acme:form-textbox code="worker.application.form.label.answer" path="answer"/>
	<acme:form-textbox code="worker.application.form.label.optionalApplication" path="optionalApplication"/>
	<jstl:if test="${command == 'create'}">
	<h4><acme:message code="worker.application.form.message.password"/></h4>
	<acme:form-textbox code="worker.application.form.label.password" path="password"/>
	</jstl:if>
	</jstl:if>
	
	<jstl:if test="${command == 'show' }">
	<acme:form-textbox code="worker.application.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	<jstl:if test="${status != 'pending' && command != 'create'}">
		<acme:form-textbox code="worker.application.form.label.justification" path="justification"/>
	</jstl:if>
	
	<jstl:if test="${hasXXXX == true}">
	<h4><acme:message code="worker.application.form.message.answer.created"/></h4>
	<acme:form-textbox code="worker.application.form.label.answer" path="answer"/>
	<acme:form-textbox code="worker.application.form.label.optionalApplication" path="optionalApplication"/>
	<jstl:if test="${hasPassword == true}">
	<acme:form-textbox code="worker.application.form.label.password.created" path="password"/>
	</jstl:if>
	</jstl:if>
	


	
	
	
	<acme:form-submit test ="${command == 'create'}" code="worker.application.form.label.button.create" action="/worker/application/create?jobId=${param.jobId}&challenge=${param.challenge}"/>
	<acme:form-return code="worker.application.form.label.button.return"/>
</acme:form>