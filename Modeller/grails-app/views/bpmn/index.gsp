
<%@ page import="modeller.Bpmn" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'bpmn.label', default: 'Bpmn')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-bpmn" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-bpmn" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'bpmn.name.label', default: 'Name')}" />
					
						<th><g:message code="bpmn.user.label" default="User" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${bpmnInstanceList}" status="i" var="bpmnInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${bpmnInstance.id}">${fieldValue(bean: bpmnInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: bpmnInstance, field: "user")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${bpmnInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
