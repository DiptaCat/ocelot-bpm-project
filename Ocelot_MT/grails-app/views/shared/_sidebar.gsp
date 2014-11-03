<div class="sidebar sidebar-fixed" id="sidebar">
	<ul class="nav nav-list">
		<li class="sidebar-app-title sidebar-appsti" id="app-title">Ocelot MT</li>

		<li><a href="${createLink(uri: '/')}"><i class="glyphicon glyphicon-home"></i><span class="menu-text"><g:message
				code="default.home.label"/></span></a></li>

		<li><g:link controller="member" action="index"><i class="glyphicon glyphicon-user"></i> <span
				class="menu-text">Users</span></g:link></li>

		<li><g:link controller="model" action="index"><i class="glyphicon glyphicon-briefcase"></i> <span
				class="menu-text">Models</span></g:link></li>

		<li><a href="http://localhost:8080/ocelot/palette#/palette"><i class="glyphicon glyphicon-list-alt"></i> <span
				class="menu-text">Palette</span></a></li>

		<li><g:link controller="modeler" action="index"><i class="glyphicon glyphicon-briefcase"></i> <span
				class="menu-text">Canvas</span></g:link></li>
	</ul>

	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="glyphicon glyphicon-chevron-left" data-icon1="glyphicon glyphicon-chevron-left"
		   data-icon2="glyphicon glyphicon-chevron-right"></i>
	</div>
</div>

