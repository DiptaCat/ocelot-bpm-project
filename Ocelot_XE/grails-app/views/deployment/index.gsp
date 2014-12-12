<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="models.list.label"/></title>
</head>

<body>
<div class="page-header">
    <h1>Models List from Ocelot MT</h1>
</div>
<div>


    <div class="tab-content">
        <div class="tab-pane active" id="processes">

            <div class="controls" style="font-style: italic">
                <ul class="one-to-many">
                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th><g:message code="default.process.id" default="Id"/></th>
                            <th><g:message  code="default.process.name" default="Name"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <g:each in="${processes}" status="i" var="process">
                            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                <td>${process.id}</td>
                                <td>${process.name}</td>
                                <td><g:link class="edit btn btn-sm btn-grey" action="show" id="${process.id}">
                                    <g:message code="deployment.button.deploy" default="Deploy"/>
                                </g:link></td>
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
