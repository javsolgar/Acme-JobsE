<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
	<acme:form-textbox code="administrator.companyrecords.form.label.name" path="name"/>
	<acme:form-textbox code="administrator.companyrecords.form.label.sector" path="sector"/>
	<acme:form-textbox code="administrator.companyrecords.form.label.ceo" path="ceo"/>
	<acme:form-textarea code="administrator.companyrecords.form.label.description" path="description"/>
	<acme:form-url code="administrator.companyrecords.form.label.website" path="website"/>
	<acme:form-textbox code="administrator.companyrecords.form.label.phone" path="phone"/>
	<acme:form-textbox code="administrator.companyrecords.form.label.email" path="email"/>
	<acme:form-textbox code="administrator.companyrecords.form.label.incorporated" path="incorporated"/>
	<acme:form-textbox code="administrator.companyrecords.form.label.numberStars" path="numberStars"/>
	
	<acme:form-submit test ="${command == 'show'}" code="administrator.companyrecords.form.button.update" action="/administrator/companyrecord/update"/>
	<acme:form-submit test ="${command == 'show'}" code="administrator.companyrecords.form.button.delete" action="/administrator/companyrecord/delete"/>
	<acme:form-submit test ="${command == 'create'}" code="administrator.companyrecords.form.button.create" action="/administrator/companyrecord/create"/>
	<acme:form-submit test ="${command == 'update'}" code="administrator.companyrecords.form.button.update" action="/administrator/companyrecord/update"/>
	<acme:form-submit test ="${command == 'delete'}" code="administrator.companyrecords.form.button.delete" action="/administrator/companyrecord/delete"/>
	
	<acme:form-return code="administrator.companyrecords.form.label.button.return"/>
</acme:form>