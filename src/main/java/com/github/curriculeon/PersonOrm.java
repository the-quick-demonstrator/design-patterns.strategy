package com.github.curriculeon;

public class PersonOrm {
    private String[] columns;

    public PersonOrm(String columns) {
        this(columns.split(","));
    }

    public PersonOrm(String[] columns) {
        this.columns = columns;
    }

    public Person parse() {
        final Long id = Long.valueOf(columns[0]);
        final String name = String.valueOf(columns[1]);
        final Integer age = Integer.valueOf(columns[2]);
        final Person person = new Person(id, name ,age);
        return person;
    }
}
