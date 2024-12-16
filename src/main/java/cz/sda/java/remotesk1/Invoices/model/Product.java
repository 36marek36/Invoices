package cz.sda.java.remotesk1.Invoices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class Product {

    @Id
    private String id;
    private String name;
    private int price;
}
