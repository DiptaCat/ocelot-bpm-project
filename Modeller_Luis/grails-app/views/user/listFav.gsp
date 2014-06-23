<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Favorite Projects</title>
</head>

<body>

    Favorite Projects:
    <g:each in="${fav}" var="proj">
        <div id="show">
            <span class="name">${proj.name}</span>
        </div>
    </g:each>

</body>
</html>