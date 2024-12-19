package cz.sda.java.remotesk1.Invoices.repository;

import cz.sda.java.remotesk1.Invoices.model.OrderItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderItemRepository extends CrudRepository<OrderItem, String> {
    List<OrderItem> findAllByOrderId(String orderId);
}