<%@ page import="blank.User" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
<g:set var="entityName" value="${message(code: 'bpm.label', default: 'BPM')}"/>
</head>
<body>
<div class="page-header">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
</div>

<g:each in="${blank.Bpm.all}" var="bpm">
    <div id="show">
        <span class="name">${bpm.name} ${bpm.id}</span> <g:link id="${bpm.id}" action="addBPMToFavourites" controller="User">Add to Favourites</g:link>
    </div>
</g:each>