<%@ page import="blank.Bpm" %>

<div class="control-group ${hasErrors(bean: bpmInstance, field: 'name', 'error')}  col-xs-12">

    <label class="control-label" for="name" style="font-weight: bold">
        <g:message code="bpm.name.label" default="Name" />
    </label>

    <div class="controls">
        <g:message name="name" message="${bpmInstance?.name}"/>
    </div>

</div>

<div class="control-group ${hasErrors(bean: bpmInstance, field: 'dateCreated', 'error')}  col-xs-12">
    <label class="control-label" for="name" style="font-weight: bold">
        <g:message code="bpm.lavel.owner" default="Owner"/>
    </label>

    <div class="controls" style="font-style: italic">
        <g:message name="name" message="${bpmInstance?.user?.login}"/>
    </div>
</div>

<div class="control-group ${hasErrors(bean: bpmInstance, field: 'dateCreated', 'error')}  col-xs-12">
    <label class="control-label" for="name" style="font-weight: bold">
        <g:message code="user.dateCreated.label" default="Date Created"/>
    </label>

    <div class="controls" style="font-style: italic">
        <g:message name="name" message="${bpmInstance?.dateCreated}"/>
    </div>
</div>


<div class="control-group ${hasErrors(bean: bpmInstance, field: 'lastUpdated', 'error')}  col-xs-12">
    <label class="control-label" for="name" style="font-weight: bold">
        <g:message code="user.lastUpdated.label" default="Date Updated"/>
    </label>

    <div class="controls" style="font-style: italic">
        <g:message name="name" message="${bpmInstance?.lastUpdated}"/>
    </div>
</div>

