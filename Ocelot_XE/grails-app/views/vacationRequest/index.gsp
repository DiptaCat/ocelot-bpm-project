
<%@ page import="example.VacationRequest" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'vacationRequest.label', default: 'VacationRequest')}" />
</head>
<body>
<div class="page-header">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
</div>

<div id="list-vacationRequest" class="content scaffold-list" role="main">
    <g:if test="${flash.message}">
        <div class="alert alert-${flash.messagetype?:'info'} message" role="status"><button data-dismiss="alert" class="close" type="button">×</button>${flash.message}</div>
    </g:if>
    <table class="table table-striped table-bordered">
        <thead>
        <tr>

            <g:sortableColumn property="id" title="${message(code: 'vacationRequest.id.label', default: 'Id.')}" />

            <g:sortableColumn property="employeeName" title="${message(code: 'vacationRequest.employeeName.label', default: 'Employee Name')}" />
            
            <g:sortableColumn property="numberOfDays" title="${message(code: 'vacationRequest.numberOfDays.label', default: 'Number Of Days')}" />
            
            <g:sortableColumn property="vacationDescription" title="${message(code: 'vacationRequest.vacationDescription.label', default: 'Vacation Description')}" />
            
            <g:sortableColumn property="approvalStatus" title="${message(code: 'vacationRequest.approvalStatus.label', default: 'Approval Status')}" />
            
            <g:sortableColumn property="approvalRemark" title="${message(code: 'vacationRequest.approvalRemark.label', default: 'Approval Remark')}" />
            
            <g:sortableColumn property="resendRequest" title="${message(code: 'vacationRequest.resendRequest.label', default: 'Resend Request')}" />
            
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
    <div class="pagination pagination-right">
        <div class="pagination-content">
            <g:paginate total="${vacationRequestInstanceCount ?: 0}" />
        </div>
    </div>
</div>
</body>
</html>