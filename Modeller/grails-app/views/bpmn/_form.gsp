<%@ page import="modeller.Bpmn" %>



<div class="fieldcontain ${hasErrors(bean: bpmnInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="bpmn.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${bpmnInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bpmnInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="bpmn.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${modeller.User.list()}" optionKey="id" required="" value="${bpmnInstance?.user?.id}" class="many-to-one"/>

</div>

