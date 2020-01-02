<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<h3><acme:message code="employer.application.form.label.message.info"/></h3>
	<acme:form-textarea code="employer.application.form.label.skills" path="skills" readonly="true"/>
	<acme:form-textarea code="employer.application.form.label.qualifications" path="qualifications" readonly="true"/>
	<acme:form-moment code="employer.application.form.label.moment" path="moment" readonly="true"/>
	<acme:form-textbox code="employer.application.form.label.reference" path="reference" readonly="true"/>
	<acme:form-textarea code="employer.application.form.label.statement" path="statement" readonly="true"/>
	
	<jstl:if test="${status == 'pending' && command == 'show'}">
	<h3><acme:message code="employer.application.form.label.message"/></h3>
	<acme:form-select code="employer.application.form.label.status" path="status">
	<acme:form-option code="employer.application.form.label.status.pending" value="pending"/>
	<acme:form-option code="employer.application.form.label.status.accepted" value="accepted"/>
	<acme:form-option code="employer.application.form.label.status.rejected" value="rejected"/>
	</acme:form-select>
	</jstl:if>
	
	<jstl:if test="${status == 'pending' && command == 'update'}">
	<h3><acme:message code="employer.application.form.label.message"/></h3>
	<acme:form-select code="employer.application.form.label.status" path="status">
	<acme:form-option code="employer.application.form.label.status.pending" value="pending"/>
	<acme:form-option code="employer.application.form.label.status.rejected" value="rejected"/>
	<acme:form-option code="employer.application.form.label.status.accepted" value="accepted"/>
	</acme:form-select>
	</jstl:if>
	
	<jstl:if test="${status == 'accepted' && command == 'update'}">
	<h3><acme:message code="employer.application.form.label.message"/></h3>
	<acme:form-select code="employer.application.form.label.status" path="status">
	<acme:form-option code="employer.application.form.label.status.accepted" value="accepted"/>
	<acme:form-option code="employer.application.form.label.status.rejected" value="rejected"/>
	<acme:form-option code="employer.application.form.label.status.pending" value="pending"/>
	</acme:form-select>
	</jstl:if>
	
	<jstl:if test="${status == 'rejected' && command == 'update'}">
	<h3><acme:message code="employer.application.form.label.message"/></h3>
	<acme:form-select code="employer.application.form.label.status" path="status">
	<acme:form-option code="employer.application.form.label.status.rejected" value="rejected"/>
	<acme:form-option code="employer.application.form.label.status.pending" value="pending"/>
	<acme:form-option code="employer.application.form.label.status.accepted" value="accepted"/>
	</acme:form-select>
	</jstl:if>
	
	<jstl:if test="${status != 'pending' && command == 'show'}">
		<acme:form-textarea code="employer.application.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	<acme:form-textarea code="employer.application.form.label.justification" path="justification"/>


    <acme:form-submit test ="${command == 'show' && status == 'pending'}" code="employer.application.form.button.update" action="/employer/application/update"/>
    <acme:form-submit test ="${command == 'update'}" code="employer.application.form.button.update" action="/employer/application/update"/>
	<acme:form-return code="employer.application.form.label.button.return"/>
</acme:form>