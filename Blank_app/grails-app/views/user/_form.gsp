<%@ page import="blank.User" %>


<div class="control-group ${hasErrors(bean: userInstance, field: 'name', 'error')} required col-xs-12"
     xmlns="http://www.w3.org/1999/html">
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

<div>
    </li><g:hiddenField name="id"/>
    <g:link class="edit btn btn-sm btn-pink " action="getModelTabs" id="${userInstance?.id}">
        <i class="icon-trash icon-pencil"></i>
        <g:message code="default.button.tabsModels.label" default="Models Manager"/>
    </g:link>
</div>

%{--
<div class="control-group ${hasErrors(bean: userInstance, field: 'models', 'error')}  col-xs-12">

    <label class="control-label">
        <g:message code="user.models.label" default="Models"/>
    </label>

    <div class="controls">

        <div class="controls" style="font-style: italic">
            <ul class="one-to-many">
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th><g:message code="model.name.label" default="Name"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${userInstance?.models?.sort { a, b -> a.name.compareTo(b.name) } ?}" status="i"
                            var="model">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                            <td><g:link controller="model" action="show"
                                        id="${model.id}">${model?.name}</g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </ul>
        </div>
    </div>

    <g:link class="btn btn-sm btn-purple" controller="model" action="create" id="${userInstance?.id}">
        <i class="icon-trash icon-pencil"></i>
        <g:message code="default.button.tabsModels.label" default="New Model"/>
    </g:link>

</div>



<div class="control-group ${hasErrors(bean: userInstance, field: 'favourites', 'error')}  col-xs-12">

    <label class="control-label">
        <g:message code="user.add.label" default="Favourites"/>
    </label>

    <div class="controls">
        <div class="controls" style="font-style: italic">
            <ul class="one-to-many">
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th><g:message code="model.name.label" default="Name"/></th>
                        <th><g:message code="model.user.label" default="User"/></th>
                        <th><g:message code="user.remove.fav" default="Remove from favourites"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${userInstance?.favourites ?}" status="i" var="model">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                            <td><g:link controller="model" action="show"
                                        id="${model.id}">${model?.name}</g:link></td>

                            <td><g:link controller="user" action="show"
                                        id="${model?.user?.id}">${model?.user?.login}</g:link></td>

                            <td><g:link controller="user" action="unmarkFavourite" id="unmark"
                                        params="${[userId: userInstance?.id, modelId: model.id]}">Remove</g:link></li></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </ul>
        </div>
        <g:link class="btn btn-sm btn-purple" action="getModels" id="${userInstance?.id}">
            <i class="icon-trash icon-pencil"></i>
            <g:message code="default.button.tabsModels.label" default="Add Favourites"/>
        </g:link>
    </div>
    </div>
</div>
--}%
