package com.dukeacademy.testexecutor.models;

/**
 * Represents a compile error encountered when compiling Java programs. The current implementation only contains the
 * error message shown by the compiler.
 */
public class CompileError {
    private final String errorMessage;

    /**
     * Instantiates a new Compile error.
     *
     * @param errorMessage the error message
     */
    public CompileError(String errorMessage) {
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

    /**
     * Returns true if the object is another CompileError instance with the same error message.
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof CompileError) {
            CompileError other = (CompileError) object;
            return other.errorMessage.equals(this.errorMessage);
        } else {
            return false;
        }
    }
}
