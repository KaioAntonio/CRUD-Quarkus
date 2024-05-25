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

    public Person findById(Long id) {
        return personRepository.findById(id);
    }

    @Transactional
    public Person updatePerson(Long id, Person newPerson) {
        Person oldPerson = findById(id);
        if (oldPerson != null) {
            oldPerson.setName(newPerson.getName());
            oldPerson.setAge(newPerson.getAge());
            personRepository.persist(oldPerson);
        }
        return oldPerson;
    }

    @Transactional
    public void deletePerson(Long id) {
        Person person = findById(id);
        if (person != null) {
            personRepository.delete(person);
        }
    }

}
