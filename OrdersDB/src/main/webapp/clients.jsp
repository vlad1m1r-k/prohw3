<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Управление клиентами</title>
</head>
<body>
<a href="/">На главную.</a> <br>
<form action="clients" method="post" target="_self">
    <table>
        <tr>
            <td>Фамилия: </td>
            <td>
                <input type="text" name="lastName" required>
            </td>
            <td>Имя: </td>
            <td>
                <input type="text" name="firstName" required>
            </td>
            <td>
                <input type="submit" value="Добавить">
            </td>
        </tr>
    </table>
</form>
Наши клиенты:
<br>
<c:choose>
    <c:when test="${(clients ne null) && (fn:length(clients) gt 0)}">
        <table border="1">
            <tr>
                <th>Фамилия</th><th>Имя</th>
            </tr>
            <c:forEach items="${clients}" var="a">
                <tr>
                    <td>${a.lastName}</td><td>${a.firstName}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        Нет.
    </c:otherwise>
</c:choose>
</body>
</html>
