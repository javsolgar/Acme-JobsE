<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
	
	
	<jstl:set var="id" value="${id}"/>
	
	<acme:form-url code="sponsor.comercialbanner.form.label.urlPicture" path="urlPicture"/>
	<acme:form-textbox code="sponsor.comercialbanner.form.label.slogan" path="slogan"/>
	<acme:form-url code="sponsor.comercialbanner.form.label.urlTarget" path="urlTarget"/>
	<jstl:if test="${command == 'show' }">
	<acme:form-textbox code="sponsor.comercialbanner.form.label.creditNumber" path="creditNumber" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${command != 'create' }">
	<jstl:if test="${finalMode == false }">
	<acme:form-select code="sponsor.comercialbanner.form.label.finalmode" path="finalMode">
	<acme:form-option code="sponsor.comercialbanner.form.label.finalmode.false" value="false"/>
	<acme:form-option code="sponsor.comercialbanner.form.label.finalmode.true" value="true"/>
	</acme:form-select>
	</jstl:if>
	
	<jstl:if test="${finalMode == true }">
	<acme:form-select code="sponsor.comercialbanner.form.label.finalmode" path="finalMode">
	<acme:form-option code="sponsor.comercialbanner.form.label.finalmode.true" value="true"/>
	<acme:form-option code="sponsor.comercialbanner.form.label.finalmode.false" value="false"/>
	</acme:form-select>
	</jstl:if>
	</jstl:if>
	
	<jstl:if test="${command == 'create' }">
	<acme:form-select code="sponsor.comercialbanner.form.label.finalmode" path="finalMode">
	<acme:form-option code="sponsor.comercialbanner.form.label.finalmode.false" value="false"/>
	<acme:form-option code="sponsor.comercialbanner.form.label.finalmode.true" value="true"/>
	</acme:form-select>
	
	
	<h4><acme:message code="sponsor.comercialbanner.form.message"/></h4>
	<acme:form-textbox code="sponsor.comercialbanner.form.label.creditNumber" path="creditNumber"/>
	<acme:form-textbox code="sponsor.comercialbanner.form.label.name" path="name"/>
	<acme:form-textbox code="sponsor.comercialbanner.form.label.surname" path="surname"/>
	<acme:form-moment code="sponsor.comercialbanner.form.label.expiration" path="expiration"/>
	<acme:form-textbox code="sponsor.comercialbanner.form.label.securityCode" path="securityCode"/>
	
	<acme:form-select code="sponsor.comercialbanner.form.label.type" path="type">
	<acme:form-option code="sponsor.comercialbanner.form.label.type.Visa" value="Visa"/>
	<acme:form-option code="sponsor.comercialbanner.form.label.type.Master_Card" value="Master Card"/>
	<acme:form-option code="sponsor.comercialbanner.form.label.type.American_Express" value="American Express"/>
	<acme:form-option code="sponsor.comercialbanner.form.label.type.Dinners_Club" value="Dinners Club"/>
	</acme:form-select>
	</jstl:if>
	<acme:form-submit test ="${command == 'create'}" code="sponsor.comercialbanner.form.label.button.create" action="/sponsor/comercialbanner/create"/>
	<acme:form-submit test ="${command == 'update'}" code="sponsor.comercialbanner.form.label.button.update" action="/sponsor/comercialbanner/update?id=${id}"/>
	<acme:form-submit test ="${command == 'show'}" code="sponsor.comercialbanner.form.label.button.update" action="/sponsor/comercialbanner/update?id=${id}"/>
	<acme:form-submit test ="${command == 'update'}" code="sponsor.comercialbanner.form.label.button.delete" action="/sponsor/comercialbanner/delete"/>
	<acme:form-submit test ="${command == 'show'}" code="sponsor.comercialbanner.form.label.button.delete" action="/sponsor/comercialbanner/delete"/>
	<acme:form-return code="sponsor.comercialbanner.form.label.button.return"/>
</acme:form>