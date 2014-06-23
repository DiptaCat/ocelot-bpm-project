<%--
  Created by IntelliJ IDEA.
  User: beto
  Date: 19/06/14
  Time: 12:44
--%>

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>JIMI HENDRIX</title>
</head>
<body>

Jimi Hendrix Page

<%--
${textGrup}
${textNom}
--%>

<%-- el nombre de id es el que indica la accion que se ejecutara en el Controller --%>
<g:form  controller="hello"  enctype="multipart/form-data" method="post" id="addArtista">
    Nom: <g:textField name="textNom" value="" /> <br>
    Grup: <g:textField name="textGrup" value="" /> <br>
    <g:checkBox name="checkbox" value="1" /> Favorito<br>
    <g:actionSubmit action="guardar" value="Guardar" /> <br>
    <g:actionSubmit action="llistarArtistes" value="Llistar Artistes" />
    <g:actionSubmit action="llistarFavorits" value="Llistar Favorits" />
    <g:actionSubmit action="llistarRecents" value="Llistar Recents" />
</g:form>

</body>
</html>