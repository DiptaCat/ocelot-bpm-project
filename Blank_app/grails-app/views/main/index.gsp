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

<!-- Nav tabs -->
<ul class="nav nav-tabs" role="tablist">
    <li class="active"><a href="#my-bmps" role="tab" data-toggle="tab">My BPMs</a></li>
    <li><a href="#favourites" role="tab" data-toggle="tab">Favourites</a></li>
    <li><a href="#temporals" role="tab" data-toggle="tab">Temporals</a></li>
</ul>

<!-- Tab panes -->
<div class="tab-content">
    <div class="tab-pane active" id="my-bmps"><g:render template="data"</div>
    <div class="tab-pane" id="favourites">Favourites</div>
    <div class="tab-pane" id="temporals">Temporals</div>
</div>

</body>
</html>
