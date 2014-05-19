<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:javascript src="codemirror-4.0/codemirror.js"/>
    <g:javascript src="codemirror-4.0/mode/groovy/groovy.js"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'codemirror.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'codemirror_override.css')}" type="text/css">
</head>

<body>

<br/>
<div class="row">
    <div class="explanation col-xs-8">
        <div class="white-space: pre-wrap;">${koan.explanation}</div>
    </div>

    <div class="links col-xs-4">
        <ul>
            <g:each in="${koan.links}" var="link">
                <li><g:link url="${link}">${link}</g:link></li>
            </g:each>
        </ul>
    </div>
</div>

<br/>
<g:form action="view" id="${koan.id}">
    <div class="codeBlock">
        <g:textArea name="preCode" value="${koan.preCode}" readonly="readonly"/>
        <g:textArea name="code" value="${code}"/>
        <g:textArea name="postCode" value="${koan.postCode}" readonly="readonly"/>
    </div>
    <g:submitButton name="try"/>
</g:form>

<br/>
<div class="result">
    <g:if test="${output}">
        <p>
            <h2>output:</h2>
            <pre>${output}</pre>
        </p>
    </g:if>

    <g:if test="${success}">
        Success!<br/>
        <g:link action="next" id="${koan.id}">next</g:link>
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
</div>

<r:script>
    CodeMirror.fromTextArea(document.getElementById("preCode"), {readOnly: true});
    CodeMirror.fromTextArea(document.getElementById("code"), {lineNumbers: false, viewportMargin: Infinity});
    CodeMirror.fromTextArea(document.getElementById("postCode"), {readOnly: true});
</r:script>
</body>

</html>