<%@ page import="blank.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
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

<div id="list-user" class="content scaffold-list" role="main">
    <g:if test="${flash.message}">
        <div class="alert alert-${flash.messagetype ?: 'info'} message" role="status"><button data-dismiss="alert"
                                                                                              class="close"
                                                                                              type="button">×</button>${flash.message}
        </div>
    </g:if>
    <table class="table table-striped table-bordered">
        <thead>
        <tr>

            <g:sortableColumn property="name" title="${message(code: 'user.name.label', default: 'Name')}"/>

            <g:sortableColumn property="login" title="${message(code: 'user.login.label', default: 'Login')}"/>

            %{-- <g:sortableColumn property="dateCreated" title="${message(code: 'user.dateCreated.label', default: 'Date Created')}" /> --}%

        </tr>
        </thead>
        <tbody>
        <g:each in="${userInstanceList}" status="i" var="userInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${userInstance.id}">${fieldValue(bean: userInstance, field: "name")}</g:link></td>

                <td>${fieldValue(bean: userInstance, field: "login")}</td>

                %{-- <td><g:formatDate date="${userInstance.dateCreated}" /></td> --}%

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination pagination-right">
        <div class="pagination-content">
            <g:paginate total="${userInstanceCount ?: 0}"/>
        </div>
    </div>
</div>

</body>
</html>