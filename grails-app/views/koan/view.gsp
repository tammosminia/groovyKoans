<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <r:require modules="codemirror"/>
    <meta name="layout" content="main"/>
</head>

<body>

<div class="row">
    <div class="col-md-8">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">${koan.chapter.name} : ${koan.name}</h3>
            </div>

            <div class="panel-body">
                ${koan.explanation}
            </div>
        </div>
    </div>

    <div class="col-md-4">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Resources</h3>
            </div>

            <div class="panel-body">
                <ul class="list-unstyled">
                    <g:each in="${koan.chapter.links}" var="link">
                        <li>
                            <g:link target="_blank" url="${link.url}">${link.name}</g:link>
                        </li>
                    </g:each>
                </ul>
            </div>
        </div>
    </div>
</div>



<g:if test="${success}">
    <div class="row">
        <div class="col-md-12">
            <div class="alert alert-success">
                <strong>Well done!</strong> You successfully solved Koan #${koan.number + 1} "${koan.name}"
                <g:if test="${output}">
                    <a href="#" class="alert-link pull-right"></a>
                    <a type="button" class="alert-link pull-right" data-container="body" data-toggle="popover"
                       data-placement="left" data-content="${output}" data-original-title="" title="">View output</a>
                </g:if>
            </div>
        </div>
    </div>
</g:if>
<g:if test="${exception || message}">
    <div class="row">
        <div class="col-md-12">
            <div class="alert alert-danger">
                <strong>Hold on!</strong> ${message}
                <g:if test="${exception}">
                    <a type="button" class="alert-link pull-right" data-container="body" data-toggle="popover"
                       data-placement="left" data-content="${exception.message}" data-original-title=""
                       title="">View details</a>
                </g:if>
            </div>
        </div>
    </div>
</g:if>
<g:form action="view" params="[number: koan.number]">
    <div class="row">
        <div class="col-md-12">
            <div class="codeBlock">
                <g:textArea class="preCode" name="preCode" value="${koan.preCode}" readonly="readonly"/>
                <g:textArea name="code" value="${code}"/>
                <g:textArea name="postCode" value="${koan.postCode}" readonly="readonly"/>
            </div>
        </div>
    </div>

    <div class="row" style="padding-top: 20px;">
        <div class="col-md-12">
            <g:if test="${success}">
                <g:link action="next" class="btn btn-success pull-right"
                        params="[number: koan.number]">Next Koan</g:link>
            </g:if>
            <g:submitButton class="btn btn-primary pull-right" name="Execute code"/>
        </div>
    </div>
</g:form>
<div class="row" style="padding-top: 20px;">
    <div class="col-md-12">
        <div class="progress text-right">
            <div class="progress-bar" style="width: ${Math.round((koan.number + 1) * 100 / totalKoans)}%;"></div>
        </div>

        <div>
            Groovy Koan ${(koan.number + 1)} van ${totalKoans}
        </div>
    </div>
</div>
<r:script>
    CodeMirror.fromTextArea(document.getElementById("preCode"), {readOnly: true});
    CodeMirror.fromTextArea(document.getElementById("code"), {
        lineNumbers: false, viewportMargin: Infinity
    });
    CodeMirror.fromTextArea(document.getElementById("postCode"), {readOnly: true});
</r:script>
</body>
</html>