<%@ page import="blank.User" %>
<!DOCTYPE html>

<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/> ${userInstance.toString()}</title>
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

<div id="show-user" class="content scaffold-show" role="main">
    <g:if test="${flash.message}">
        <div class="alert alert-${flash.messagetype ?: 'info'} message" role="status"><button data-dismiss="alert"
                                                                                              class="close"
                                                                                              type="button">×</button>${flash.message}
        </div>
    </g:if>
    <div class="form-horizontal">
        <fieldset>
            <g:render template="data" model="${['userInstance': userInstance]}"/>
        </fieldset>
    </div>
    <g:form>
        <div class="form-actions">

            <g:hiddenField name="id" value="${userInstance?.id}"/>

            <g:link class="edit btn btn-sm btn-primary" action="edit" id="${userInstance?.id}">
                <i class="icon-trash icon-pencil"></i>
                <g:message code="default.button.edit.label" default="Edit"/>
            </g:link>

            <g:hiddenField name="id"/>
            <g:link class="edit btn btn-sm btn-red" action="getModelTabs" id="${userInstance?.id}">
                <i class="icon-trash icon-pencil"></i>
                <g:message code="default.button.tabsModels.label" default="Models Manager"/>
            </g:link>

        </div>
    </g:form>
</div>
</body>
</html>

%{--<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-user" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list user">

				<g:if test="${userInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="user.name.label" default="Name" /></span>

						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${userInstance}" field="name"/></span>

				</li>
				</g:if>

				<g:if test="${userInstance?.login}">
				<li class="fieldcontain">
					<span id="login-label" class="property-label"><g:message code="user.login.label" default="Login" /></span>

						<span class="property-value" aria-labelledby="login-label"><g:fieldValue bean="${userInstance}" field="login"/></span>

				</li>
				</g:if>

				<g:if test="${userInstance?.models}">
				<li class="fieldcontain">
					<span id="models-label" class="property-label"><g:message code="user.models.label" default="models" /></span>

						<g:each in="${userInstance.models}" var="b">
						<span class="property-value" aria-labelledby="models-label"><g:link controller="model" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></span>
						</g:each>

				</li>
				</g:if>

				<g:if test="${userInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="user.dateCreated.label" default="Date Created" /></span>

						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${userInstance?.dateCreated}" /></span>

				</li>
				</g:if>

			</ol>
			<g:form url="[resource:userInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${userInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>--}%