package cz.sda.java.remotesk1.Invoices.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItem {

    @Id
    private String id;
    @ManyToOne
    private Invoice invoice;
    @ManyToOne
    private Product product;

    private int amount;
}
