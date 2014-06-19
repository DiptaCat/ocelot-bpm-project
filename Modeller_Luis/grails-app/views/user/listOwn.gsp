<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Own Projects</title>
</head>

<body>

    Projects:
    <g:each in="${projects}" var="proj">
        <div id="show">
            <span class="name">${proj.name}</span>
        </div>
    </g:each>

</body>
</html>