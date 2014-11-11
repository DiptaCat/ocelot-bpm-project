<%@ page import="ocelot.Modeler; ocelot.PaletteItem" %>

<div class="control-group ${hasErrors(bean: properties, field: 'error')}  col-xs-12"
	 xmlns="http://www.w3.org/1999/html">

<label class="control-label" for="props" style="font-weight: bold">
	<g:message code="element.label" default="Properties"/>
</label>

<table class="controls">
	<thead>
		<g:sortableColumn property="name" title="Name"/>
	</thead>
	<tbody>
	<g:each in="${properties}" status="i" var="prop">
		<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			<td><g:message name="props" message="${prop}"/></td>
		</tr>
	</g:each>
	</tbody>
</table>
</div>
