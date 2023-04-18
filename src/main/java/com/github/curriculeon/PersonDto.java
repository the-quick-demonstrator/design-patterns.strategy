package com.github.curriculeon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonDto {
    private File file;

    public PersonDto(File file) {
        this.file = file;
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getRows() {
        try {
            return Files
                    .lines(file.toPath())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(Person person) {
        try {
            final FileWriter fileWriter = new FileWriter(file, true);
            final String contentToBeWritten = person.toString();
            fileWriter.write(contentToBeWritten);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    public List<Person> parse() {
        final List<Person> result = new ArrayList<>();
        getRows().forEach(person -> result.add(new PersonOrm(person).parse()));
        return result;
    }
}
