
<%@ page import="blank.Task" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'task.label', default: 'Task')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div class="page-header">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
</div>

<div class="row-fluid wizard-actions">
    <g:link class="btn btn-sm btn-info" action="create"><i class="icon-plus"/></i> <g:message code="default.new.label" args="[entityName]" /></g:link>
    <div class="hr dotted clearfix"></div>
</div>

<div id="list-task" class="content scaffold-list" role="main">
    <g:if test="${flash.message}">
        <div class="alert alert-${flash.messagetype?:'info'} message" role="status"><button data-dismiss="alert" class="close" type="button">×</button>${flash.message}</div>
    </g:if>
    <table class="table table-striped table-bordered">
        <thead>
        <tr>
            
            <g:sortableColumn property="name" title="${message(code: 'task.name.label', default: 'Name')}" />
            
            <g:sortableColumn property="dateCreated" title="${message(code: 'task.dateCreated.label', default: 'Date Created')}" />
            
            <th><g:message code="task.user.label" default="User" /></th>
            
        </tr>
        </thead>
        <tbody>
        <g:each in="${taskInstanceList}" status="i" var="taskInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                
                <td><g:link action="show" id="${taskInstance.id}">${fieldValue(bean: taskInstance, field: "name")}</g:link></td>
                
                <td><g:formatDate date="${taskInstance.dateCreated}" /></td>
                
                <td>${fieldValue(bean: taskInstance, field: "user")}</td>
                
            </tr>
        </g:each>
        </tbody>
    </table>
    <div class="pagination pagination-right">
        <div class="pagination-content">
            <g:paginate total="${taskInstanceCount ?: 0}" />
        </div>
    </div>
</div>
</body>
</html>