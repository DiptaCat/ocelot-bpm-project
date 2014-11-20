<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="default.home.label"/></title>
</head>

<body>
<div class="page-header">
    <h1>Tasks</h1>
</div>
    <div class="tab-content">
        <div class="tab-pane active" id="tasks">

            <div class="controls" style="font-style: italic">
                <ul class="one-to-many">
                    Assigned Tasks
                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th><g:message code=" process.task.name" default="Name"/></th>
                            <th><g:message  code="process.task.processName" default="Process Name"/></th>
                            <th><g:message  code="process.task.created" default="Created"/>
                            <th><g:message  code="process.task.assignee" default="Assignee"/>
                            <th><g:message  code="process.task.actions" default="Actions"/></th>

                        </tr>
                        </thead>
                        <tbody>
                        <g:each in="${assigned}" status="i" var="t">
                            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                <td>${t.name}</td>
                                <td>${t.processName}</td>
                                <td>${t.date}</td>
                                <td>${t.assignee}</td>
                                <td><g:link class="edit btn btn-sm btn-grey" controller="task" action="show" id="${t.id}">Show</g:link>
                                    | <g:link class="edit btn btn-sm btn-grey" controller="task" action="unclaim" id="${t.id}">Unclaim</g:link>
                                </td>
                            </tr>
                        </g:each>
                        </tbody>
                    </table>
                    Unassigned Tasks
                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th><g:message code="process.task.name" default="Name"/></th>
                            <th><g:message  code="process.task.processName" default="Process Name"/></th>
                            <th><g:message  code="process.task.created" default="Created"/>
                            <th><g:message  code="process.task.actions" default="Actions"/></th>

                        </tr>
                        </thead>
                        <tbody>
                        <g:each in="${unassigned}" status="i" var="t">
                            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                <td>${t.name}</td>
                                <td>${t.processName}</td>
                                <td>${t.date}</td>
                                <td><g:link class="edit btn btn-sm btn-grey" controller="task" action="claim" id="${t.id}">Claim</g:link></td>
                            </tr>
                        </g:each>
                        </tbody>
                    </table>


                </ul>
            </div>
        </div>
    </div>
</div>

</body>
</html>