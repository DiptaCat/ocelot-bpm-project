<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="default.home.label"/></title>
</head>

<body>
<div class="page-header">
    <h1>Process Details</h1>
</div>
<div>
    <div class="tab-content">
        <div class="tab-pane active" id="deployments">

            <div class="controls" style="font-style: italic">
                <ul class="one-to-many">
                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th><g:message code="default.process.id" default="Id"/></th>
                            <th><g:message  code="default.process.name" default="Name"/></th>
                            <th><g:message  code="default.process.key" default="Key"/></th>
                            <th><g:message  code="default.process.version" default="Version"/></th>
                            <th><g:message  code="default.process.instances" default="Instances"/></th>

                        </tr>
                        </thead>
                        <tbody>
                        <td>${process.deploymentId}</td>
                        <td>${process.name}</td>
                        <td>${process.id}</td>
                        <td>${process.version}</td>
                        <td>${numInstances}</td>
                        </tbody>
                    </table>
                </ul>
            </div>
        </div>
    </div>
    <g:link class="edit btn btn-sm btn-pink" action="create" id="${process.deploymentId}">
        <i class="icon-trash icon-pencil"></i>
        <g:message code="default.button.create.label" default="Create"/>
    </g:link>
</div>

</body>
</html>
