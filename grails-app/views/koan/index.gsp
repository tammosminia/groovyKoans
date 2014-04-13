<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Groovy Koans</title>
</head>

<body>

<h2>Available koans</h2>
<table>
    <g:each in="${koans}" var="koanNumber">
        <tr>
            <td>
                <g:link action="view" id="${koanNumber}">${koanNumber}</g:link>
            </td>
        </tr>
    </g:each>
</table>
</body>
</html>