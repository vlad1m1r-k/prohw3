package ua.kiev.prog.ordersdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

@WebServlet(urlPatterns = "/orders")
public class OrdersServlet extends HttpServlet {
    private Dao dao = MysqlDaoImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        req.setAttribute("clients", dao.getClients());
        req.setAttribute("products", dao.getProducts());
        req.setAttribute("orders", dao.getOrders());
        req.getRequestDispatcher("orders.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        Integer orderNumber = Integer.valueOf(req.getParameter("orderNumber"));
        Client client = dao.getClient(Integer.valueOf(req.getParameter("clientID")));
        String[] productsId = req.getParameterValues("productID");
        List<Product> products = new LinkedList<>();
        for (String id : productsId) {
            products.add(dao.getProduct(Integer.valueOf(id)));
        }
        dao.addOrder(new Order(orderNumber, client, products));
        req.setAttribute("clients", dao.getClients());
        req.setAttribute("products", dao.getProducts());
        req.setAttribute("orders", dao.getOrders());
        req.getRequestDispatcher("orders.jsp").forward(req, resp);
    }
}
