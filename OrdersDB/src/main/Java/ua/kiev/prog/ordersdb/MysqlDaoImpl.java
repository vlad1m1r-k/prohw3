package ua.kiev.prog.ordersdb;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MysqlDaoImpl implements Dao{
    private static final MysqlDaoImpl instance = new MysqlDaoImpl();
    private final BasicDataSource dataSource = new BasicDataSource();

    private MysqlDaoImpl() {
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/orders?characterEncoding=utf8");
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");
    }

    public static MysqlDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<Product> getProducts() {
        Connection connection = null;
        List<Product> result = new LinkedList<>();
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            while (resultSet.next()) {
                result.add(new Product(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3)));
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

    @Override
    public Product getProduct(Integer id) {
        Connection connection = null;
        Product result = null;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products WHERE id = " + id);
            while (resultSet.next()) {
                result = new Product(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3));
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

    @Override
    public List<Client> getClients() {
        Connection connection = null;
        List<Client> result = new LinkedList<>();
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clients");
            while (resultSet.next()) {
                result.add(new Client(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));
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

    @Override
    public Client getClient(Integer id) {
        Connection connection = null;
        Client result = null;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clients WHERE id = " + id);
            while (resultSet.next()) {
                result = new Client(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
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

    @Override
    public List<Order> getOrders() {
        Connection connection = null;
        List<Order> result = new LinkedList<>();
        try {
            connection = dataSource.getConnection();
            List<Integer> orderIdList = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT order_number FROM orders GROUP BY order_number");
            while (resultSet.next()) {
                orderIdList.add(resultSet.getInt(1));
            }
            for (Integer orderId : orderIdList) {
                resultSet = statement.executeQuery("SELECT client_id FROM orders WHERE order_number = " + orderId + " LIMIT 1");
                resultSet.next();
                Client client = getClient(resultSet.getInt(1));
                List<Product> products = new ArrayList<>();
                resultSet = statement.executeQuery("SELECT product_id FROM orders WHERE order_number = " + orderId);
                while (resultSet.next()) {
                    products.add(getProduct(resultSet.getInt(1)));
                }
                result.add(new Order(orderId, client, products));
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

    @Override
    public void addProduct(Product product) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO products (name, price) VALUES ('" + product.getName() + "', '" + product.getPrice() + "')");
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
    }

    @Override
    public void addClient(Client client) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO clients (firstname, lastname) VALUES ('" + client.getFirstName() + "', '" + client.getLastName() + "')");
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
    }

    @Override
    public void addOrder(Order order) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            for (Product product : order.getProducts()) {
                statement.executeUpdate("INSERT INTO orders (order_number, client_id, product_id) VALUES (" +
                        order.getOrderNumber() + ", " + order.getClient().getId() + ", " + product.getId() + ")");
            }
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
    }
}
