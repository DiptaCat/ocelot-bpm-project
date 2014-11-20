<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="default.home.label"/></title>
</head>

<body>
        <div class="body">
            <h1><g:message code="default.approve.label" args="[entityName]" default="Approve {0}"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:form action="performApproval" >
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vacationRequest.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: vacationRequestInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vacationRequest.employeeName.label" default="Employee Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: vacationRequestInstance, field: "employeeName")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vacationRequest.numberOfDays.label" default="Number Of Days" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: vacationRequestInstance, field: "numberOfDays")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vacationRequest.vacationDescription.label" default="Vacation Description" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: vacationRequestInstance, field: "vacationDescription")}</td>
                            
                        </tr>
                    
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="approvalStatus"><g:message code="vacationRequest.approvalStatus.label" default="Approval Status" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vacationRequestInstance, field: 'approvalStatus', 'errors')}">
                                    <g:select name="approvalStatus" from="${['APPROVED', 'REJECTED']}" value="${vacationRequestInstance?.approvalStatus}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="approvalRemark"><g:message code="vacationRequest.approvalRemark.label" default="Approval Remark" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vacationRequestInstance, field: 'approvalRemark', 'errors')}">
                                    <g:textArea name="approvalRemark" cols="40" rows="5" value="${vacationRequestInstance?.approvalRemark}" />
                                </td>
                            </tr>
                    
                    </tbody>
                </table>
            </div>
            		<div class="buttons">
                    <span class="button"><g:submitButton name="save" class="save" value="${message(code: 'default.button.save.label', default: 'Save')}" /></span>
                    <span class="button"><g:submitButton name="complete" class="save" value="${message(code: 'default.button.complete.label', default: 'Complete')}" /></span>
                </div>
                <g:hiddenField name="id" value="${vacationRequestInstance?.id}" />
                <g:hiddenField name="taskId" value="${taskId}" />
            </g:form>            
        </div>
    </body>
</html>
