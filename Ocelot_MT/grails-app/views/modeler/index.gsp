<%@ page contentType="text/html;charset=UTF-8" %>

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
				Properties
			</div>
		</div>

	</div>
</div>
</body>
</html>

%{--<style>
div{
border: 1px #000000 solid;
}
#canvas{
height: 80%;
}
#properties{
height: 20%;
position: relative;
}
</style>--}%

%{--<div style="min-height: 300px; width: 100%;">
	<div style="height: 100%; width: 20%">
		Palette<br>
		Palette<br>
		Palette<br>
		Palette<br>
	</div>
	<div style="height: 100%; width: 80%; float: right; vertical-align: top;">
		<div style="height: 80%;">
			Canvas
		</div>
		<div style="height: 20%;">
			Properties
		</div>
	</div>
</div>--}%
