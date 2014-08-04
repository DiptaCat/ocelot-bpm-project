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
    <g:link action="deploy">Deploy</g:link>

    Upload Form: <br />
    <g:uploadForm action="upload">
        <input type="file" name="myFile" />
        <input type="submit" />
    </g:uploadForm>
    <div class="tab-content">
        <div class="tab-pane active" id="deployments">

            <div class="controls" style="font-style: italic">
                <ul class="one-to-many">
                    <table class="table table-striped table-bordered">

                            <g:each in="${deployments}" var="d">
                                    <li>${d}</li>
                            </g:each>
                    </table>
                </ul>
            </div>
            </div>
    </div>
</div>

</body>
</html>
