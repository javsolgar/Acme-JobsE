<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<jstl:set var="id" value="${id}"/>
	<acme:form-textbox code="administrator.auditorrequest.form.label.username" path="username" readonly="true"/>
	<acme:form-textbox code="administrator.auditorrequest.form.label.firm" path="firm" readonly="true"/>
	<acme:form-textbox code="administrator.auditorrequest.form.label.respStatement" path="respStatement" readonly="true"/>
	<acme:form-textarea code="administrator.auditorrequest.form.label.description" path="description" readonly="true"/>
	<acme:form-textarea code="administrator.auditorrequest.form.label.moment" path="moment" readonly="true"/>
	
	
	<jstl:if test="${status == 'pending'}">
	<acme:form-select code="administrator.auditorrequest.form.label.status" path="status">
	<acme:form-option code="administrator.auditorrequest.form.label.pending" value="pending"/>
	<acme:form-option code="administrator.auditorrequest.form.label.accepted" value="accepted"/>
	<acme:form-option code="administrator.auditorrequest.form.label.rejected" value="rejected"/>
	</acme:form-select>
	</jstl:if>
	
	<jstl:if test="${status == 'rejected'}">
	<acme:form-textbox code="administrator.auditorrequest.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${status == 'accepted'}">
	<acme:form-textbox code="administrator.auditorrequest.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${status == 'pending'}">
	<acme:form-submit test ="${command == 'show'}" code="administrator.auditorrequest.form.label.button.update" action="/administrator/auditorrequest/update?id=${id}"/>
	<acme:form-submit test ="${command == 'update'}" code="administrator.auditorrequest.form.label.button.update" action="/administrator/auditorrequest/update?id=${id}"/>
	</jstl:if>
	<acme:form-return code="administrator.auditorrequest.form.label.button.return"/>
</acme:form>