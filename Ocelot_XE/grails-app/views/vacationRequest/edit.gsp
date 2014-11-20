
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'vacationRequest.label', default: 'VacationRequest')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-vacationRequest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<div id="edit-vacationRequest" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${vacationRequestInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${vacationRequestInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="update">
				<g:hiddenField name="version" value="${vacationRequestInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<span class="button"><g:submitButton name="save" class="save" value="${message(code: 'default.button.save.label', default: 'Save')}" /></span>
					<span class="button"><g:submitButton name="complete" class="save" value="${message(code: 'default.button.complete.label', default: 'Complete')}" /></span>
						<g:hiddenField name="id" value="${vacationRequestInstance?.id}" />
						<g:hiddenField name="taskId" value="${taskId}" />
				</fieldset>
			</g:form>




		</div>
	</body>
</html>
