package cz.sda.java.remotesk1.Invoices.repository;

import cz.sda.java.remotesk1.Invoices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
