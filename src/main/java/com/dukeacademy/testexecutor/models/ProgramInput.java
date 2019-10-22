package com.dukeacademy.testexecutor.models;

/**
 * Represents input to be fed into a program executed by the application.
 */
public class ProgramInput {
    private String input;

    /**
     * Instantiates a new Program input.
     *
     * @param input the input
     */
    public ProgramInput(String input) {
        this.input = input;
    }

    /**
     * Gets input.
     *
     * @return the input
     */
    public String getInput() {
        return this.input;
    }
}
