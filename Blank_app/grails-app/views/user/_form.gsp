<%@ page import="blank.User" %>


<div class="control-group ${hasErrors(bean: userInstance, field: 'name', 'error')} required col-xs-12">
    <label class="control-label" for="name">
        <g:message code="user.name.label" default="Name"/>
        <span class="required-indicator">*</span>
    </label>

    <div class="controls">
        <g:textField name="name" required="" value="${userInstance?.name}"/>

    </div>
</div>


<div class="control-group ${hasErrors(bean: userInstance, field: 'login', 'error')} required col-xs-12">
    <label class="control-label" for="login">
        <g:message code="user.login.label" default="Login"/>
        <span class="required-indicator">*</span>
    </label>

    <div class="controls">
        <g:textField name="login" required="" value="${userInstance?.login}"/>

    </div>
</div>


<div class="control-group ${hasErrors(bean: userInstance, field: 'bpms', 'error')}  col-xs-12">
    <label class="control-label" for="bpms">
        <g:message code="user.bpms.label" default="Bpms"/>

    </label>

    <div class="controls">

        <ul class="one-to-many">
            <g:each in="${userInstance?.bpms ?}" var="b">
                <li><g:link controller="bpm" action="show" id="${b.id}">${b?.name}</g:link></li>
            </g:each>
            <li class="add">
                <g:link controller="bpm" action="create"
                        params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'bpm.label', default: 'Bpm')])}</g:link>
            </li>
        </ul>

    </div>
</div>
