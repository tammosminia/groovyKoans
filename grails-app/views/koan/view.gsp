<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <g:javascript src="codemirror-4.0/codemirror.js"/>
    <g:javascript src="codemirror-4.0/mode/groovy/groovy.js"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'codemirror.css')}" type="text/css">
</head>

<body>

<g:link action="index">list</g:link>
<hr/>

<pre>${koan.explanation}</pre>

<g:form action="view" id="${koan.id}">
    <g:textArea name="code" value="${code}"/>
    %{--<div id="code"/>--}%
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

<r:script>
    var myCodeMirror = CodeMirror.fromTextArea(document.getElementById("code"), {lineNumbers: 'true'});
</r:script>
<r:layoutResources disposition="defer"/>
</body>

</html>