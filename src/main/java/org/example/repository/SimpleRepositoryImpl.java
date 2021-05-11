package org.example.repository;

import org.example.model.Person;

import java.util.Random;

public class SimpleRepositoryImpl implements SimpleRepository {

    private final Person[] persons = {
            new Person("Геральт Артёмович Ривийский", 100),
            new Person("Петров Пётр Петрович", 44),
            new Person("asdqwasd", -99)
    };

    @Override
    public Person getPersonFromDb() {
        return persons[0];
    }

    @Override
    public void savePersonToDb(Person person) {
        throw new IllegalArgumentException("Not yet implemented!");
    }
}
