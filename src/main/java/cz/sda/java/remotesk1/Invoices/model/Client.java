package cz.sda.java.remotesk1.Invoices.model;

import lombok.Builder;

@Builder
public record Client (String id, String name, String address) {
}
