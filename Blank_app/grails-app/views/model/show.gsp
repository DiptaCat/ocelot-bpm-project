<%@ page import="blank.Model" %>
<!DOCTYPE html>

<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'model.label', default: 'model')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/> ${modelInstance.toString()}</title>
</head>

<body>
<div class="page-header">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
</div>

<div class="row-fluid wizard-actions">
    <g:link class="btn btn-sm btn-purple" action="index"><i class="icon-list"></i> <g:message code="default.list.label"
                                                                                              args="[entityName]"/></g:link>
    <g:link class="btn btn-sm btn-info" action="create"><i class="icon-plus"></i> <g:message code="default.new.label"
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
                <i class="icon-trash icon-pencil"></i>
                <g:message code="default.button.edit.label" default="Edit"/>
            </g:link>

            <button type="submit" name="_action_delete" class="btn btn-sm btn-danger delete" formnovalidate=""
                    onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">

                <i class="icon-trash icon-white"></i>
                ${message(code: 'default.button.delete.label', default: 'Delete')}
            </button>
        </div>
    </g:form>
</div>
</body>
</html>

%{--<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'model.label', default: 'model')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-model" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-model" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list model">

				<g:if test="${modelInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="model.name.label" default="Name" /></span>

						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${modelInstance}" field="name"/></span>

				</li>
				</g:if>

				<g:if test="${modelInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="model.dateCreated.label" default="Date Created" /></span>

						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${modelInstance?.dateCreated}" /></span>

				</li>
				</g:if>

				<g:if test="${modelInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="model.lastUpdated.label" default="Last Updated" /></span>

						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${modelInstance?.lastUpdated}" /></span>

				</li>
				</g:if>

				<g:if test="${modelInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="model.user.label" default="User" /></span>

						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${modelInstance?.user?.id}">${modelInstance?.user?.encodeAsHTML()}</g:link></span>

				</li>
				</g:if>

			</ol>
			<g:form url="[resource:modelInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${modelInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>--}%