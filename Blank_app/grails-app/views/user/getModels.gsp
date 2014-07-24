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

            <g:sortableColumn property="name" title="${message(code: 'model.name.label', default: 'Name')}"/>

            <g:sortableColumn property="user" title="${message(code: 'model.name.label', default: 'User')}"/>

            <th><g:message code="user.add.label" default="Add to Favourites"/></th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${modelsList}" status="i" var="model">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td>${fieldValue(bean: model, field: "name")}</td>

                <td>${fieldValue(bean: model, field: "user.login")}</td>

                <td><g:link action="markFavourite" id="${model.id}"
                            params="${[userId: userInstance?.id, modelId: model.id]}"
                            controller="user">Add</g:link></td>

            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</body>
</html>

%{--<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'model.label', default: 'model')}"/>
</head>

<body>
<div class="page-header">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
</div>

<g:each in="${modelsList}" var="model">
    <div id="show">
        <span class="name">${model.name} ${model.id}</span><g:link action="addmodelToFavourites" id="${model.id}"
                                                                params="${[userId: userInstance?.id, modelId: model.id]}"
                                                                controller="user">Add to Favourites</g:link>
    </div>
</g:each>
</body>
</html>--}%