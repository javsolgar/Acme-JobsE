<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	
	<acme:form-textbox code="authenticated.auditor.form.label.firm" path="firm"/>
	<acme:form-textarea code="authenticated.auditor.form.label.respStatement" path="respStatement"/>
	
	
	<acme:form-submit test ="${command == 'update'}" code="authenticated.auditor.form.button.update" action="/authenticated/auditor/update"/>
	<acme:form-return code="authenticated.auditorrequest.form.label.button.return"/>
</acme:form>