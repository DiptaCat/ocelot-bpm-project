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


<div class="control-group ${hasErrors(bean: userInstance, field: 'models', 'error')}  col-xs-12">

    <label class="control-label">
        <g:message code="user.models.label" default="models"/>
    </label>

    <div class="controls">

        <ul class="one-to-many">
            <g:each in="${userInstance?.models ?}" var="b">
                <li><g:link controller="model" action="show" id="${b.id}">${b?.name}</g:link></li>
            </g:each>
            <li class="add">
                <g:link controller="model" action="create"
                        params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'model.label', default: 'model')])}</g:link>
            </li>
        </ul>

    </div>
</div>

<div class="control-group ${hasErrors(bean: userInstance, field: 'favourites', 'error')}  col-xs-12">

    <label class="control-label">
        <g:message code="user.add.label" default="Favourites"/>
    </label>

    <div class="controls">

        <ul class="one-to-many">
            <g:each in="${userInstance?.favourites ?}" var="b">
                <li><g:link controller="model" action="show" id="${b.id}">${b?.name}</g:link></li>
            </g:each>

            <li class="add">
                <g:link controller="user" action="getModels"
                        id="${userInstance?.id}">${message(code: 'user.add.label', default: "Add Favourite")}</g:link>
            </li>
        </ul>

    </div>
</div>
