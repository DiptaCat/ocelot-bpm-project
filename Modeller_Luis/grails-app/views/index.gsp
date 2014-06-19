<!DOCTYPE html>
<html>
    <head>
        <title>Acctions</title>
    </head>

	<body>
        <g:formRemote url="[user: 'addOwn']" name="addProjectForm">
            <g:textField name="name" value=""/>
            <g:submitButton name="Add Own Project" id="add_project_button"/>
        </g:formRemote>

    <br/>

        <g:formRemote url="[user: 'add']" name="newProjectForm">
            <g:textField name="name" value=""/>
            <g:submitButton name="Add new Project" id="create_project_button"/>
        </g:formRemote>

    <br/>

        <g:form controller="user" action="listFav" name="listFavProjectForm">
            <g:submitButton name="List Fav Projects" id="list_fav_button"/>
        </g:form>

    <br/>

        <g:form controller="user" action="listOwn" name="listOwnProjectForm">
            <g:submitButton name="List Own Projects" id="list_own_button"/>
        </g:form>

    <br/>

        <g:form controller="user" action="list" name="listProjectsForm">
            <g:submitButton name="List all Projects" id="list_projects_button"/>
        </g:form>
	</body>
</html>