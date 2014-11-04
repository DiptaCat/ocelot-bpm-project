<%@ page import="ocelot.Modeler; ocelot.PaletteItem" %>

<div class="control-group ${hasErrors(bean: properties, field: 'props', 'error')}  col-xs-12"
	 xmlns="http://www.w3.org/1999/html">

	<label class="control-label" for="props" style="font-weight: bold">
		<g:message code="element.label" default="Properties"/>
	</label>

	<div class="controls">

	</div>
</div>


%{--<g:each in="${properties}" var="prop">
<g:each in="${prop}" var="val">
<g:message name="props" message="${val}"/><br>
</g:each>
</g:each>--}%