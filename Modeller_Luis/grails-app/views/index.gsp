<!DOCTYPE html>
<html>
    <head>
        <title>Acctions</title>
    </head>

	<body>
        <g:form controller="user" name="listProjectsForm">
            <g:submitButton name="List all Projects" id="list_projects_button"/>
        </g:form>

        <g:form controller="addOwn" name="newProjectForm">
            <g:textField name="q" value=""/>
            <g:submitButton name="Add new Project" id="create_project_button"/>
        </g:form>
	</body>
</html>