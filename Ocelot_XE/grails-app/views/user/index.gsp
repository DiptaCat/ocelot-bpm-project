<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="default.home.label"/></title>
</head>

<body>

<div class="page-header">
    <h1>User Info</h1>
</div>
<g:form controller="user" action="change" name="change">
    <label for="user">
        <g:message code="default.user.label" default="User Login" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="user" maxlength="50" required="" value="${user}"/>

    <label for="user">
        <g:message code="default.group.label" default="Current Group" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="group" maxlength="50" required="" value="${group}"/>


    <g:submitButton name="change" value="Change" />
</g:form>
</div>

</body>
</html>