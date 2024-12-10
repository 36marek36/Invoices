package cz.sda.java.remotesk1.Invoices.controller.rest.request;

import lombok.NonNull;

public record CreateClient(@NonNull String name, @NonNull String address) {
}
