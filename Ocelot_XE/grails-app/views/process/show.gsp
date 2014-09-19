<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="default.home.label"/></title>
</head>

<body>
<div class="page-header">
    <h1>Process</h1>
</div>
<div>
    <div class="tab-content">
        <div class="tab-pane active" id="deployments">

            <div class="controls" style="font-style: italic">
                <ul class="one-to-many">
                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th><g:message code="deployment.process.id" default="Id"/></th>
                            <th><g:message  code="deployment.process.name" default="Name"/></th>
                            <th><g:message  code="default.process.RunningInstances" default="Running Instances"/></th>

                        </tr>
                        </thead>
                        <tbody>
                        <td>${deployment.getId()}</td>
                        <td>${deployment.getName()}</td>
                        <td>${numInstnaces}</td>
                        </tbody>
                    </table>
                </ul>
            </div>
        </div>
    </div>
    <g:link class="edit btn btn-sm btn-pink" action="newInstanceView" id="${deployment.getId()}">
        <i class="icon-trash icon-pencil"></i>
        <g:message code="instance.process.new" default="New Instance"/>
    </g:link>
</div>

</body>
</html>
