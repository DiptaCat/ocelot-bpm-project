<html>
    <head>
        <title>All projects</title>
    </head>

    <body>

        All Projects:
        <g:each in="${projects}" var="proj">
            <div id="show">
                <span class="name">${proj.name}</span> <g:link id="${proj.id}" action="addFav" controller="user">Add to Fav</g:link>
            </div>
        </g:each>

    </body>
</html>