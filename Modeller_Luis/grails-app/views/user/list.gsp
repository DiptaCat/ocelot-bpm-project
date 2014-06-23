<html>
    <head>
        <title>All projects</title>
    </head>

    <body>

        All Projects:
        <g:each in="${projects}" var="proj">
            <div id="show">
                <g:form controller="User" enctype="multipart/form-data" method="post" id="user" name="addFavForm">
                    <g:textField name="name" value="${proj.name}" />
                    <g:actionSubmit action="addFav" value="AddFav"/>
                </g:form>
            </div>
        </g:each>

    </body>
</html>