<html>
<head>
    <title>Own Projects</title>
</head>

<body>

Owned Projects:<br/>

<g:each in="${own}" var="proj">
    <div id="show">
        <span class="name">${proj.name}</span>
    </div>
</g:each>

</body>
</html>