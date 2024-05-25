package dev.kaio.service;

import dev.kaio.entity.Person;
import dev.kaio.repository.PersonRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PersonService {

    @Inject
    PersonRepository personRepository;

    public List<Person> personList() {
        return personRepository.listAll();
    }

    @Transactional
    public Person addPerson(Person person) {
        personRepository.persist(person);
        return person;
    }
}
