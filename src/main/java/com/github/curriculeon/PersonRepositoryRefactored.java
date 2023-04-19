package com.github.curriculeon;

import java.util.ArrayList;
import java.util.List;

public class PersonRepositoryRefactored implements RepositoryInterface<Long, Person> {
    private final List<Person> personList;

    public PersonRepositoryRefactored() {
        this(new ArrayList<>());
    }

    public PersonRepositoryRefactored(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public List<Person> getEntityList() {
        return personList;
    }
}