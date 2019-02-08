package ua.kiev.prog.ordersdb;

import java.util.List;

public interface Dao {
    List<Product> getProducts();
    Product getProduct(Integer id);
    List<Client> getClients();
    Client getClient(Integer id);
    List<Order> getOrders();

    void addProduct(Product product);
    void addClient(Client client);
    void addOrder(Order order);
}
