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
	<jstl:if test="${command == 'show' }">
	<acme:form-textbox code="worker.application.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	<jstl:if test="${status != 'pending'}">
		<acme:form-textbox code="worker.application.form.label.justification" path="justification"/>
	</jstl:if>
	<acme:form-submit test ="${command == 'create'}" code="worker.application.form.label.button.create" action="/worker/application/create?jobId=${param.jobId}"/>
	<acme:form-return code="worker.application.form.label.button.return"/>
</acme:form>