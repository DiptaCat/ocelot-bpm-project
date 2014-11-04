<%@ page import="ocelot.PaletteItem; grails.converters.JSON" contentType="text/html;charset=UTF-8" %>

<html>
<head>
	<title>Canvas</title>
	<meta name="layout" content="main">
	<asset:stylesheet href="ocelot/ocelot.css"/>
</head>

<body>
<div class="container-fluid">
	<div class="row" >

		<div id="palette" class="col-md-2">
				Palette
		</div>

		<div id="col2" class="col-md-10">
			<div id="canvas">
				Canvas
			</div>

			<div id="properties">
				%{--Properties--}%
				%{--<g:render template="properties" model="${['element': PaletteItem.get(1)]}"/>--}%
				<g:render template="properties" model="${['properties': JSON.parse(PaletteItem.get(10).props)]}"/>
			</div>
		</div>

	</div>
</div>
</body>
</html>
