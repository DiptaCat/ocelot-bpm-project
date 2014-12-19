<%@ page import="ocelot.Model" %>
<!DOCTYPE html>

<html>
<head>
	<meta name="layout" content="main">
	<g:set var="entityName" value="${message(code: 'model.label', default: 'Model')}"/>
	<title><g:message code="default.show.label" args="[entityName]"/> ${modelInstance.toString()}</title>
</head>

<body>
<div class="page-header">
	<h1><g:message code="default.show.label" args="[entityName]"/></h1>
</div>

<div class="row-fluid wizard-actions">
	<g:link class="btn btn-sm btn-purple" action="index"><i class="glyphicon glyphicon-th-list"></i> <g:message
			code="default.list.label"
			args="[entityName]"/></g:link>
	<g:link class="btn btn-sm btn-info" action="create"><i class="glyphicon glyphicon-plus"></i> <g:message
			code="default.new.label"
			args="[entityName]"/></g:link>
</div>

<div id="show-model" class="content scaffold-show" role="main">
	<g:if test="${flash.message}">
		<div class="alert alert-${flash.messagetype ?: 'info'} message" role="status"><button data-dismiss="alert"
																							  class="close"
																							  type="button">×</button>${flash.message}
		</div>
	</g:if>
	<div class="form-horizontal">
		<fieldset>
			<g:render template="data" model="${['modelInstance': modelInstance]}"/>

		</fieldset>
	</div>
	<g:form>
		<div class="form-actions">

			<g:hiddenField name="id" value="${modelInstance?.id}"/>
			<g:link class="edit btn btn-sm btn-primary" action="edit" id="${modelInstance?.id}">
				<i class="glyphicon glyphicon-pencil"></i>
				<g:message code="default.button.edit.label" default="Edit"/>
			</g:link>

		</div>
	</g:form>
</div>
</body>
</html>