package cz.sda.java.remotesk1.Invoices.controller.rest;

import cz.sda.java.remotesk1.Invoices.model.User;
import cz.sda.java.remotesk1.Invoices.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DbInitializer(final UserRepository userRepository,final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("user", passwordEncoder.encode("admin"), "ADMIN"));
        userRepository.save(new User("user2", passwordEncoder.encode("admin"), "ADMIN"));
        userRepository.save(new User("user3", passwordEncoder.encode("admin"), "USER"));

    }
}
