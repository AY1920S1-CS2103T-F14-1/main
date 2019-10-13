package com.dukeacademy.solution.exceptions;

/**
 * Exception thrown by the compiler environment.
 */
public class CompilerEnvironmentException extends Exception {
    /**
     * Instantiates a new Compiler environment exception.
     *
     * @param message the message
     */
    public CompilerEnvironmentException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Compiler environment exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public CompilerEnvironmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
