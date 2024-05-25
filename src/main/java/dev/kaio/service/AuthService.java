package dev.kaio.service;

import dev.kaio.entity.Person;
import dev.kaio.repository.PersonRepository;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class AuthService {

    @Inject
    PersonRepository personRepository;

    public String login(String email, String password) {
        Person person = personRepository.findByEmail(email);
        if(person != null && person.getPassword().equals(password)) {
            return generateToken(email);
        }
        throw new RuntimeException("Invalid Login");
    }

    private String generateToken(String email) {
        Set<String> roles = new HashSet<>();
        roles.add("USER");
        return Jwt.issuer("login-quarkus-kaio-perinity")
                .subject(email)
                .groups(roles)
                .expiresAt(System.currentTimeMillis() + 3600)
                .sign();
    }

}
