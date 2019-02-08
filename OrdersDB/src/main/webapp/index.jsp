<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>База даных заказов</title>
</head>
<body>
<table>
    <tr>
        <td>
            <form action="clients" method="get" target="_self">
                <input type="submit" value="Клиенты">
            </form>
        </td>
        <td>
            <form action="products" method="get" target="_self">
                <input type="submit" value="Товары">
            </form>
        </td>
        <td>
            <form action="orders" method="get" target="_self">
                <input type="submit" value="Заказы">
            </form>
        </td>
    </tr>
</table>
</body>
</html>
