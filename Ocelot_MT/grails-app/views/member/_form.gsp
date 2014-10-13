<%@ page import="ocelot.Member" %>

<div class="control-group ${hasErrors(bean: memberInstance, field: 'name', 'error')} required col-xs-12">
	<label class="control-label" for="name">
		<g:message code="user.name.label" default="Name"/>
		<span class="required-indicator">*</span>
	</label>

	<div class="controls">
		<g:textField name="name" required="" value="${memberInstance?.name}"/>

	</div>
</div>


<div class="control-group ${hasErrors(bean: memberInstance, field: 'login', 'error')} required col-xs-12">
	<label class="control-label" for="login">
		<g:message code="user.login.label" default="Login"/>
		<span class="required-indicator">*</span>
	</label>

	<div class="controls">
		<g:textField name="login" required="" value="${memberInstance?.login}"/>

	</div>
</div>

<div>
	<g:link class="edit btn btn-sm btn-pink " action="getModelTabs" id="${memberInstance?.id}">
		<i class="glyphicon glyphicon-pencil"></i>
		<g:message code="default.button.tabsModel.label" default="Models Manager"/>
	</g:link>
</div>