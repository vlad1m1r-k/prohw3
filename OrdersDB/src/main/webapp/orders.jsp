<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Оформление заказа</title>
</head>
<body>
<a href="/">На главную.</a> <br>
<form action="orders" method="post" target="_self">
    <table>
        <tr>
            <td>Номер заказа: </td>
            <td>
                <input type="number" name="orderNumber" required>
            </td>
            <td>
                <input type="submit" value="Оформить">
            </td>
        </tr>
    </table>
    <table>
        <tr>
            <th>Клиенты</th><th>Продукты</th>
        </tr>
        <tr>
            <td>
                <div style="height: 300px; border: solid 1px black; overflow: scroll;">
                    <c:choose>
                        <c:when test="${(clients ne null) && (fn:length(clients) gt 0)}">
                            <table border="1">
                                <tr>
                                    <th></th><th>Фамилия</th><th>Имя</th>
                                </tr>
                                <c:forEach items="${clients}" var="a">
                                    <tr>
                                        <td>
                                            <input type="radio" name="clientID" value=${a.id}>
                                        </td>
                                        <td>${a.lastName}</td><td>${a.firstName}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:when>
                        <c:otherwise>
                            Нет.
                        </c:otherwise>
                    </c:choose>
                </div>
            </td>
            <td>
                <div style="height: 300px; border: solid 1px black; overflow: scroll;">
                    <c:choose>
                        <c:when test="${(products ne null) && (fn:length(products) gt 0)}">
                            <table border="1">
                                <tr>
                                    <th></th><th>Наименование</th><th>Цена</th>
                                </tr>
                                <c:forEach items="${products}" var="a">
                                    <tr>
                                        <td>
                                            <input type="checkbox" name="productID" value=${a.id}>
                                        </td>
                                        <td>${a.name}</td><td>${a.price}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:when>
                        <c:otherwise>
                            Нет.
                        </c:otherwise>
                    </c:choose>
                </div>
            </td>
        </tr>
    </table>
</form>
Наши заказы: <br>
<c:choose>
    <c:when test="${(orders ne null) && (fn:length(orders) gt 0)}">
        <table border="1">
            <tr>
                <th>Номер заказа</th><th>Клиент</th><th>Товары</th>
            </tr>
            <c:forEach items="${orders}" var="a">
                <tr>
                    <td>${a.orderNumber}</td>
                    <td>${a.client.lastName} ${a.client.firstName}</td>
                    <td>
                        <table>
                            <c:forEach items="${a.products}" var="b">
                                <tr>
                                    <td>${b.name} ${b.price}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
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
