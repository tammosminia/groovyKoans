<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>

<g:link action="index">list</g:link>
<hr/>

<pre>${koan.explanation}</pre>

<g:form action="view" id="${koan.id}">
    <g:textArea name="code" value="${code}" rows="20" cols="100"/>
    <hr/>
    <g:submitButton name="try"/>
</g:form>

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

<p>
    <h2>output:</h2>
    <pre>${output}</pre>
</p>

</body>
</html>