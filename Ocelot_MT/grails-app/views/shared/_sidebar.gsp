<div class="sidebar sidebar-fixed" id="sidebar">
	<ul class="nav nav-list">
		<li class="sidebar-app-title sidebar-manager" id="app-title">Ocelot MT</li>

		<li><a href="${createLink(uri: '/')}"><i class="glyphicon glyphicon-home"></i><span class="menu-text"><g:message
				code="default.home.label"/></span></a></li>

		<li><g:link controller="member" action="index"><i class="glyphicon glyphicon-user"></i> <span
				class="menu-text">Users</span></g:link></li>

        <li><a href="${createLink(uri: '/palette#/modelerIndex')}"><i class="glyphicon glyphicon-briefcase"></i> <span
                class="menu-text">Models</span></a></li>

		<li><a href="${createLink(uri: '/palette#/palette')}"><i class="glyphicon glyphicon-list-alt"></i> <span
				class="menu-text">Palette</span></a></li>
	</ul>

	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="glyphicon glyphicon-chevron-left" data-icon1="glyphicon glyphicon-chevron-left"
		   data-icon2="glyphicon glyphicon-chevron-right"></i>
	</div>
</div>

