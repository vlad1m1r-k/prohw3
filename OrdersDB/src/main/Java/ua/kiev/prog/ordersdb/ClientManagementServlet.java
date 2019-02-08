package ua.kiev.prog.ordersdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/clients")
public class ClientManagementServlet extends HttpServlet {
    private Dao dao = MysqlDaoImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        req.setAttribute("clients", dao.getClients());
        req.getRequestDispatcher("clients.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        dao.addClient(new Client(req.getParameter("firstName"), req.getParameter("lastName")));
        req.setAttribute("clients", dao.getClients());
        req.getRequestDispatcher("clients.jsp").forward(req, resp);
    }
}
