<%@ page import="ocelot.Palette" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Toda, guapu</title>
    <asset:javascript src="ocelotApp/app.js"/>
    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.21/angular.js"></script>
    <script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.11.0.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/angular.js/1.2.20/angular-resource.js"></script>

    <!-- Latest compiled and minified CSS -->
    %{--<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">--}%
</head>
<body>

<div ng-app="ocelotApp">
    <div ng-view></div>
</div>

</body>
</html>