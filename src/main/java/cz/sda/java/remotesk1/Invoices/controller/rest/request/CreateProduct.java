package cz.sda.java.remotesk1.Invoices.controller.rest.request;

import lombok.NonNull;

public record CreateProduct (@NonNull String name, @NonNull String price) {
}
