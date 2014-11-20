



<div class="fieldcontain ${hasErrors(bean: vacationRequestInstance, field: 'employeeName', 'error')} required">
	<label for="employeeName">
		<g:message code="vacationRequest.employeeName.label" default="Employee Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="employeeName" maxlength="50" required="" value="${vacationRequestInstance?.employeeName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: vacationRequestInstance, field: 'numberOfDays', 'error')} required">
	<label for="numberOfDays">
		<g:message code="vacationRequest.numberOfDays.label" default="Number Of Days" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="numberOfDays" from="${1..14}" class="range" required="" value="${fieldValue(bean: vacationRequestInstance, field: 'numberOfDays')}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: vacationRequestInstance, field: 'vacationDescription', 'error')} required">
	<label for="vacationDescription">
		<g:message code="vacationRequest.vacationDescription.label" default="Vacation Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="vacationDescription" cols="40" rows="5" maxlength="255" required="" value="${vacationRequestInstance?.vacationDescription}"/>

</div>

<g:if test="${vacationRequestInstance.id}">

<div class="fieldcontain ${hasErrors(bean: vacationRequestInstance, field: 'approvalStatus', 'error')} required">
	<label for="approvalStatus">
		<g:message code="vacationRequest.approvalStatus.label" default="Approval Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="approvalStatus" required="" value="${vacationRequestInstance?.approvalStatus}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: vacationRequestInstance, field: 'approvalRemark', 'error')} ">
	<label for="approvalRemark">
		<g:message code="vacationRequest.approvalRemark.label" default="Approval Remark" />
		
	</label>
	<g:textField name="approvalRemark" value="${vacationRequestInstance?.approvalRemark}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: vacationRequestInstance, field: 'resendRequest', 'error')} ">
	<label for="resendRequest">
		<g:message code="vacationRequest.resendRequest.label" default="Resend Request" />
		
	</label>
	<g:checkBox name="resendRequest" value="${vacationRequestInstance?.resendRequest}" />

</div>

</g:if>

