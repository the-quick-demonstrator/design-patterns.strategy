package com.github.curriculeon;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PersonPersistentRepository implements PersonRepositoryInterfaceRefactored {
    private final Document document;

    public PersonPersistentRepository(File file) {
        this.document = new Document(file.getAbsolutePath());
    }

    @Override
    public List<Person> getEntityList() {
        return document
                .toList()
                .stream()
                .map(PersonOrm::new)
                .map(PersonOrm::parse)
                .collect(Collectors.toList());
    }

    @Override
    public Person findById(Long id) {
        return getEntityList()
                .stream()
                .filter(person -> person.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    @Override
    public Person add(Person person) {
        document.append(person.toString());
        return findById(person.getId());
    }

    @Override
    public Person updateById(Long id, Person newPersonData) {
        document.replaceAllContent("");
        getEntityList().forEach(person -> {
            final Long personId = person.getId();
            final Boolean isPersonToUpdate = Objects.equals(personId, id);
            final String personData = isPersonToUpdate ? newPersonData.toString() : person.toString();
            document.append(personData);
        });
        return findById(id);
    }

    @Override
    public Person deleteById(Long id) {
        document.replaceAllContent("");
        Person personToDelete = findById(id);
        getEntityList().forEach(person -> {
            final Long personId = person.getId();
            final Boolean isPersonToDelete = Objects.equals(personId, id);
            final String personData = isPersonToDelete ? "" : person.toString();
            document.append(personData);
        });
        return personToDelete;
    }
    @Override
    public Person delete(Person person) {
        return deleteById(person.getId());
    }
}
