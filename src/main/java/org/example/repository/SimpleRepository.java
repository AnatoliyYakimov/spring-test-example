package org.example.repository;

import org.example.model.Person;

public interface SimpleRepository {
    Person getPersonFromDb();

    void savePersonToDb(Person person);
}
