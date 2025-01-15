package cz.sda.java.remotesk1.Invoices.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity (name = "users")
public class User {

    @Id
    private String username;

    @Column
    private String password;

    @Column
    private String role;
}
