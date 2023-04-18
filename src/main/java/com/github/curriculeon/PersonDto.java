package com.github.curriculeon;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PersonDto {
    private final Document document;

    public PersonDto(File file) {
        this.document = new Document(file.getAbsolutePath());
    }

    public void write(Person person) {
        document.append(person.toString());
    }


    public List<Person> parse() {
        return document
                .toList()
                .stream()
                .map(PersonOrm::new)
                .map(PersonOrm::parse)
                .collect(Collectors.toList());
    }

    public void update(Long id, Person newPersonData) {
        final List<Person> personList = parse();
        document.replaceAllContent("");
        for(int index=0; index<personList.size(); index++) {
            final Person person = personList.get(index);
            final Long personId = person.getId();
            final Boolean isPersonToUpdate = Objects.equals(personId, id);
            String personData = person.toString();
            if(isPersonToUpdate) {
                personData = newPersonData.toString();
            }
            document.append(personData);
        }
    }
}
