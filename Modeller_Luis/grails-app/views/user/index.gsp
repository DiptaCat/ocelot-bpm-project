<!DOCTYPE html>
<html>
    <head>
        <title>Actions</title>
    </head>

	<body>

        <g:form controller="User" method="post" name="ownProjectForm">
            <g:textField name="name" value="${name}" />
            <g:actionSubmit action="addOwn" value="AddOwn"/>
        </g:form>

    <br/>

        <g:form controller="User" enctype="multipart/form-data" method="post" id="user" name="addProjectForm">
            <g:textField name="name" value="${name}" />
            <g:actionSubmit action="add" value="AddNew"/>
        </g:form>

    <br/>

        <g:form controller="user" action="list" name="listProjectsForm">
            <g:submitButton name="List all Projects" id="list_projects_button"/>
        </g:form>

    <br/>

        <g:form controller="user" action="listFav" name="listFavProjectForm">
            <g:submitButton name="List Fav Projects" id="list_fav_button"/>
        </g:form>

    <br/>

        <g:form controller="user" action="listOwn" name="listOwnProjectForm">
            <g:submitButton name="List Own Projects" id="list_own_button"/>
        </g:form>

    <br/>

    <g:form controller="user" action="listRecent" name="listRecProjectForm">
        <g:submitButton name="List Recent Projects" id="list_rec_button"/>
    </g:form>

	</body>
</html>