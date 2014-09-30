<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
<div class="page-header">
    <h1><g:message code="default.create.label" args="[entityName]" /></h1>
</div>

<div class="row-fluid wizard-actions">
    <g:link class="btn btn-sm btn-purple" action="list"><i class="icon-list"/></i> <g:message code="default.list.label" args="[entityName]" /></g:link>
</div>

<div id="create-${domainClass.propertyName}" class="content scaffold-create" role="main">
    <g:if test="\${flash.message}">
        <div class="alert alert-\${flash.messagetype?:'info'} message" role="status"><button data-dismiss="alert" class="close" type="button">×</button>\${flash.message}</div>
    </g:if>
    <g:hasErrors bean="\${${propertyName}}">
        <ul class="errors" role="alert">
            <g:eachError bean="\${${propertyName}}" var="error">
                <li <g:if test="\${error in org.springframework.validation.FieldError}">data-field-id="\${error.field}"</g:if>><g:message error="\${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form class="form-horizontal" action="save" <%= multiPart ? ' enctype="multipart/form-data"' : '' %>>
    <fieldset class="form">
        <g:render template="form"/>
    </fieldset>
    <fieldset class="form-actions">
        <button type="submit" name="create" class="btn btn-sm btn-success save">
            <i class="icon-ok"></i>
            \${message(code: 'default.button.create.label', default: 'Create')}
        </button>
        <button type="submit" name="_action_list" class="btn btn-sm">
            \${message(code: 'default.button.cancel.label', default: 'Cancel')}
        </button>
    </fieldset>
    </g:form>
</div>
</body>
</html>
