
<%@ page import="pcsladders.team.Team" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'team.label', default: 'Team')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-team" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-team" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list team">
			
				<g:if test="${teamInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="team.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${teamInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${teamInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="team.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${teamInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${teamInstance?.tag}">
				<li class="fieldcontain">
					<span id="tag-label" class="property-label"><g:message code="team.tag.label" default="Tag" /></span>
					
						<span class="property-value" aria-labelledby="tag-label"><g:fieldValue bean="${teamInstance}" field="tag"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${teamInstance?.tierRift}">
				<li class="fieldcontain">
					<span id="tierRift-label" class="property-label"><g:message code="team.tierRift.label" default="Tier Rift" /></span>
					
						<span class="property-value" aria-labelledby="tierRift-label"><g:fieldValue bean="${teamInstance}" field="tierRift"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${teamInstance?.divisionRift}">
				<li class="fieldcontain">
					<span id="divisionRift-label" class="property-label"><g:message code="team.divisionRift.label" default="Division Rift" /></span>
					
						<span class="property-value" aria-labelledby="divisionRift-label"><g:fieldValue bean="${teamInstance}" field="divisionRift"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${teamInstance?.leaguePointsRift}">
				<li class="fieldcontain">
					<span id="leaguePointsRift-label" class="property-label"><g:message code="team.leaguePointsRift.label" default="League Points Rift" /></span>
					
						<span class="property-value" aria-labelledby="leaguePointsRift-label"><g:fieldValue bean="${teamInstance}" field="leaguePointsRift"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${teamInstance?.addDate}">
				<li class="fieldcontain">
					<span id="addDate-label" class="property-label"><g:message code="team.addDate.label" default="Add Date" /></span>
					
						<span class="property-value" aria-labelledby="addDate-label"><g:fieldValue bean="${teamInstance}" field="addDate"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${teamInstance?.createDate}">
				<li class="fieldcontain">
					<span id="createDate-label" class="property-label"><g:message code="team.createDate.label" default="Create Date" /></span>
					
						<span class="property-value" aria-labelledby="createDate-label"><g:fieldValue bean="${teamInstance}" field="createDate"/></span>
					
				</li>
				</g:if>
			
			</ol>
		</div>
	</body>
</html>
