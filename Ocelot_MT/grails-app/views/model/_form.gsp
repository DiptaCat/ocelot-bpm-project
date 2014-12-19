<%@ page import="ocelot.Member; ocelot.Member" %>

<div class="control-group ${hasErrors(bean: modelInstance, field: 'name', 'error')} required col-xs-12">
	<label class="control-label" for="name">
		<g:message code="model.name.label" default="Name"/>
		<span class="required-indicator">*</span>
	</label>

	<div class="controls">
		<g:textField name="name" required="" value="${modelInstance?.name}"/>

	</div>
</div>


<div class="control-group ${hasErrors(bean: modelInstance, field: 'user', 'error')} required col-xs-12">
	<label class="control-label" for="user">
		<g:message code="model.user.label" default="User"/>
		<span class="required-indicator">*</span>
	</label>

	<div class="controls">

		<g:select id="user" name="user.id" from="${Member.list()}" optionKey="id" required=""
				  value="${modelInstance?.user?.id}" class="many-to-one"/>

	</div>
</div>


<div class="control-group ${hasErrors(bean: modelInstance, field: 'temporal', 'error')} col-xs-12">
	<label class="control-label" for="temporal">
		<g:message code="model.temporal.label" default="Temporal"/>
	</label>

	<div class="controls">
		<g:checkBox id="temporal" name="temporal" value="${modelInstance?.temporal}"/>

	</div>
</div>
