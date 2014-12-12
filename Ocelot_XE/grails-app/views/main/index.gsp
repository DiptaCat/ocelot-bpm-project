<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="default.home.label"/></title>
</head>

<body>
<div class="page-header">
    <h1>Home Page</h1>
</div>
<div>
    <g:if test="${flash.error}">
        <div class="alert alert-error" style="display: block">${flash.error}</div>
    </g:if>
    <g:if test="${flash.message}">
        <div class="message" style="display: block">${flash.message}</div>
    </g:if>
    <div class="tab-content">
        <div class="tab-pane active" id="fileUpload">
            <div class="controls" style="font-style: italic">
                <ul class="one-to-many">
                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th><g:message code="deployment.upload.file" default="Upload Form: "/></th>
                            <th><g:message  code="deployment.upload.list" default="Name"/></th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><g:uploadForm action="upload">
                                    <input type="file" name="myFile" />
                                    <input type="submit" />
                                </g:uploadForm></td>
                                <td>hola</td>
                            </tr>
                        </tbody>
                    </table>
                </ul>
            </div>
        </div>
    </div>
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
                        <g:each in="${deployments}" status="i" var="d">
                            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                <td><g:link action="show" controller="process"
                                            id="${d.getValue()['id']}" params="${[id:d.getValue()['id']]}">${d.getValue()['id']}</g:link></td>
                                <td>${d.getValue()['name']}
                                <td>${d.getValue()['time']}
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
