package com.github.curriculeon;

import java.util.Arrays;
import java.util.function.Supplier;

public enum PersonRepositoryFacadeRefactoredOption {
    ADD(PersonRepositoryFacadeRefactored.INSTANCE::add),
    FIND_BY_ID(PersonRepositoryFacadeRefactored.INSTANCE::findById),
    UPDATE_BY_ID(PersonRepositoryFacadeRefactored.INSTANCE::update),
    DELETE_BY_ID(PersonRepositoryFacadeRefactored.INSTANCE::delete),
    QUIT(() -> null);

    private Supplier<Person> personSupplier;

    PersonRepositoryFacadeRefactoredOption(Supplier<Person> personSupplier) {
        this.personSupplier = personSupplier;
    }

    public Person invoke() {
        return personSupplier.get();
    }

    public static void performUserInput() {
        String userInput;
        do {
            final InputOutputMediator io = new InputOutputMediator();
            final String menuOptions = Arrays.toString(values());
            final String menuName = PersonRepositoryFacadeRefactoredOption.class.getSimpleName();
            final String message = "Welcome to the [ %s ] menu, from here, you can select of the following options:\n\t%s";
            userInput = io.getStringInput(message, menuName, menuOptions).toUpperCase();
            valueOf(userInput).invoke();
            io.println(PersonRepositoryFacadeRefactored
                    .INSTANCE
                    .getRepository()
                    .getEntityList()
                    .toString());
        } while (valueOf(userInput) != QUIT);
    }

}
