<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
	<acme:form-url code="administrator.noncomercialbanner.form.label.urlPicture" path="urlPicture"/>
	<acme:form-textarea code="administrator.noncomercialbanner.form.label.slogan" path="slogan"/>
	<acme:form-url code="administrator.noncomercialbanner.form.label.urlTarget" path="urlTarget"/>
	<acme:form-url code="administrator.noncomercialbanner.form.label.jingle" path="jingle"/>
	
	<acme:form-submit test ="${command == 'show'}" code="administrator.noncomercialbanner.form.button.update" action="/administrator/noncomercialbanner/update"/>
	<acme:form-submit test ="${command == 'show'}" code="administrator.noncomercialbanner.form.button.delete" action="/administrator/noncomercialbanner/delete"/>
	<acme:form-submit test ="${command == 'create'}" code="administrator.noncomercialbanner.form.button.create" action="/administrator/noncomercialbanner/create"/>
	<acme:form-submit test ="${command == 'update'}" code="administrator.noncomercialbanner.form.button.update" action="/administrator/noncomercialbanner/update"/>
	<acme:form-submit test ="${command == 'delete'}" code="administrator.noncomercialbanner.form.button.delete" action="/administrator/noncomercialbanner/delete"/>
	
	<acme:form-return code="administrator.noncomercialbanner.form.label.button.return"/>
</acme:form>