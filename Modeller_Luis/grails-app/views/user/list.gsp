<html>
<head>
    <title>All projects</title>
</head>

<body>

All Projects: <br><br>

<g:each in="${projects}" var="proj">
    <div id="show">
        <g:form controller="user" name="ownProjectForm">

        <%--<g:textField name="name" value="${proj.name}" disabled=""/>--%>
            <span class="name">${proj.name}</span>
            <g:link id="${proj.id}" action="addFav" controller="User">Add Fav</g:link>

        </g:form>

    </div>
</g:each>

</body>
</html>