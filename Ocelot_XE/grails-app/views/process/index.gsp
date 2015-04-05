<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="page-header">
    <h1>Process Management</h1>
</div>

    <g:if test="${flash.error}">
        <div class="alert alert-error" style="display: block">${flash.error}</div>
    </g:if>
    <g:if test="${flash.message}">
        <div class="message" style="display: block">${flash.message}</div>
    </g:if>


    <div class="tab-content">
        <div class="tab-pane active" id="processes">

            <div class="controls" style="font-style: italic">
                <ul class="one-to-many">
                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th><g:message code="default.process.id" default="Id"/></th>
                            <th><g:message  code="default.process.name" default="Name"/></th>
                            %{--<th><g:message  code="default.process.key" default="Key"/></th>--}%
                            <th><g:message  code="default.process.version" default="Version"/></th>
                            <th><g:message  code="default.process.instances" default="Instances"/></th>
                            <th><g:message  code="default.action.label" default="Action"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <g:each in="${processes}" status="i" var="process">
                            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                <td>${process.id}</td>
                                <td>${process.name}</td>
                                <td>${process.version}</td>
                                <td>${process.numInstances}</td>
                                <td><g:link class="edit btn btn-sm btn-success" action="create" id="${process.id}">
                                    <i class="icon-trash icon-paste"></i>
                                    <g:message code="default.button.startform.label" default="Start Form"/>
                                </g:link></td>
                            </tr>
                        </g:each>
                        </tbody>
                    </table>
                </ul>
            </div>
            </div>
    </div>

<br/>
<br/>
<br/>

<fieldset class="buttons">

    <div class="row">
        <div class="col-md-6">
            <div class="col-md-6">
                <g:message code="deployment.upload.file" default="Upload Model: "/>
                <g:uploadForm action="upload">
                    <input type="file" name="myFile" />
                    <input type="submit" />
                </g:uploadForm>
            </div>
            <div class="col-md-6">
                <g:message  code="deployment.upload.list" default="Name"/><br>
                <g:link class="edit btn btn-sm btn-primary" controller="deployment" action="index">
                    <i class="icon-list"></i><g:message code="default.ocelot-mt.button.label" default="List (Ocelot MT)"/>
                </g:link>
            </div>
        </div>
    </div>
</fieldset>

</body>
</html>
