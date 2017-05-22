<%--
 * display.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<form:form action="project/crown/edit.do" modelAttribute="projectForm">
	
	<form:hidden path="id" />
	
	<!-- private Category category; -->

	<acme:textbox code="project.title" path="title"/>
	<acme:textarea code="project.description" path="description"/>
	<acme:textbox code="project.goal" path="goal"/>
	<acme:textbox code="project.ttl2" path="ttl"/>
	<jstl:if test="${projectForm.id == 0}">
		<acme:textbox code="project.url" path="url"/>
	</jstl:if>
	
	<acme:select items="${categories }" itemLabel="name" code="project.category" path="category"/>

	<input type="submit" name="save" value="<spring:message code="project.save" />" />
	
	<jstl:if test="${projectForm.id != 0}">
	<jstl:if test="${borrar == true}">
		<input type="submit" name="delete" value="<spring:message code="project.delete" />" onclick="return confirm('<spring:message code="project.confirm.delete" />')" />&nbsp;
	</jstl:if>
	</jstl:if>
	
	<jstl:choose>
			<jstl:when test="${projectForm.id==0}">
				<input type="button" name="cancel" value="<spring:message code="project.cancel" />" onclick="window.location='welcome/index.do'" /> <br />
			</jstl:when>
			
			<jstl:otherwise>
				<input type="button" name="cancel" value="<spring:message code="project.cancel" />" onclick="window.location='project/display.do?projectId=${projectForm.id}'" /> <br />
			</jstl:otherwise>
	</jstl:choose>
</form:form>
<br/>
