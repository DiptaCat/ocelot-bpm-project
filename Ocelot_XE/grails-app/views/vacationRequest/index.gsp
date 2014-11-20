

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'vacationRequest.label', default: 'VacationRequest')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-vacationRequest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div class="tab-content">
        <div class="tab-pane active" id="list-vacationRequest", role="main">

            <div class="controls" style="font-style: italic">
                <ul class="one-to-many">


			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>

						<g:sortableColumn property="employeeName" title="${message(code: 'default.id.label', default: 'Id')}" />

						<g:sortableColumn property="employeeName" title="${message(code: 'vacationRequest.employeeName.label', default: 'Name')}" />
					
						<g:sortableColumn property="numberOfDays" title="${message(code: 'vacationRequest.numberOfDays.label', default: 'Days')}" />
					
						<g:sortableColumn property="vacationDescription" title="${message(code: 'vacationRequest.vacationDescription.label', default: 'Description')}" />
					
						<g:sortableColumn property="approvalStatus" title="${message(code: 'vacationRequest.approvalStatus.label', default: 'Status')}" />
					
						<g:sortableColumn property="approvalRemark" title="${message(code: 'vacationRequest.approvalRemark.label', default: 'Remark')}" />
					
						<g:sortableColumn property="resendRequest" title="${message(code: 'vacationRequest.resendRequest.label', default: 'Resend?')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${vacationRequestInstanceList}" status="i" var="vacationRequestInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${vacationRequestInstance.id}">${fieldValue(bean: vacationRequestInstance, field: "id")}</g:link></td>

						<td>${fieldValue(bean: vacationRequestInstance, field: "employeeName")}</td>
					
						<td>${fieldValue(bean: vacationRequestInstance, field: "numberOfDays")}</td>
					
						<td>${fieldValue(bean: vacationRequestInstance, field: "vacationDescription")}</td>
					
						<td>${fieldValue(bean: vacationRequestInstance, field: "approvalStatus")}</td>
					
						<td>${fieldValue(bean: vacationRequestInstance, field: "approvalRemark")}</td>
					
						<td><g:formatBoolean boolean="${vacationRequestInstance.resendRequest}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${vacationRequestInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
