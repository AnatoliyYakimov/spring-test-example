package org.example.service;

import org.example.repository.SimpleRepository;
import org.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {

    private final SimpleRepository simpleRepository;

    /**
     * аннотация {@link Autowired} не обязательна в новых версиях Spring Boot
     */
    @Autowired
    public SimpleService(SimpleRepository simpleRepository) {
        this.simpleRepository = simpleRepository;
    }

    public Person getPerson() {
        return simpleRepository.getPersonFromDb();
    }

    public void savePerson(Person person) {
        Person newPerson = new Person(person.getName() + " Ибн Аль Хаттаб", 100);
        simpleRepository.savePersonToDb(newPerson);
    }
}
