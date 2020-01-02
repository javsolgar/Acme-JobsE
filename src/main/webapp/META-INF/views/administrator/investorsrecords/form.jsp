<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
    <acme:form-textbox code="administrator.investorsrecords.form.label.name" path="name"/>
	<acme:form-textbox code="administrator.investorsrecords.form.label.sector" path="sector"/>
	<acme:form-textarea code="administrator.investorsrecords.form.label.statement" path="statement"/>
	<acme:form-integer code="administrator.investorsrecords.form.label.numberStars" path="numberStars"/>
	
	
	<acme:form-submit test ="${command == 'show'}" code="administrator.investorsrecords.form.button.update" action="/administrator/investorsrecords/update"/>
	<acme:form-submit test ="${command == 'show'}" code="administrator.investorsrecords.form.button.delete" action="/administrator/investorsrecords/delete"/>
	<acme:form-submit test ="${command == 'create'}" code="administrator.investorsrecords.form.button.create" action="/administrator/investorsrecords/create"/>
	<acme:form-submit test ="${command == 'update'}" code="administrator.investorsrecords.form.button.update" action="/administrator/investorsrecords/update"/>
	<acme:form-submit test ="${command == 'delete'}" code="administrator.investorsrecords.form.button.delete" action="/administrator/investorsrecords/delete"/>
	
	<acme:form-return code="administrator.investorsrecords.form.label.button.return"/>
</acme:form>