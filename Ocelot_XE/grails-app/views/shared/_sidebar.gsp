<div class="sidebar sidebar-fixed" id="sidebar">
	<ul class="nav nav-list">
		<li class="sidebar-app-title sidebar-appsti" id="app-title">Ocelot XE</li>
		<li><a href="${createLink(uri: '/')}"><i class="icon-home"></i> <span class="menu-text"><g:message code="default.home.label" /></span></a></li>
        <li><g:link controller="task" action="emptyView"><i class="icon-tasks"></i> <span class="menu-text"><g:message code="default.tasks.label" /></span></g:link></li>
        %{--<li><g:link controller="bpm" action="index"><i class="icon-user"></i> <span class="menu-text">Bpms</span></g:link></li>--}%
 	</ul>
    <div class="sidebar-collapse" id="sidebar-collapse">
        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
    </div>
</div>

