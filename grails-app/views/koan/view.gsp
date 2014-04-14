<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <g:javascript src="codemirror-4.0/codemirror.js"/>
    <g:javascript src="codemirror-4.0/mode/groovy/groovy.js"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'codemirror.css')}" type="text/css">
    <style type="text/css">
    .codeBlock {border: 1px solid black}
    .CodeMirror {
        border: 1px solid lightgrey;
        height: auto;
    }
    .CodeMirror-scroll {
        overflow-y: hidden;
        overflow-x: auto;
        height: auto;
    }
    </style>
</head>

<body>

<g:link action="index">list</g:link>
<br/>

<pre>${koan.explanation}</pre>

<g:form action="view" id="${koan.id}">
    <div class="codeBlock">
        %{--<pre>${koan.preCode}</pre>--}%
        <g:textArea name="preCode" value="${koan.preCode}" readonly="readonly"/>
        <g:textArea name="code" value="${code}"/>
        <g:textArea name="postCode" value="${koan.postCode}" readonly="readonly"/>
        %{--<pre>${koan.postCode}</pre>--}%
    </div>
    <g:submitButton name="try"/>
</g:form>

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

<r:script>
    CodeMirror.fromTextArea(document.getElementById("preCode"), {readOnly: true});
    CodeMirror.fromTextArea(document.getElementById("code"), {lineNumbers: false, viewportMargin: Infinity});
    CodeMirror.fromTextArea(document.getElementById("postCode"), {readOnly: true});
</r:script>
<r:layoutResources disposition="defer"/>
</body>

</html>