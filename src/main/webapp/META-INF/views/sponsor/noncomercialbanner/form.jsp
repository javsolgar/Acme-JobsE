<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>

	<jstl:set var="id" value="${id}"/>
	<acme:form-url code="sponsor.noncomercialbanner.form.label.urlPicture" path="urlPicture"/>
	<acme:form-textbox code="sponsor.noncomercialbanner.form.label.slogan" path="slogan"/>
	<acme:form-url code="sponsor.noncomercialbanner.form.label.urlTarget" path="urlTarget"/>
	<acme:form-url code="sponsor.noncomercialbanner.form.label.jingle" path="jingle"/>
	
		<jstl:if test="${command != 'create' }">
	<jstl:if test="${finalMode == false }">
	<acme:form-select code="sponsor.noncomercialbanner.form.label.finalmode" path="finalMode">
	<acme:form-option code="sponsor.noncomercialbanner.form.label.finalmode.false" value="false"/>
	<acme:form-option code="sponsor.noncomercialbanner.form.label.finalmode.true" value="true"/>
	</acme:form-select>
	</jstl:if>
	
	<jstl:if test="${finalMode == true }">
	<acme:form-select code="sponsor.noncomercialbanner.form.label.finalmode" path="finalMode">
	<acme:form-option code="sponsor.noncomercialbanner.form.label.finalmode.true" value="true"/>
	<acme:form-option code="sponsor.noncomercialbanner.form.label.finalmode.false" value="false"/>
	</acme:form-select>
	</jstl:if>
	</jstl:if>
	
	<jstl:if test="${command == 'create' }">
	<acme:form-select code="sponsor.noncomercialbanner.form.label.finalmode" path="finalMode">
	<acme:form-option code="sponsor.noncomercialbanner.form.label.finalmode.false" value="false"/>
	<acme:form-option code="sponsor.noncomercialbanner.form.label.finalmode.true" value="true"/>
	</acme:form-select>
	</jstl:if>
	
	<acme:form-submit test ="${command == 'create'}" code="sponsor.noncomercialbanner.form.label.button.create" action="/sponsor/noncomercialbanner/create"/>
	<acme:form-submit test ="${command == 'update'}" code="sponsor.noncomercialbanner.form.label.button.update" action="/sponsor/noncomercialbanner/update?id=${id}"/>
	<acme:form-submit test ="${command == 'show'}" code="sponsor.noncomercialbanner.form.label.button.update" action="/sponsor/noncomercialbanner/update?id=${id}"/>
	<acme:form-submit test ="${command == 'update'}" code="sponsor.noncomercialbanner.form.label.button.delete" action="/sponsor/noncomercialbanner/delete"/>
	<acme:form-submit test ="${command == 'show'}" code="sponsor.noncomercialbanner.form.label.button.delete" action="/sponsor/noncomercialbanner/delete"/>
	<acme:form-return code="sponsor.noncomercialbanner.form.label.button.return"/>
</acme:form>