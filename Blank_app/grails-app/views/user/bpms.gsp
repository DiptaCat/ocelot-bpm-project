<%@ page import="blank.Bpm" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'bpm.label', default: 'Bpm')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="page-header">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
</div>

<div id="list-bpm" class="content scaffold-list" role="main">
    <g:if test="${flash.message}">
        <div class="alert alert-${flash.messagetype ?: 'info'} message" role="status"><button data-dismiss="alert"
                                                                                              class="close"
                                                                                              type="button">×</button>${flash.message}
        </div>
    </g:if>
    <table class="table table-striped table-bordered">
        <thead>
        <tr>

            <g:sortableColumn property="name" title="${message(code: 'bpm.name.label', default: 'Name')}"/>

            <g:sortableColumn property="user" title="${message(code: 'bpm.name.label', default: 'User')}"/>

            <th><g:message code="user.add.label" default="Add to Favourites"/></th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${bpmsList}" status="i" var="bpm">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td>${fieldValue(bean: bpm, field: "name")}</td>

                <td>${fieldValue(bean: bpm, field: "user.login")}</td>

                <td><g:link action="addBPMToFavourites" id="${bpm.id}"
                            params="${[userId: userInstance?.id, bpmId: bpm.id]}"
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
    <g:set var="entityName" value="${message(code: 'bpm.label', default: 'BPM')}"/>
</head>

<body>
<div class="page-header">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
</div>

<g:each in="${bpmsList}" var="bpm">
    <div id="show">
        <span class="name">${bpm.name} ${bpm.id}</span><g:link action="addBPMToFavourites" id="${bpm.id}"
                                                                params="${[userId: userInstance?.id, bpmId: bpm.id]}"
                                                                controller="user">Add to Favourites</g:link>
    </div>
</g:each>
</body>
</html>--}%