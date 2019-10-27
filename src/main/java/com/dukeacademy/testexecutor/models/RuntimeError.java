package com.dukeacademy.testexecutor.models;

/**
 * Represents a runtime error encountered when executing a test case against a program.
 */
public class RuntimeError {
    private final String errorMessage;

    /**
     * Instantiates a new Runtime error.
     *
     * @param errorMessage the error message
     */
    public RuntimeError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof RuntimeError) {
            return ((RuntimeError) o).errorMessage.equals(this.errorMessage);
        } else {
            return false;
        }
    }
}
