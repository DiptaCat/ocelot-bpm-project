<%@ page import="example.VacationRequest" %>




<div class="control-group ${hasErrors(bean: vacationRequestInstance, field: 'employeeName', 'error')} required col-xs-12">
    <label class="control-label" for="employeeName">
        <g:message code="vacationRequest.employeeName.label" default="Employee Name" />
        <span class="required-indicator">*</span>
    </label>
    <div class="controls">
        <g:textField name="employeeName" maxlength="50" required="" value="${vacationRequestInstance?.employeeName}"/>

    </div>
</div>


<div class="control-group ${hasErrors(bean: vacationRequestInstance, field: 'numberOfDays', 'error')} required col-xs-12">
    <label class="control-label" for="numberOfDays">
        <g:message code="vacationRequest.numberOfDays.label" default="Number Of Days" />
        <span class="required-indicator">*</span>
    </label>
    <div class="controls">
        <g:select name="numberOfDays" from="${1..14}" class="range" required="" value="${fieldValue(bean: vacationRequestInstance, field: 'numberOfDays')}"/>

    </div>
</div>


<div class="control-group ${hasErrors(bean: vacationRequestInstance, field: 'vacationDescription', 'error')} required col-xs-12">
    <label class="control-label" for="vacationDescription">
        <g:message code="vacationRequest.vacationDescription.label" default="Vacation Description" />
        <span class="required-indicator">*</span>
    </label>
    <div class="controls">
        <g:textArea name="vacationDescription" cols="40" rows="5" maxlength="255" required="" value="${vacationRequestInstance?.vacationDescription}"/>

    </div>
</div>

<g:if test="${vacationRequestInstance.id}">

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


<div class="control-group ${hasErrors(bean: vacationRequestInstance, field: 'resendRequest', 'error')}  col-xs-12">
    <label class="control-label" for="resendRequest">
        <g:checkBox name="resendRequest" value="${vacationRequestInstance?.resendRequest}" />

        <span class="lbl"> <g:message code="vacationRequest.resendRequest.label" default="Resend Request" /></span>
        
    </label>
</div>

</g:if>