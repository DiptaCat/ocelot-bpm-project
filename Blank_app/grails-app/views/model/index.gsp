<%@ page import="blank.Model" %>
<!DOCTYPE html>

<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'model.label', default: 'Model')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="page-header">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
</div>

<div class="row-fluid wizard-actions">
    <g:link class="btn btn-sm btn-info" action="create"><i class="glyphicon glyphicon-plus"></i> <g:message code="default.new.label"
                                                                                             args="[entityName]"/></g:link>
</div>

<div class="hr dotted clearfix"></div>

<div>

    <div id="list-models" class="content scaffold-list" role="main">

        <g:if test="${flash.message}">
            <div class="alert alert-${flash.messagetype ?: 'info'} message" role="status"><button
                    data-dismiss="alert"
                    class="close"
                    type="button">×</button>${flash.message}
            </div>
        </g:if>

        <ul class="nav nav-tabs" role="tablist">
            <li class="active"><a href="#models" role="tab" data-toggle="tab">Models</a></li>
            <li><a href="#recents" role="tab" data-toggle="tab">Recents</a></li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">

            <div class="tab-pane active" id="models">

                <div class="controls" style="font-style: italic">
                    <ul class="one-to-many">
                        <table class="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th><g:message code="model.name.label" default="Name"/></th>
                                <th><g:message code="model.user.label" default="User"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <g:each in="${modelInstanceList ?}" status="i" var="model">
                                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                    <td><g:link controller="model" action="show"
                                                id="${model.id}">${model?.name}</g:link>
                                    <td><g:link controller="user" action="show"
                                                id="${model?.user?.id}">${model.user?.login}</g:link>
                                </tr>
                            </g:each>
                            </tbody>
                        </table>
                    </ul>
                </div>
            </div>

            <div class="tab-pane" id="recents">

                <div class="controls" style="font-style: italic">
                    <ul class="one-to-many">
                        <table class="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th><g:message code="model.name.label" default="Name"/></th>
                                <th><g:message code="model.lastUpdated.label" default="Last Updated"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <g:each in="${modelInstanceList?.sort { a, b -> a.lastUpdated.compareTo(b.lastUpdated) }?.reverse()}"
                                    status="i" var="model">
                                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                    <td><g:link controller="model" action="show"
                                                id="${model.id}">${model?.name}</g:link>
                                    <td><g:formatDate date="${model?.lastUpdated}"/></td>
                                </tr>
                            </g:each>
                            </tbody>
                        </table>
                    </ul>
                </div>
            </div>

            <div class="pagination pagination-right">
                <div class="pagination-content">
                    <g:paginate total="${modelInstanceCount ?: 0}"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>