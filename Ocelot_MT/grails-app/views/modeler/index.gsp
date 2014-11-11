<%@ page import="ocelot.PaletteItem; grails.converters.JSON" contentType="text/html;charset=UTF-8" %>

<html>
<head>
	<title>Canvas</title>
	<meta name="layout" content="main">
	<asset:stylesheet href="ocelot/ocelot.css"/>
	%{--<script src="http://d3js.org/d3.v2.js"></script>--}%
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js"></script>
	<asset:javascript src="ocelotApp/Canvas/app.js"/>
</head>

<body>
<div class="container-fluid">
	<div class="row">

		<div id="palette" class="col-md-2">
				Palette
		</div>

		<div id="col2" class="col-md-10">
			<div id="canvas" ng-app="docsTimeDirective" ng-controller="Controller">
				%{--Date format: <input ng-model="item">--}%
				%{--<button><a class="btn btn-mini" ng-click="asdf(item)">Boton</a></button>--}%
				%{--<hr/>--}%
				<span my-current-time="form">{{pimpollo}}</span>
				%{--Canvas--}%
				<my-current-time val="data" grouped="grouped"></my-current-time>
			</div>

			<div id="properties">
				%{--<g:render template="properties" model="${['properties': JSON.parse(PaletteItem.get(10).props)]}"/>--}%
			</div>
		</div>
	</div>
</div>
</body>
</html>
