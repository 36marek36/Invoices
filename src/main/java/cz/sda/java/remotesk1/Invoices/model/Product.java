package cz.sda.java.remotesk1.Invoices.model;

import lombok.Builder;

@Builder
public record Product (String id, String name, String price) {
}
