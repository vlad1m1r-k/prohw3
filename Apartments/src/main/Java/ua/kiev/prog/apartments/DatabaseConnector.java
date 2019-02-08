package ua.kiev.prog.apartments;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DatabaseConnector {
    private static final DatabaseConnector connector = new DatabaseConnector();
    private final BasicDataSource dataSource = new BasicDataSource();

    private DatabaseConnector() {
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/apartments?characterEncoding=utf8");
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");
    }

    public static DatabaseConnector getInstance() {
        return connector;
    }

    public Map<String, List<String>> getParameters() {
        Map<String, List<String>> result = new HashMap<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM cities ORDER BY name");
            List<String> cities = new LinkedList<>();
            while (resultSet.next()) {
                cities.add(resultSet.getString(1));
            }
            result.put("cities", cities);
            resultSet = statement.executeQuery("SELECT name FROM areas ORDER BY name");
            List<String> areas = new LinkedList<>();
            while (resultSet.next()) {
                areas.add(resultSet.getString(1));
            }
            result.put("areas", areas);
            resultSet = statement.executeQuery("SELECT name FROM streets ORDER BY name");
            List<String> streets = new LinkedList<>();
            while (resultSet.next()) {
                streets.add(resultSet.getString(1));
            }
            result.put("streets", streets);
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public List<Apartment> query(ParameterTransferClass parametersDTO) {
        LinkedList<Apartment> result = new LinkedList<>();
        Connection connection = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT c.name, ar.name, s.name, ad.build, a.apartmentNumber, a.space, a.rooms, a.price ");
        query.append("FROM apartments a ");
        query.append("JOIN areas ar ON a.area_id = ar.area_id ");
        query.append("JOIN addresses ad ON a.address_id = ad.address_id ");
        query.append("JOIN cities c ON ad.city_id = c.city_id ");
        query.append("JOIN streets s ON ad.street_id = s.street_id ");
        if (parametersDTO.isDataPresent()) {
            query.append("WHERE ");
            if (parametersDTO.getCity() != null) {
                query.append("c.name = '" + parametersDTO.getCity() + "' AND ");
            }
            if (parametersDTO.getArea() != null) {
                query.append("ar.name = '" + parametersDTO.getArea() + "' AND ");
            }
            if (parametersDTO.getStreet() != null) {
                query.append("s.name = '" + parametersDTO.getStreet() + "' AND ");
            }
            if (parametersDTO.getSpaceFrom() != null) {
                query.append("a.space > " + parametersDTO.getSpaceFrom() + " AND ");
            }
            if (parametersDTO.getSpaceTo() != null) {
                query.append("a.space < " + parametersDTO.getSpaceTo() + " AND ");
            }
            if (parametersDTO.getRoomsFrom() != null) {
                query.append("a.rooms > " + parametersDTO.getRoomsFrom() + " AND ");
            }
            if (parametersDTO.getRoomsTo() != null) {
                query.append("a.rooms < " + parametersDTO.getRoomsTo() + " AND ");
            }
            if (parametersDTO.getPriceFrom() != null) {
                query.append("a.price > " + parametersDTO.getPriceFrom() + " AND ");
            }
            if (parametersDTO.getPriceTo() != null) {
                query.append("a.price < " + parametersDTO.getPriceTo() + " AND ");
            }
            query = new StringBuilder(query.substring(0, query.length() - 5));
        }
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                result.add(new Apartment(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6),
                        resultSet.getInt(7), resultSet.getInt(8)));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
