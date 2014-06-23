<!DOCTYPE html>
<html>
    <head>
        <title>Actions</title>
    </head>

	<body>

        <g:form controller="user" name="ownProjectForm">
            <g:textField name="name" value="${name}" />
            <g:actionSubmit action="addOwn" name="Add own Project"  value=""/>
        </g:form>

    <br/>

        <g:form controller="user" name="addProjectForm">
            <g:textField name="name" value="${name}" />
            <g:actionSubmit action="add" name="Add new Project"  value=""/>
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

	</body>
</html>