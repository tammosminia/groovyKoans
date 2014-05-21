<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>

<h2>Available koans</h2>
<ul>
    <g:each in="${chapters}" var="chapter">
        <li>
            ${chapter.name}<br/>
        <ul>
            <g:each in="${chapter.koans}" var="koan">
                <li><g:link action="view" params="[number: koan.number]">${koan.name}</g:link></li>
            </g:each>
        </ul>
        </li>
    </g:each>
</ul>
</body>
</html>