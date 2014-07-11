<%@ page import="blank.User" %>
<!DOCTYPE html>

<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
        <title><g:message code="default.show.label" args="[entityName]"/> BPMs</title>
    </head>

    <body>
        <div class="page-header">
            <h1><g:message default="BPMs"/></h1>
        </div>

        <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist">
            <li class="active"><a href="#my-bmps" role="tab" data-toggle="tab">My BPMs</a></li>
            <li><a href="#favourites" role="tab" data-toggle="tab">Favourites</a></li>
        <li><a href="#recents" role="tab" data-toggle="tab">Recents</a></li>
            <li><a href="#temporals" role="tab" data-toggle="tab">Temporals</a></li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div class="tab-pane active" id="my-bmps">
                <div class="control-group ${hasErrors(bean: userInstance, field: 'favouriteBPMs', 'error')}  col-xs-12">
                    <div class="controls" style="font-style: italic">
                        <ul class="one-to-many">
                            <table class="table table-striped table-bordered">
                                <thead>
                                <tr>
                                    <th><g:message code="bpm.name.label" default="Name"/></th>
                                    <th><g:message code="bpm.lastUpdated.label" default="Last Updated"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <g:each in="${userInstance?.bpms ?}" status="i" var="bpm">
                                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                        <td><g:link controller="bpm" action="show" id="${bpm.id}">${bpm?.name}</g:link>
                                        <td><g:formatDate date="${bpm?.lastUpdated}"/></td>
                                    </tr>
                                </g:each>
                                </tbody>
                            </table>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="tab-pane" id="favourites">
                <div class="tab-pane active" id="my-bmps">
                    <div class="control-group ${hasErrors(bean: userInstance, field: 'favouriteBPMs', 'error')}  col-xs-12">
                        <div class="controls" style="font-style: italic">
                            <ul class="one-to-many">
                                <table class="table table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th><g:message code="bpm.name.label" default="Name"/></th>
                                        <th><g:message code="bpm.lastUpdated.label" default="Last Updated"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <g:each in="${userInstance?.favouriteBPMs ?}" status="i" var="bpm">
                                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                            <td><g:link controller="bpm" action="show" id="${bpm.id}">${bpm?.name}</g:link>
                                            <td><g:formatDate date="${bpm?.lastUpdated}"/></td>
                                        </tr>
                                    </g:each>
                                    </tbody>
                                </table>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="tab-pane" id="recents">
                <div class="tab-pane active" id="my-bmps">
                    <div class="control-group ${hasErrors(bean: userInstance, field: 'favouriteBPMs', 'error')}  col-xs-12">
                        <div class="controls" style="font-style: italic">
                            <ul class="one-to-many">
                                <table class="table table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th><g:message code="bpm.name.label" default="Name"/></th>
                                        <th><g:message code="bpm.lastUpdated.label" default="Last Updated"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <g:each in="${userInstance?.bpms.sort{a,b-> a.lastUpdated.compareTo(b.lastUpdated)}.reverse()}" status="i" var="bpm">
                                            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                                <td><g:link controller="bpm" action="show" id="${bpm.id}">${bpm?.name}</g:link>
                                                <td><g:formatDate date="${bpm?.lastUpdated}"/></td>
                                            </tr>
                                        </g:each>
                                    </tbody>
                                </table>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="tab-pane" id="temporals">
                <div class="tab-pane active" id="my-bmps">
                    <div class="control-group ${hasErrors(bean: userInstance, field: 'favouriteBPMs', 'error')}  col-xs-12">
                        <div class="controls" style="font-style: italic">
                            <ul class="one-to-many">
                                <table class="table table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th><g:message code="bpm.name.label" default="Name"/></th>
                                        <th><g:message code="bpm.lastUpdated.label" default="Last Updated"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <g:each in="${userInstance?.bpms}" status="i" var="bpm">
                                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                            <g:if test = "${bpm.temporal == true}">
                                                <td><g:link controller="bpm" action="show" id="${bpm.id}">${bpm?.name}</g:link>
                                                <td><g:formatDate date="${bpm?.lastUpdated}"/></td>
                                            </g:if>
                                        </tr>
                                    </g:each>
                                    </tbody>
                                </table>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>