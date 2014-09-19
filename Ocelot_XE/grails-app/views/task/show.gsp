<%--
  Created by IntelliJ IDEA.
  User: jbondia
  Date: 9/19/14
  Time: 12:55 PM
--%>

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="default.home.label"/></title>
</head>

<body>
<div class="page-header">
    <h1>Task</h1>
</div>
<div>
    <div class="tab-content">
        <div class="tab-pane active" id="task">

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
                            <td>${task.getName()}</td>
                            <td>${procName}</td>
                            <td>${task.createTime}</td>
                            <td>${task.dueDate}</td>
                            <td><g:link class="edit btn btn-sm btn-grey" controller="task" action="action">Task</g:link></td>
                        </tbody>
                    </table>
                </ul>
            </div>
        </div>
    </div>
</div>

</body>
</html>
