<!doctype html>

<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title><g:layoutTitle default="${grailsApplication.config.appName}"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<asset:javascript src="application.js"/>
	<asset:stylesheet href="application.css"/>
	<asset:link rel="shortcut icon" href="favicon.ico" type="image/x-icon"/>

	<g:layoutHead/>
</head>

<body class="navbar-fixed">
<g:render template="/shared/navbar"/>

<div class="main-container" id="main-container">
	<div class="main-container-inner">
		<a class="menu-toggler" id="menu-toggler" href="#"><span class="menu-text"></span></a>

		<g:render template="/shared/sidebar"/>

		<div class="main-content">
			<div class="page-content">
				<g:layoutBody/>
			</div>
		</div>
	</div>
</div>
<asset:javascript src="sidebar.js"/>
</body>
</html>