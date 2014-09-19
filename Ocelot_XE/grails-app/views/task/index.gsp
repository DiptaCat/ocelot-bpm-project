<%--
  Created by IntelliJ IDEA.
  User: jbondia
  Date: 9/4/14
  Time: 1:02 PM
--%>

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
        <div class="tab-pane active" id="deployments">

            <div class="controls" style="font-style: italic">
                <ul class="one-to-many">
                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th><g:message code="process.task.name" default="Name"/></th>
                            <th><g:message  code="process.task.processName" default="Process Name"/></th>
                            <th><g:message  code="process.task.created" default="Created"/>
                            <th><g:message  code="process.task.due" default="Due"/></th>
                            <th><g:message  code="process.task.actions" default="Actions"/></th>

                        </tr>
                        </thead>
                        <tbody>
                        <g:each in="${tasks}" status="i" var="d">
                            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                <td><g:link action="show" controller="task"
                                            id="${d.getId()}" params="${[id:d.getId(), procName:processNames[i]]}">${d.getName()}</g:link></td>
                                <td>${processNames[i]}</td>
                                <td>${d.getCreateTime()}</td>
                                <td>${d.getDueDate()}</td>
                                <td><g:link class="edit btn btn-sm btn-grey" controller="task" action="action" id="${d.getId()}">Task</g:link></td>
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