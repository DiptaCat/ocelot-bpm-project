<%=packageName%>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}"/>
	<title><g:message code="default.edit.label" args="[entityName]"/>  \${${propertyName}.toString()}</title>
</head>

<body>
<div class="page-header">
	<h1><g:message code="default.edit.label" args="[entityName]"/></h1>
</div>

<div class="row-fluid wizard-actions">
	<g:link class="btn btn-sm btn-purple" action="list"><i class="icon-list"/></i> <g:message code="default.list.label"
																							  args="[entityName]"/></g:link>
	<g:link class="btn btn-sm btn-info" action="create"><i class="icon-plus"/></i> <g:message code="default.new.label"
																							  args="[entityName]"/></g:link>
</div>

<div id="edit-${domainClass.propertyName}" class="content scaffold-edit" role="main">
	<g:if test="\${flash.message}">
		<div class="alert alert-\${flash.messagetype?:'info'} message" role="status"><button data-dismiss="alert"
																							 class="close"
																							 type="button">×</button>\${flash.message}
		</div>
	</g:if>
	<g:hasErrors bean="\${${propertyName}}">
		<ul class="errors" role="alert">
			<g:eachError bean="\${${propertyName}}" var="error">
				<li <g:if test="\${error in org.springframework.validation.FieldError}">data-field-id="\${error.field}"</g:if>><g:message
						error="\${error}"/></li>
			</g:eachError>
		</ul>
	</g:hasErrors>
	<g:form class="form-horizontal" method="post" <%=multiPart ? ' enctype="multipart/form-data"' : '' %>>
	<g:hiddenField name="id" value="\${${propertyName}?.id}"/>
	<g:hiddenField name="version" value="\${${propertyName}?.version}"/>
	<fieldset class="form">
		<g:render template="form"/>
	</fieldset>
	<fieldset class="form-actions">
		<button type="submit" name="_action_update" class="btn btn-sm btn-success save">
			<i class="icon-refresh"></i>
			\${message(code: 'default.button.update.label', default: 'Update')}
		</button>
		<button type="submit" name="_action_show" class="btn btn-sm">
			\${message(code: 'default.button.cancel.label', default: 'Cancel')}
		</button>
		<button type="submit" name="_action_delete" class="btn btn-sm btn-danger delete" formnovalidate=""
				onclick="return confirm('\${message(code: '
				default.button.delete.confirm.message
				', default: '
				Are
				you
				sure ? ')}'
				)
				;">
			<i class="icon-trash icon-white"></i>
			\${message(code: 'default.button.delete.label', default: 'Delete')}
		</button>
	</fieldset>
	</g:form>
</div>
</body>
</html>
