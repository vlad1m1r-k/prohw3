package ua.kiev.prog.apartments;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/")
public class QueryServlet extends HttpServlet {
    private DatabaseConnector connector = DatabaseConnector.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        ParameterTransferClass parametersDTO = new ParameterTransferClass();
        parametersDTO.setCity(req.getParameter("city").equals("all") ? null : req.getParameter("city"));
        parametersDTO.setArea(req.getParameter("area").equals("all") ? null : req.getParameter("area"));
        parametersDTO.setStreet(req.getParameter("street").equals("all") ? null : req.getParameter("street"));
        parametersDTO.setSpaceFrom(req.getParameter("spacefrom").equals("") ? null : Integer.valueOf(req.getParameter("spacefrom")));
        parametersDTO.setSpaceTo(req.getParameter("spaceto").equals("") ? null : Integer.valueOf(req.getParameter("spaceto")));
        parametersDTO.setRoomsFrom(req.getParameter("roomsfrom").equals("") ? null : Integer.valueOf(req.getParameter("roomsfrom")));
        parametersDTO.setRoomsTo(req.getParameter("roomsto").equals("") ? null : Integer.valueOf(req.getParameter("roomsto")));
        parametersDTO.setPriceFrom(req.getParameter("pricefrom").equals("") ? null : Integer.valueOf(req.getParameter("pricefrom")));
        parametersDTO.setPriceTo(req.getParameter("priceto").equals("") ? null : Integer.valueOf(req.getParameter("priceto")));
        List<Apartment> queryResult = connector.query(parametersDTO);

        Map<String, List<String>> parameters = connector.getParameters();
        for (Map.Entry<String, List<String>> entry : parameters.entrySet()) {
            req.setAttribute(entry.getKey(), entry.getValue());
        }
        req.setAttribute("apartments", queryResult);
        req.getRequestDispatcher("result.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, List<String>> parameters = connector.getParameters();
        for (Map.Entry<String, List<String>> entry : parameters.entrySet()) {
            req.setAttribute(entry.getKey(), entry.getValue());
        }
        req.getRequestDispatcher("result.jsp").forward(req, resp);
    }
}
