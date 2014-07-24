<%@ page import="blank.Model" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'model.label', default: 'model')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="page-header">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
</div>

<div class="row-fluid wizard-actions">
    <g:link class="btn btn-sm btn-info" action="create"><i class="icon-plus"></i> <g:message code="default.new.label"
                                                                                             args="[entityName]"/></g:link>
    <div class="hr dotted clearfix"></div>
</div>

<div id="list-model" class="content scaffold-list" role="main">
    <g:if test="${flash.message}">
        <div class="alert alert-${flash.messagetype ?: 'info'} message" role="status"><button data-dismiss="alert"
                                                                                              class="close"
                                                                                              type="button">×</button>${flash.message}
        </div>
    </g:if>
    <table class="table table-striped table-bordered">
        <thead>
        <tr>

            <th><g:message code="model.lastUpdated.label" default="Last Updated"/></th>

            <th><g:message code="model.name.label" default="Name"/></th>

            <th><g:message code="model.user.label" default="User"/></th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${modelInstanceList}" status="i" var="modelInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:formatDate date="${modelInstance.lastUpdated}"/></td>

                <td><g:link action="show"
                            id="${modelInstance.id}">${fieldValue(bean: modelInstance, field: "name")}</g:link></td>

                <td>${fieldValue(bean: modelInstance, field: "user.login")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</body>
</html>