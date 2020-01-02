<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	
	<acme:form-textarea code="authenticated.worker.form.label.qualificationRecord" path="qualificationRecord"/>
	<acme:form-textarea code="authenticated.worker.form.label.skillRecord" path="skillRecord"/>
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.worker.form.button.create" action="/authenticated/worker/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.worker.form.button.update" action="/authenticated/worker/update"/>
	<acme:form-return code="authenticated.worker.form.label.button.return"/>
	
</acme:form>