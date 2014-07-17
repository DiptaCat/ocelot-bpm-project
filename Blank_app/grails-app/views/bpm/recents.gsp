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

<div class="row-fluid wizard-actions">
    <g:link class="btn btn-sm btn-info" action="create"><i class="icon-plus"></i> <g:message code="default.new.label"
                                                                                             args="[entityName]"/></g:link>
    <div class="hr dotted clearfix"></div>
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

            <th><g:message code="bpm.lastUpdated.label" default="Last Updated"/></th>

            <th><g:message code="bpm.name.label" default="Name"/></th>

            <th><g:message code="bpm.user.label" default="User"/></th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${bpmInstanceList}" status="i" var="bpmInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:formatDate date="${bpmInstance.lastUpdated}"/></td>

                <td><g:link action="show"
                            id="${bpmInstance.id}">${fieldValue(bean: bpmInstance, field: "name")}</g:link></td>

                <td>${fieldValue(bean: bpmInstance, field: "user.login")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</body>
</html>