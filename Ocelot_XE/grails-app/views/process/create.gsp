
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>New Process Instance</title>
    </head>

    <g:form controller="process" action="save" name="save" id="${processDefinitionId}">
        <table class="table table-striped table-bordered" align="center">
            <tr>
                <th class="panel-title"><g:message align="center" code="form.field.label" default="Field"/></th>
                <th class="panel-title"><g:message code="form.field.value" default="Value"/></th>
            </tr>
            <g:each in="${startFormData}" var="property">
                <tr>
                    <td width="20%"><g:message code="${property.label}" default="${property.label}"/><span class="required-indicator">*</span></td>
                    <g:if test="${property.type.toString().substring(0, property.type.toString().indexOf('@'))=='org.camunda.bpm.engine.impl.form.type.StringFormType'}">
                        <td><g:textField name="${property.id}" value="${property.defaultValue}" datatype="property.type"/></td>
                    </g:if>
                    <g:elseif test="${property.type.toString().substring(0, property.type.toString().indexOf('@'))=='org.camunda.bpm.engine.impl.form.type.LongFormType'}">
                        <td><g:select from="${(1..1000)}" name="${property.id}" value="${property.defaultValue}"/></td>
                    </g:elseif>
                    <g:elseif test="${property.type.toString().substring(0, property.type.toString().indexOf('@'))=='org.camunda.bpm.engine.impl.form.type.BooleanFormType'}">
                        <td><g:checkBox name="${property.id}" value="${property.defaultValue}" datatype="property.type"/></td>
                    </g:elseif>
                </tr>
            </g:each>
        </table>
        <g:submitButton name="update" value="Start process" />
    </g:form>


</html>