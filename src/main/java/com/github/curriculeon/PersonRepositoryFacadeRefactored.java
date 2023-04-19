package com.github.curriculeon;

import java.io.File;
import java.util.List;

public enum PersonRepositoryFacadeRefactored {
    INSTANCE(new File("target/database.csv"));
    private final InputOutputMediator console;
    private final File file;

    PersonRepositoryFacadeRefactored(File file) {
        this.file = file;
        this.console = new InputOutputMediator();
    }

    public RepositoryInterface<Long, Person> getRepository() {
        return new RepositoryLogger<>(new PersonPersistentRepository(file));
    }

    public Person add() {
        final Long id = console.getLongInput("Enter id");
        final String name = console.getStringInput("Enter name");
        final Integer age = console.getIntegerInput("Enter age");
        final Person personToAdd = new Person(id, name, age);
        getRepository().add(personToAdd);
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
        getRepository().updateById(id, new Person(newId, name, age));
        return getRepository().findById(id);
    }

    public Person delete() {
        final Long id = console.getLongInput("Enter id");
        return getRepository().deleteById(id);
    }

    public List<Person> findAll() {
        return getRepository().getEntityList();
    }
}
