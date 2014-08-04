<div class="navbar navbar-default navbar-fixed-top" id="navbar">
    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <div style="display:inline;">
                <g:link controller="main" action="index"><asset:image src="logodipu.gif"/></g:link>
            </div>
        </div>

        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li>
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <g:if test="${session.profile?.foto}">
                            <asset:image src="${session.profile?.foto}"/>

                        </g:if>
                        <g:else>
                            <asset:image src="useravatar.png"/>

                        </g:else>
                        <span>${session.profile?.nomComplet ?: request.user ?: '[desconegut]'}</span> <i
                            class="icon-caret-down"></i>
                    </a>
                    <ul class="pull-right dropdown-navbar navbar-blue dropdown-menu dropdown-caret dropdown-close dropdown-profiles">
                        <li class="dropdown-header"><i class="icon-user"></i> Perfil</li>
                        <g:each in="${request.units}">
                            <li>
                                <g:link controller="main" action="setUnit" id="${it.id}">
                                    <div class="clearfix">
                                        <span class="pull-left"><i
                                                class="btn btn-xs no-hover btn-${it.id == request.unit ? 'success' : 'info'} icon-briefcase"></i>${it.value}
                                        </span>
                                    </div>
                                </g:link>
                            </li>
                        </g:each>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
