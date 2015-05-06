
<%@ page import="example.VacationRequest" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'vacationRequest.label', default: 'VacationRequest')}" />
</head>
<body>
<div class="page-header">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
</div>
<div class="row-fluid wizard-actions">
    <g:link class="btn btn-sm btn-purple" action="index"><i class="icon-list"/></i> <g:message code="default.list.label" args="[entityName]" /></g:link>
</div>
<div id="show-vacationRequest" class="content scaffold-show" role="main">
    <g:if test="${flash.message}">
        <div class="alert alert-${flash.messagetype?:'info'} message" role="status"><button data-dismiss="alert" class="close" type="button">×</button>${flash.message}</div>
    </g:if>
    <div class="form-horizontal">
        <fieldset>
            <g:render template="data" model="${['vacationRequestInstance': vacationRequestInstance]}" />
        </fieldset>
    </div>
</div>
</body>
</html>
