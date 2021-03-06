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


<form:form action="contest/crown/join.do" modelAttribute="contestForm">
	
	<form:hidden path="contestId" />
	
	<acme:join items="${projects }" itemLabel="title" code="contest.project" path="project" />
	
	<button type="submit" name="save" class="btn btn-success">
	<i class="glyphicon glyphicon-floppy-saved"></i> <spring:message code="project.save"/>
	</button>
	
	<input type="button" name="cancel" class="btn btn-danger" value="<spring:message code="project.cancel" />" class="btn btn-danger"  onclick="window.location='contest/display.do?contestId=${contestForm.contestId}'" /> <br />
</form:form>
<br/>
