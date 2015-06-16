<%@ page import="ocelot.Palette" %>

<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<title>Ocelot MT</title>
	<asset:javascript src="ocelot/app.js"/>
	<script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.11.0.js"></script>
</head>

<body>

<div ng-app="ocelotApp">
	<div ng-view></div>
</div>

</body>
</html>