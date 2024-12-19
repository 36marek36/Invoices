package cz.sda.java.remotesk1.Invoices.service;

import cz.sda.java.remotesk1.Invoices.model.Order;
import cz.sda.java.remotesk1.Invoices.model.OrderItem;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    Order addOrder(@NonNull String clientId, @NonNull LocalDate date);

    List<Order> getAllOrders();

    Order getOrder(String id);

    void removeOrder(String id);

    Order updateOrder(String id, String clientId, LocalDate date);

    OrderItem addItemToOrder(String orderId, @NonNull String productId, int amount);

    List<OrderItem> getAllItemsFor(String orderId);

    OrderItem getOrderItem(String orderId, String id);

    void removeOrderItem(String orderId, String id);

    OrderItem updateOrderItem(String orderId, String id, String productId, Integer amount);
}