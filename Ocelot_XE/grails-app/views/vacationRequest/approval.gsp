<%@ page import="example.VacationRequest" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'vacationRequest.label', default: 'VacationRequest')}" />
</head>
<body>
<div class="page-header">
    <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
</div>
<div class="row-fluid wizard-actions">
    <g:link class="btn btn-sm btn-purple" action="index"><i class="icon-list"/></i> <g:message code="default.list.label" args="[entityName]" /></g:link>
</div>

<div id="edit-vacationRequest" class="content scaffold-edit" role="main">
    <g:if test="${flash.message}">
        <div class="alert alert-${flash.messagetype?:'info'} message" role="status"><button data-dismiss="alert" class="close" type="button">×</button>${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${vacationRequestInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${vacationRequestInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form class="form-horizontal" action="performApproval" >
        <g:hiddenField name="id" value="${vacationRequestInstance?.id}" />
        <g:hiddenField name="version" value="${vacationRequestInstance?.version}" />
        <fieldset class="form">
            <div class="control-group ${hasErrors(bean: vacationRequestInstance, field: 'approvalStatus', 'error')}  col-xs-12">
                <label class="control-label" for="approvalStatus">
                    <g:message code="vacationRequest.approvalStatus.label" default="Approval Status" />

                </label>
                <div class="controls">
                    <g:select name="approvalStatus" from="${['APPROVED', 'REJECTED']}" value="${vacationRequestInstance?.approvalStatus}"  />
                </div>
            </div>
            <div class="control-group ${hasErrors(bean: vacationRequestInstance, field: 'approvalRemark', 'error')}  col-xs-12">
                <label class="control-label" for="approvalRemark">
                    <g:message code="vacationRequest.approvalRemark.label" default="Approval Remark" />

                </label>
                <div class="controls">
                    <g:textField name="approvalRemark" value="${vacationRequestInstance?.approvalRemark}"/>

                </div>
            </div>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="save" class="edit btn btn-sm btn-success" value="${message(code: 'default.button.save.label', default: 'Save')}" />
            <g:submitButton name="complete" class="edit btn btn-sm btn-primary" value="${message(code: 'default.button.complete.label', default: 'Complete')}" />
            <g:hiddenField name="id" value="${vacationRequestInstance?.id}" />
            <g:hiddenField name="taskId" value="${taskId}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>