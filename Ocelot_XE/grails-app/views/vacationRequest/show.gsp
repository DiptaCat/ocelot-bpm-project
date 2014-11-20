<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'vacationRequest.label', default: 'VacationRequest')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-vacationRequest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<div id="show-vacationRequest" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list vacationRequest">
			
				<g:if test="${vacationRequestInstance?.employeeName}">
				<li class="fieldcontain">
					<span id="employeeName-label" class="property-label"><g:message code="vacationRequest.employeeName.label" default="Employee Name" /></span>
					
						<span class="property-value" aria-labelledby="employeeName-label"><g:fieldValue bean="${vacationRequestInstance}" field="employeeName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${vacationRequestInstance?.numberOfDays}">
				<li class="fieldcontain">
					<span id="numberOfDays-label" class="property-label"><g:message code="vacationRequest.numberOfDays.label" default="Number Of Days" /></span>
					
						<span class="property-value" aria-labelledby="numberOfDays-label"><g:fieldValue bean="${vacationRequestInstance}" field="numberOfDays"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${vacationRequestInstance?.vacationDescription}">
				<li class="fieldcontain">
					<span id="vacationDescription-label" class="property-label"><g:message code="vacationRequest.vacationDescription.label" default="Vacation Description" /></span>
					
						<span class="property-value" aria-labelledby="vacationDescription-label"><g:fieldValue bean="${vacationRequestInstance}" field="vacationDescription"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${vacationRequestInstance?.approvalStatus}">
				<li class="fieldcontain">
					<span id="approvalStatus-label" class="property-label"><g:message code="vacationRequest.approvalStatus.label" default="Approval Status" /></span>
					
						<span class="property-value" aria-labelledby="approvalStatus-label"><g:fieldValue bean="${vacationRequestInstance}" field="approvalStatus"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${vacationRequestInstance?.approvalRemark}">
				<li class="fieldcontain">
					<span id="approvalRemark-label" class="property-label"><g:message code="vacationRequest.approvalRemark.label" default="Approval Remark" /></span>
					
						<span class="property-value" aria-labelledby="approvalRemark-label"><g:fieldValue bean="${vacationRequestInstance}" field="approvalRemark"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${vacationRequestInstance?.resendRequest}">
				<li class="fieldcontain">
					<span id="resendRequest-label" class="property-label"><g:message code="vacationRequest.resendRequest.label" default="Resend Request" /></span>
					
						<span class="property-value" aria-labelledby="resendRequest-label"><g:formatBoolean boolean="${vacationRequestInstance?.resendRequest}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${vacationRequestInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="vacationRequest.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${vacationRequestInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${vacationRequestInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="vacationRequest.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${vacationRequestInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
			</ol>
		</div>
	</body>
</html>
