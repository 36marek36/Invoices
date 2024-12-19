package cz.sda.java.remotesk1.Invoices.controller.rest.request;

import lombok.NonNull;

public record CreateOrderItem(@NonNull String productId, int amount) {
}