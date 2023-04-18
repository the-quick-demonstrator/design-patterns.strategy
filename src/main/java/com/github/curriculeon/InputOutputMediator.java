package com.github.curriculeon;


import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * In this case, the IOMediator class acts as a mediator between user input/output and other parts of the program.
 * The various getXXXInput methods delegate to the getStringInput method, which prompts the user for input and returns the user's response.
 * The getStringInput method uses the println method to display the prompt to the user, which is a form of output.
 * <p>
 * By using the IOMediator class to handle user input/output, other parts of the program can interact with the user without needing to directly access System.in or System.out.
 * This reduces coupling between the different components of the program and makes it easier to modify or replace the input/output behavior in the future.
 */
public class InputOutputMediator {
    private final Scanner input;
    private final PrintStream output;

    public InputOutputMediator() {
        this(System.in, System.out);
    }

    public InputOutputMediator(InputStream in, OutputStream out) {
        this.input = new Scanner(in);
        this.output = new PrintStream(out);
    }


    public void print(String val, Object... args) {
        output.format(val, args);
    }

    public void println(String val, Object... vals) {
        print(val + "\n", vals);
    }

    public String getStringInput(String prompt, Object... args) {
        println(prompt, args);
        return input.nextLine();
    }

    public Double getDoubleInput(String prompt, Object... args) {
        String stringInput = getStringInput(prompt, args);
        try {
            Double doubleInput = Double.parseDouble(stringInput);
            return doubleInput;
        } catch (NumberFormatException nfe) {
            println("[ %s ] is an invalid user input!", stringInput);
            println("Try inputting a numeric value!");
            return getDoubleInput(prompt, args);
        }
    }

    public Long getLongInput(String prompt, Object... args) {
        String stringInput = getStringInput(prompt, args);
        try {
            Long longInput = Long.parseLong(stringInput);
            return longInput;
        } catch (NumberFormatException nfe) {
            println("[ %s ] is an invalid user input!", stringInput);
            println("Try inputting an integer value!");
            return getLongInput(prompt, args);
        }
    }

    public Integer getIntegerInput(String prompt, Object... args) {
        return getLongInput(prompt, args).intValue();
    }
}