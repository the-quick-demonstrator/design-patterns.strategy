package com.github.curriculeon;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public enum PersonRepositoryFacadeRefactored {
    INSTANCE(new PersonDto(new File("target/database.csv")));
    private final PersonDto personDto;
    private final InputOutputMediator console;

    PersonRepositoryFacadeRefactored(PersonDto personDto) {
        this.personDto = personDto;
        this.console = new InputOutputMediator();
    }

    public PersonRepository getRepository() {
        return new PersonRepository(personDto.parse());
    }

    public Person add() {
        final Long id = console.getLongInput("Enter id");
        final String name = console.getStringInput("Enter name");
        final Integer age = console.getIntegerInput("Enter age");
        final Person personToAdd = new Person(id, name, age);
        personDto.write(personToAdd);
        return personToAdd;
    }

    public Person findById() {
        final Long id = console.getLongInput("Enter id");
        return getRepository().findById(id);
    }

    public Person update() {
        final Long id = console.getLongInput("Enter id");
        final Long newId = console.getLongInput("Enter new id");
        final String name = console.getStringInput("Enter name");
        final Integer age = console.getIntegerInput("Enter age");

        return getRepository().update(id, new Person(newId, name, age));
    }

    public Person delete() {
        final Long id = console.getLongInput("Enter id");
        return getRepository().delete(id);
    }

    public List<Person> findAll() {
        return getRepository().getPersonList();
    }
}
