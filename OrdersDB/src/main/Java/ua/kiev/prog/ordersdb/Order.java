package ua.kiev.prog.ordersdb;

import java.util.List;

public class Order {
    private Integer orderNumber;
    private Client client;
    private List<Product> products;

    public Order(Integer orderNumber, Client client, List<Product> products) {
        this.orderNumber = orderNumber;
        this.client = client;
        this.products = products;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public Client getClient() {
        return client;
    }

    public List<Product> getProducts() {
        return products;
    }
}
