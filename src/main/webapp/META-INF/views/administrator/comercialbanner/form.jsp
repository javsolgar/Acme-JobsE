<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
	<acme:form-url code="administrator.comercialbanner.form.label.urlPicture" path="urlPicture"/>
	<acme:form-textbox code="administrator.comercialbanner.form.label.slogan" path="slogan"/>
	<acme:form-url code="administrator.comercialbanner.form.label.urlTarget" path="urlTarget"/>
	<jstl:if test="${command == 'show'}">
	<acme:form-textbox code="administrator.comercialbanner.form.label.creditCard" path="creditNumber" readonly="true"/>
	</jstl:if>
	
	
	<jstl:if test="${command == 'create' }">
	
	<h4><acme:message code="administrator.comercialbanner.form.message"/></h4>
	<acme:form-textbox code="administrator.comercialbanner.form.label.creditCard" path="creditNumber"/>
	<acme:form-textbox code="administrator.comercialbanner.form.label.name" path="name"/>
	<acme:form-textbox code="administrator.comercialbanner.form.label.surname" path="surname"/>
	<acme:form-moment code="administrator.comercialbanner.form.label.expiration" path="expiration"/>
	<acme:form-textbox code="administrator.comercialbanner.form.label.securityCode" path="securityCode"/>
	
	<acme:form-select code="administrator.comercialbanner.form.label.type" path="type">
	<acme:form-option code="administrator.comercialbanner.form.label.type.Visa" value="Visa"/>
	<acme:form-option code="administrator.comercialbanner.form.label.type.Master_Card" value="Master Card"/>
	<acme:form-option code="administrator.comercialbanner.form.label.type.American_Express" value="American Express"/>
	<acme:form-option code="administrator.comercialbanner.form.label.type.Dinners_Club" value="Dinners Club"/>
	</acme:form-select>
	</jstl:if>
	
	<acme:form-submit test ="${command == 'show'}" code="administrator.comercialbanner.form.button.update" action="/administrator/comercialbanner/update"/>
	<acme:form-submit test ="${command == 'show'}" code="administrator.comercialbanner.form.button.delete" action="/administrator/comercialbanner/delete"/>
	<acme:form-submit test ="${command == 'create'}" code="administrator.comercialbanner.form.button.create" action="/administrator/comercialbanner/create"/>
	<acme:form-submit test ="${command == 'update'}" code="administrator.comercialbanner.form.button.update" action="/administrator/comercialbanner/update"/>
	<acme:form-submit test ="${command == 'delete'}" code="administrator.comercialbanner.form.button.delete" action="/administrator/comercialbanner/delete"/>
	
	<acme:form-return code="administrator.comercialbanner.form.label.button.return"/>
</acme:form>