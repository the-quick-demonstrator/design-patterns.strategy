package com.github.curriculeon;

import java.util.List;

public interface PersonRepositoryInterface {
    List<Person> toList();

    Person findById(Long id);

    Person add(Person person);

    Person updateById(Long id, Person newPersonData);

    Person deleteById(Long id);

    Person delete(Person person);
}
