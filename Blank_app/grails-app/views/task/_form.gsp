<%@ page import="blank.Task" %>




<div class="control-group ${hasErrors(bean: taskInstance, field: 'name', 'error')} required col-xs-12">
    <label class="control-label" for="name">
        <g:message code="task.name.label" default="Name" />
        <span class="required-indicator">*</span>
    </label>
    <div class="controls">
        <g:textField name="name" required="" value="${taskInstance?.name}"/>

    </div>
</div>


<div class="control-group ${hasErrors(bean: taskInstance, field: 'user', 'error')} required col-xs-12">
    <label class="control-label" for="user">
        <g:message code="task.user.label" default="User" />
        <span class="required-indicator">*</span>
    </label>
    <div class="controls">
        <g:select id="user" name="user.id" from="${blank.User.list()}" optionKey="id" required="" value="${taskInstance?.user?.id}" class="many-to-one"/>

    </div>
</div>

