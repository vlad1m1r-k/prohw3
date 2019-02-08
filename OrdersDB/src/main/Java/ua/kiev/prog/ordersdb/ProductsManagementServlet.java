package ua.kiev.prog.ordersdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/products")
public class ProductsManagementServlet extends HttpServlet {
    private Dao dao = MysqlDaoImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        req.setAttribute("products", dao.getProducts());
        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        dao.addProduct(new Product(req.getParameter("name"), Integer.parseInt(req.getParameter("price"))));
        req.setAttribute("products", dao.getProducts());
        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }
}
