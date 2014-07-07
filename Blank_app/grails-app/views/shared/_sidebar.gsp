<div class="sidebar sidebar-fixed" id="sidebar">
	<ul class="nav nav-list">
		<li class="sidebar-app-title sidebar-appsti" id="app-title">Blank App</li>
		<li><a href="${createLink(uri: '/')}"><i class="icon-home"></i> <span class="menu-text"><g:message code="default.home.label" /></span></a></li>
		<li><g:link controller="user" action="index"><i class="icon-user"></i> <span class="menu-text">Users</span></g:link></li>
        <li><g:link controller="bpm" action="index"><i class="icon-user"></i> <span class="menu-text">Bpms</span></g:link></li>
		%{--<li><g:link controller="prova2" action="index"><i class="icon-plus-sign"></i> <span class="menu-text">Prova2</span></g:link></li>
		<li>
			<a href="#" class="dropdown-toggle">
            	<i class="icon-th-list"></i> <span class="menu-text">Tipologia</span> <b class="arrow icon-angle-down"></b>
         	</a>
			<ul class="submenu">
       			<li><g:link controller="tipusFamilia" class="index" action="index"><i class="icon-double-angle-right"></i> Família</g:link></li>
     			<li><g:link controller="tipusDestinatari" class="index" action="index"><i class="icon-double-angle-right"></i> Destinatari</g:link></li>
      			<li><g:link controller="tipusCanal" class="index" action="index"><i class="icon-double-angle-right"></i> Canal</g:link></li>
			</ul>
		</li>			--}%
 	</ul>
    <div class="sidebar-collapse" id="sidebar-collapse">
        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
    </div>
</div>

