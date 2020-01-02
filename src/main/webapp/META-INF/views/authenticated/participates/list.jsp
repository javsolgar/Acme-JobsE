<%--
- list.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.participates.list.label.authenticated" path="userName" width="100%"/>		


</acme:list>

<jstl:if test="${param.creatorId==principal.activeRoleId}">
<h4><acme:menu-suboption code="authenticated.participates.form.button.addusers" action="/authenticated/participates/create?mtid=${param.mtid}&activeId=${activeId}&creatorId=${creatorId}"/></h4>
</jstl:if>

<!-- http://localhost:8081/acme-jobs/authenticated/messagethread/list_mine -->