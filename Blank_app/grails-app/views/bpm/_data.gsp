<%@ page import="blank.Bpm" %>

<div class="control-group ${hasErrors(bean: bpmInstance, field: 'name', 'error')}  col-xs-12">

    <label class="control-label" for="name" style="font-weight: bold">

        <g:message code="bpm.name.label" default="Name"/>
    </label>

    <div class="controls" style="font-style: italic">
        <g:message name="name" message="${bpmInstance?.name}"/>
    </div>

</div>

<div class="control-group ${hasErrors(bean: bpmInstance, field: 'user', 'error')} required col-xs-12">

    <label class="control-label" for="user" style="font-weight: bold">
        <g:message code="bpm.user.label" default="Owner"/>
    </label>

    <div class="controls" style="font-style: italic">
        <td><g:link action="show" controller="user"
                    id="${bpmInstance.user?.id}">${fieldValue(bean: bpmInstance, field: "user.login")}</g:link></td>

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


<div class="control-group ${hasErrors(bean: bpmInstance, field: 'temporal', 'error')} col-xs-12">
    <label class="control-label" for="temporal" style="font-weight: bold">
        <g:message code="bpm.name.label" default="Temporal"/>
    </label>

    <div class="controls" style="font-style: italic">
        <g:checkBox id="temporal" name="temporal" value="${bpmInstance?.temporal}" disabled=""/>

    </div>
</div>
