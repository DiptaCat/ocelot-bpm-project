<%@ page import="modeller.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'bpmn', 'error')} ">
	<label for="bpmn">
		<g:message code="user.bpmn.label" default="Bpmn" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${userInstance?.bpmn?}" var="b">
    <li><g:link controller="bpmn" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="bpmn" action="create" params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'bpmn.label', default: 'Bpmn')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="user.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${userInstance?.name}"/>

</div>

