<%@ page import="ocelot.User" %>


<div class="control-group ${hasErrors(bean: userInstance, field: 'login', 'error')}  col-xs-12">
    <label class="control-label" for="login" style="font-weight: bold">
        <g:message code="user.login.label" default="Login"/>
    </label>

    <div class="controls" style="font-style: italic">
        <g:message name="login" message="${userInstance?.login}"/>
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


<div class="control-group ${hasErrors(bean: userInstance, field: 'dateCreated', 'error')}  col-xs-12">
    <label class="control-label" style="font-weight: bold">
        <g:message code="user.dateCreated.label" default="Date Created"/>
    </label>

    <div class="controls" style="font-style: italic">
        <g:message name="name" message="${userInstance?.dateCreated}"/>
    </div>
</div>