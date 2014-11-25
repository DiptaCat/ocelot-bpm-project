

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="vacationRequest.label"/></title>
</head>
	<body>
    <div class="page-header">
        <h1>Vacations Request List</h1>
    </div>
    <div class="tab-content">
        <div class="tab-pane active" id="list-vacationRequest" role="main">

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
