<%@ page import="ocelot.Model" %>

<div class="control-group ${hasErrors(bean: modelInstance, field: 'name', 'error')}  col-xs-12">

	<label class="control-label" for="name" style="font-weight: bold">

		<g:message code="model.name.label" default="Name"/>
	</label>

	<div class="controls" style="font-style: italic">
		<g:message name="name" message="${modelInstance?.name}"/>
	</div>

</div>

<div class="control-group ${hasErrors(bean: modelInstance, field: 'user', 'error')} required col-xs-12">

	<label class="control-label" for="user" style="font-weight: bold">
		<g:message code="model.user.label" default="Owner"/>
	</label>

	<div class="controls" style="font-style: italic">
		<td><g:link action="show" controller="member"
					id="${modelInstance.user?.id}">${fieldValue(bean: modelInstance, field: "user.login")}</g:link></td>

	</div>
</div>

<div class="control-group ${hasErrors(bean: modelInstance, field: 'dateCreated', 'error')}  col-xs-12">
	<label class="control-label" for="name" style="font-weight: bold">
		<g:message code="model.dateCreated.label" default="Date Created"/>
	</label>

	<div class="controls" style="font-style: italic">
		<g:message name="name" message="${modelInstance?.dateCreated}"/>
	</div>
</div>


<div class="control-group ${hasErrors(bean: modelInstance, field: 'lastUpdated', 'error')}  col-xs-12">
	<label class="control-label" for="name" style="font-weight: bold">
		<g:message code="model.lastUpdated.label" default="Date Updated"/>
	</label>

	<div class="controls" style="font-style: italic">
		<g:message name="name" message="${modelInstance?.lastUpdated}"/>
	</div>
</div>


<div class="control-group ${hasErrors(bean: modelInstance, field: 'temporal', 'error')} col-xs-12">
	<label class="control-label" for="temporal" style="font-weight: bold">
		<g:message code="model.temporal.label" default="Temporal"/>
	</label>

	<div class="controls" style="font-style: italic">
		<g:checkBox id="temporal" name="temporal" value="${modelInstance?.temporal}" disabled=""/>

	</div>
</div>
