package cz.sda.java.remotesk1.Invoices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class Invoice {

    @Id
    private String id;
    @ManyToOne
//    @JoinColumn(name = "client.id")
    private Client client;
    private LocalDate date;

}
