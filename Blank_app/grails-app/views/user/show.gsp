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
            <g:link class="edit btn btn-sm btn-pink" action="getModelTabs" id="${userInstance?.id}">
                <i class="icon-trash icon-pencil"></i>
                <g:message code="default.button.tabsModels.label" default="Models Manager"/>
            </g:link>

        </div>
    </g:form>
</div>
</body>
</html>
