package com.github.curriculeon;

import java.util.ArrayList;
import java.util.List;

public class PersonRepository {
    private final List<Person> personList;

    public PersonRepository() {
        this(new ArrayList<>());
    }

    public PersonRepository(List<Person> personList) {
        this.personList = personList;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public Person add(Person person) {
        if (findById(person.getId()) != null) {
            throw new IllegalArgumentException();
        }
        getPersonList().add(person);
        return findById(person.getId());
    }

    public Person findById(Long id) {
        return getPersonList()
                .stream()
                .filter(person -> person.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Person update(Long id, Person newPersonData) {
        final Person personToUpdate = findById(id);
        final int indexToSet = getPersonList().indexOf(personToUpdate);
        getPersonList().set(indexToSet, newPersonData);
        return getPersonList().get(indexToSet);
    }

    public Person delete(Long id) {
        final Person personToDelete = findById(id);
        final int indexToDelete = getPersonList().indexOf(personToDelete);
        getPersonList().remove(indexToDelete);
        return personToDelete;
    }

    public Person delete(Person personToDelete) {
        getPersonList().remove(personToDelete);
        return personToDelete;
    }
}