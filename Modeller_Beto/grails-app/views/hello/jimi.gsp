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
<g:form  method="post">
    <g:input name="title" value="${letter.title}" />
    <g:input name="comments[0].text" value="${letter.comments[0].text}" />
    <g:actionSubmit action="save" value="Save" />
    <g:actionSubmit action="addComment" value="Add Comment" />
</g:form>


<g:actionSubmit action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" />
--%>

<%-- el nombre de id es el que indica la accion que se ejecutara en el Controller --%>
<g:form  controller="hello"  enctype="multipart/form-data" method="post" id="hola" name="lucas">
    Nom: <g:textField name="textNom" value="${textNom}" /> <br>
    Grup: <g:textField name="textGrup" value="${textGrup}" /> <br>
    <g:actionSubmit action="guardar" value="Guardar" /> <br>
    <g:actionSubmit action="llistar" value="Llistar" />
</g:form>

</body>
</html>