<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>

<g:link action="index">list</g:link>
<hr/>

<g:if test="${success}">
    Success!<br/>
</g:if>
<g:elseif test="${exception}">
    Exception caught! ${exception.class.name}<br/>
    exception message: ${message}<br/>
    stacktrace:
    <ul>
        <g:each in="${exception.stackTrace}" var="stacktraceElement">
            <li>${stacktraceElement}</li>
        </g:each>
    </ul>
</g:elseif>
<g:elseif test="${message}">
    Failure!<br/>
    ${message}
</g:elseif>
<g:else>
    Good luck!<br/>
</g:else>

<g:form action="view" id="${id}">
    <g:textArea name="koan" value="${koan}" rows="20" cols="100"/>
    <hr/>
    <g:submitButton name="try"/>
</g:form>

</body>
</html>