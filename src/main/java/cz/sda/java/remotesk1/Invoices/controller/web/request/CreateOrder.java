package cz.sda.java.remotesk1.Invoices.controller.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrder {
    private String clientId;
    private LocalDate date;
}