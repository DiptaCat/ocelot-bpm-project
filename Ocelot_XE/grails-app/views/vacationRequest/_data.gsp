
			
					<g:if test="${vacationRequestInstance?.employeeName}">
						<div class="control-group col-xs-12">
							<label class="property-key"><g:message code="vacationRequest.employeeName.label" default="Employee Name" /></label>
							<span class="property-value" aria-labelledby="employeeName-label"><g:fieldValue bean="${vacationRequestInstance}" field="employeeName"/></span>
						</div>
					</g:if>
				
					<g:if test="${vacationRequestInstance?.numberOfDays}">
						<div class="control-group col-xs-12">
							<label class="property-key"><g:message code="vacationRequest.numberOfDays.label" default="Number Of Days" /></label>
							<span class="property-value" aria-labelledby="numberOfDays-label"><g:fieldValue bean="${vacationRequestInstance}" field="numberOfDays"/></span>
						</div>
					</g:if>
				
					<g:if test="${vacationRequestInstance?.vacationDescription}">
						<div class="control-group col-xs-12">
							<label class="property-key"><g:message code="vacationRequest.vacationDescription.label" default="Vacation Description" /></label>
							<span class="property-value" aria-labelledby="vacationDescription-label"><g:fieldValue bean="${vacationRequestInstance}" field="vacationDescription"/></span>
						</div>
					</g:if>
				
					<g:if test="${vacationRequestInstance?.approvalStatus}">
						<div class="control-group col-xs-12">
							<label class="property-key"><g:message code="vacationRequest.approvalStatus.label" default="Approval Status" /></label>
							<span class="property-value" aria-labelledby="approvalStatus-label"><g:fieldValue bean="${vacationRequestInstance}" field="approvalStatus"/></span>
						</div>
					</g:if>
				
					<g:if test="${vacationRequestInstance?.approvalRemark}">
						<div class="control-group col-xs-12">
							<label class="property-key"><g:message code="vacationRequest.approvalRemark.label" default="Approval Remark" /></label>
							<span class="property-value" aria-labelledby="approvalRemark-label"><g:fieldValue bean="${vacationRequestInstance}" field="approvalRemark"/></span>
						</div>
					</g:if>
				
					<g:if test="${vacationRequestInstance?.resendRequest}">
						<div class="control-group col-xs-12">
							<label class="property-key"><g:message code="vacationRequest.resendRequest.label" default="Resend Request" /></label>
							<span class="property-value" aria-labelledby="resendRequest-label"><g:formatBoolean boolean="${vacationRequestInstance?.resendRequest}" /></span>
						</div>
					</g:if>
				
					%{--<g:if test="${vacationRequestInstance?.dateCreated}">--}%
						%{--<div class="control-group col-xs-12">--}%
							%{--<label class="property-key"><g:message code="vacationRequest.dateCreated.label" default="Date Created" /></label>--}%
							%{--<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${vacationRequestInstance?.dateCreated}"/></span>--}%
						%{--</div>--}%
					%{--</g:if>--}%
				%{----}%
					%{--<g:if test="${vacationRequestInstance?.lastUpdated}">--}%
						%{--<div class="control-group col-xs-12">--}%
							%{--<label class="property-key"><g:message code="vacationRequest.lastUpdated.label" default="Last Updated" /></label>--}%
							%{--<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${vacationRequestInstance?.lastUpdated}"/></span>--}%
						%{--</div>--}%
					%{--</g:if>--}%
				%{----}%
					%{--<g:if test="${vacationRequestInstance?.processInstanceId}">--}%
						%{--<div class="control-group col-xs-12">--}%
							%{--<label class="property-key"><g:message code="vacationRequest.processInstanceId.label" default="Process Instance Id" /></label>--}%
							%{--<span class="property-value" aria-labelledby="processInstanceId-label"><g:fieldValue bean="${vacationRequestInstance}" field="processInstanceId"/></span>--}%
						%{--</div>--}%
					%{--</g:if>--}%
				