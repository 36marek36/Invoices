package cz.sda.java.remotesk1.Invoices.controller.rest.request;
import lombok.NonNull;

import java.time.LocalDate;

public record CreateOrder (
        @NonNull
        String clientId,
        @NonNull
        LocalDate date
) {}