<div class="sidebar sidebar-fixed" id="sidebar">
	<ul class="nav nav-list">
		<li class="sidebar-app-title sidebar-manager sidebar-procs" id="app-title">Ocelot XE</li>
		<li><a href="${createLink(uri: '/')}"><i class="icon-gear"></i> <span class="menu-text"><g:message code="default.processes.label" /></span></a></li>
        <li><g:link controller="task" action="index"><i class="icon-tasks"></i> <span class="menu-text"><g:message code="default.tasks.label" /></span></g:link></li>

        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="icon-paper-clip"></i> Examples <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li><g:link controller="vacationRequest" action="index"><i class="icon-reply"></i> <span class="menu-text"><g:message code="vacationRequest.label" /></span></g:link></li>

            </ul>
        </li>
 	</ul>

</div>

