<% import grails.persistence.Event %>
<%=packageName%>
<!DOCTYPE html>
%{--<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-${domainClass.propertyName}" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="\${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-${domainClass.propertyName}" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="\${flash.message}">
			<div class="message" role="status">\${flash.message}</div>
			</g:if>
			<ol class="property-list ${domainClass.propertyName}">
			<%  excludedProps = Event.allEvents.toList() << 'id' << 'version'
				allowedNames = domainClass.persistentProperties*.name << 'dateCreated' << 'lastUpdated'
				props = domainClass.properties.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) && (domainClass.constrainedProperties[it.name] ? domainClass.constrainedProperties[it.name].display : true) }
				Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
				props.each { p -> %>
				<g:if test="\${${propertyName}?.${p.name}}">
				<li class="fieldcontain">
					<span id="${p.name}-label" class="property-label"><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" /></span>
					<%  if (p.isEnum()) { %>
						<span class="property-value" aria-labelledby="${p.name}-label"><g:fieldValue bean="\${${propertyName}}" field="${p.name}"/></span>
					<%  } else if (p.oneToMany || p.manyToMany) { %>
						<g:each in="\${${propertyName}.${p.name}}" var="${p.name[0]}">
						<span class="property-value" aria-labelledby="${p.name}-label"><g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${p.name[0]}.id}">\${${p.name[0]}?.encodeAsHTML()}</g:link></span>
						</g:each>
					<%  } else if (p.manyToOne || p.oneToOne) { %>
						<span class="property-value" aria-labelledby="${p.name}-label"><g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${propertyName}?.${p.name}?.id}">\${${propertyName}?.${p.name}?.encodeAsHTML()}</g:link></span>
					<%  } else if (p.type == Boolean || p.type == boolean) { %>
						<span class="property-value" aria-labelledby="${p.name}-label"><g:formatBoolean boolean="\${${propertyName}?.${p.name}}" /></span>
					<%  } else if (p.type == Date || p.type == java.sql.Date || p.type == java.sql.Time || p.type == Calendar) { %>
						<span class="property-value" aria-labelledby="${p.name}-label"><g:formatDate date="\${${propertyName}?.${p.name}}" /></span>
					<%  } else if (!p.type.isArray()) { %>
						<span class="property-value" aria-labelledby="${p.name}-label"><g:fieldValue bean="\${${propertyName}}" field="${p.name}"/></span>
					<%  } %>
				</li>
				</g:if>
			<%  } %>
			</ol>
			<g:form url="[resource:${propertyName}, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="\${${propertyName}}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="\${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>--}%
<html>
<head>
	<meta name="layout" content="main">
	<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}"/>
	<title><g:message code="default.show.label" args="[entityName]"/> \${${propertyName}.toString()}</title>
</head>

<body>
<div class="page-header">
	<h1><g:message code="default.show.label" args="[entityName]"/></h1>
</div>

<div class="row-fluid wizard-actions">
	<g:link class="btn btn-sm btn-purple" action="list"><i class="icon-list"/></i> <g:message code="default.list.label"
																							  args="[entityName]"/></g:link>
	<g:link class="btn btn-sm btn-info" action="create"><i class="icon-plus"/></i> <g:message code="default.new.label"
																							  args="[entityName]"/></g:link>
</div>

<div id="show-${domainClass.propertyName}" class="content scaffold-show" role="main">
	<g:if test="\${flash.message}">
		<div class="alert alert-\${flash.messagetype?:'info'} message" role="status"><button data-dismiss="alert"
																							 class="close"
																							 type="button">×</button>\${flash.message}
		</div>
	</g:if>
	<div class="form-horizontal">
		<fieldset>
			<g:render template="data" model="\${['${propertyName}': ${propertyName}]}"/>
		</fieldset>
	</div>
	<g:form>
		<div class="form-actions">
			<g:hiddenField name="id" value="\${${propertyName}?.id}"/>
			<g:link class="edit btn btn-sm btn-primary" action="edit" id="\${${propertyName}?.id}">
				<i class="icon-trash icon-pencil"></i>
				<g:message code="default.button.edit.label" default="Edit"/>
			</g:link>
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
		</div>
	</g:form>
</div>
</body>
</html>
