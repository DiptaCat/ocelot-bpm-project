<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="default.home.label"/></title>
</head>

<body>
<div class="page-header">
    <h1>Pàgina d'inici</h1>
</div>

<g:link controller="user" class="btn btn-sm btn-info" action="create"><i class="icon-plus"></i> <g:message
        code="new.user"
        default=""/>New User</g:link>


<g:link controller="bpm" class="btn btn-sm btn-info" action="create"><i class="icon-plus"></i> <g:message code="new.bpm"
                                                                                                          default=""/>New Bpm</g:link>

</body>
</html>
