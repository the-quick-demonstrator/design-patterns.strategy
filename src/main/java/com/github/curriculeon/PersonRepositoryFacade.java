package com.github.curriculeon;

import java.util.Scanner;

public class PersonRepositoryFacade {
    private final PersonPersistentRepository personDto;

    public PersonRepositoryFacade(PersonPersistentRepository personDto) {
        this.personDto = personDto;
    }

    public PersonRepository getRepository() {
        return new PersonRepository(personDto.toList());
    }

    public Person add() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter age");
        Integer age = Integer.parseInt(scanner.nextLine());
        Person personToAdd = new Person(id, name, age);
        personDto.add(personToAdd);
        return personToAdd;
    }

    public Person findById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id");
        Long id = Long.parseLong(scanner.nextLine());
        return getRepository().findById(id);
    }

    public Person update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println("Enter new id");
        Long newId = Long.parseLong(scanner.nextLine());
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter age");
        Integer age = Integer.parseInt(scanner.nextLine());

        return getRepository().update(id, new Person(newId, name, age));
    }

    public Person delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id");
        Long id = Long.parseLong(scanner.nextLine());
        return getRepository().delete(id);
    }
}
