<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Управление товарами</title>
</head>
<body>
<a href="/">На главную.</a> <br>
<form action="products" method="post" target="_self">
    <table>
        <tr>
            <td>Название: </td>
            <td>
                <input type="text" name="name" required>
            </td>
            <td>Цена: </td>
            <td>
                <input type="number" name="price" required>
            </td>
            <td>
                <input type="submit" value="Добавить">
            </td>
        </tr>
    </table>
</form>
Наши товары:
<br>
<c:choose>
    <c:when test="${(products ne null) && (fn:length(products) gt 0)}">
        <table border="1">
            <tr>
                <th>Наименование</th><th>Цена</th>
            </tr>
            <c:forEach items="${products}" var="a">
                <tr>
                    <td>${a.name}</td><td>${a.price}</td>
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
