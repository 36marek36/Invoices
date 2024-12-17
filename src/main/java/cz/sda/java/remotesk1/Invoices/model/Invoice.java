package cz.sda.java.remotesk1.Invoices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class Invoice {

    @Id
    private String id;
    @ManyToOne
    private Client client;
    private LocalDate date;

}
