<!DOCTYPE html>
<html>
<head>
    <title>Actions</title>
</head>

<body>

Add new project
<g:form controller="user" method="post" name="addProjectForm">
    Name: <g:textField name="name" value=""/>
    <g:actionSubmit action="add" value="Save"/>
</g:form>

<br/>

Add own project
<g:form controller="user" method="post" name="ownProjectForm">
    Name: <g:textField name="name" value=""/>
    <g:actionSubmit action="addOwn" value="Save"/>
</g:form>

<br/>

<g:form controller="user" method="post">
    <g:actionSubmit value="List all Projects" action="list"/>
    <g:actionSubmit value="List own Projects" action="listOwn"/>
</g:form>

<g:form controller="user" method="post">
    <g:actionSubmit value="List fav Projects" action="listFav"/>
    <g:actionSubmit value="List recent Projects" action="listRecent"/>
</g:form>

</body>
</html>