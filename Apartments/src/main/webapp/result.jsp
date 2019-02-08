<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta contentType="text/html"; charset="UTF-8">
    <title>Квартиры</title>
</head>
<body>
<br>
Выберите параметры для выборки квартир:
<br>
<form action="#" method="post" target="_self">
    <table>
        <tr>
            <td>Город </td>
            <td>
                <select name="city">
                    <option value="all">Все</option>
                    <c:forEach items="${cities}" var="i">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Район </td>
            <td>
                <select name="area">
                    <option value="all">Все</option>
                    <c:forEach items="${areas}" var="i">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
        <td>Улица </td>
        <td>
            <select name="street">
                <option value="all">Все</option>
                <c:forEach items="${streets}" var="i">
                    <option value="${i}">${i}</option>
                </c:forEach>
            </select>
        </td>
        </tr>
        <tr>
            <td>Площадь квартиры от </td>
            <td><input type="number" name="spacefrom"> до <input type="number" name="spaceto"></td>
        </tr>
        <tr>
            <td>Количество комнат от </td>
            <td><input type="number" name="roomsfrom"> до <input type="number" name="roomsto"></td>
        </tr>
        <tr>
            <td>Цена от </td>
            <td><input type="number" name="pricefrom"> до <input type="number" name="priceto"></td>
        </tr>
    </table>
    <input type="submit" value="Запрос">
</form>
<c:choose>
    <c:when test="${(apartments ne null) && (fn:length(apartments) gt 0)}">
        <table border="1">
            <tr>
                <th>Город</th><th>Район</th><th>Улица</th><th>Дом</th><th>Квартира</th><th>Площадь</th><th>Комнат</th><th>Цена</th>
            </tr>
            <c:forEach items="${apartments}" var="a">
                <tr>
                    <td>${a.city}</td><td>${a.area}</td><td>${a.street}</td><td>${a.build}</td><td>${a.apartmentNumber}</td><td>${a.space}</td><td>${a.rooms}</td><td>${a.price}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        Нет результатов для отображения.
    </c:otherwise>
</c:choose>


</body>
</html>
