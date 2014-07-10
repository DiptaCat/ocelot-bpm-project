<%@ page import="blank.User" %>


<div class="control-group ${hasErrors(bean: userInstance, field: 'login', 'error')}  col-xs-12">
    <label class="control-label" for="login" style="font-weight: bold">
        <g:message code="user.login.label" default="Login"/>
    </label>

    <div class="controls" style="font-style: italic">
        <g:message name="login" message="${userInstance?.login}"/>
        <g:message name="" message="${userInstance?.favouriteBPMs?.size()}"/>
    </div>
</div>


<div class="control-group ${hasErrors(bean: userInstance, field: 'name', 'error')}  col-xs-12">
    <label class="control-label" for="name" style="font-weight: bold">
        <g:message code="user.name.label" default="Name"/>
    </label>

    <div class="controls" style="font-style: italic">
        <g:message name="name" message="${userInstance?.name}"/>
    </div>
</div>


<div class="control-group ${hasErrors(bean: userInstance, field: 'bpms', 'error')}  col-xs-12">
    <label class="control-label" style="font-weight: bold">
        <g:message code="user.bpms.label" default="BPMs"/>
    </label>

    <div class="controls" style="font-style: italic">
        <ul class="one-to-many">
            <g:each in="${userInstance?.bpms ?}" var="bpm">
                <li><g:link controller="bpm" action="show" id="${bpm.id}">${bpm?.name}</g:link></li>
            </g:each>
        </ul>
    </div>
</div>

<div class="control-group ${hasErrors(bean: userInstance, field: 'favouriteBPMs', 'error')}  col-xs-12">
    <label class="control-label" style="font-weight: bold">
        <g:message code="user.favouriteBpms.label" default="Favourite BPMs"/>
        "${userInstance?.favouriteBPMs.size()}"
    </label>

    <div class="controls" style="font-style: italic">
        <ul class="one-to-many">
            <g:each in="${userInstance?.getFavourites() ?}" var="bpm">
                <li><g:link controller="bpm" action="show" id="${bpm.id}">${bpm?.name}</g:link></li>
            </g:each>
        </ul>
    </div>
</div>


<div class="control-group ${hasErrors(bean: userInstance, field: 'dateCreated', 'error')}  col-xs-12">
    <label class="control-label" style="font-weight: bold">
        <g:message code="user.dateCreated.label" default="Date Created"/>
    </label>

    <div class="controls" style="font-style: italic">
        <g:message name="name" message="${userInstance?.dateCreated}"/>
    </div>
</div>