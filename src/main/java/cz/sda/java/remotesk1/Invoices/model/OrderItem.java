package cz.sda.java.remotesk1.Invoices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderItem {
    @Id
    private String id;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;
    private int amount;
}