package com.dukeacademy.model.solution;

import java.util.Optional;

import com.dukeacademy.solution.models.RuntimeError;

/**
 * Represents the results of running the user's solution against an individual test case. It contains a boolean
 * flag to indicate the success of running the test case, along with the expected and actual results.
 */
public class TestCaseResult {
    private final boolean isSuccessful;
    private final String expectedOutput;
    private final String actualOutput;
    private final Optional<RuntimeError> runtimeError;

    /**
     * Instantiates a new Test case result.
     *
     * @param isSuccessful   the is successful
     * @param expectedOutput the expected output
     * @param actualOutput   the actual output
     */
    public TestCaseResult(boolean isSuccessful, String expectedOutput, String actualOutput) {
        this.isSuccessful = isSuccessful;
        this.expectedOutput = expectedOutput;
        this.actualOutput = actualOutput;
        this.runtimeError = Optional.empty();
    }

    private TestCaseResult(boolean isSuccessful, String expectedOutput,
                           String actualOutput, Optional<RuntimeError> runtimeError) {
        this.isSuccessful = isSuccessful;
        this.expectedOutput = expectedOutput;
        this.actualOutput = actualOutput;
        this.runtimeError = runtimeError;
    }

    /**
     * Gets errored test case result.
     *
     * @param expectedOutput the expected output
     * @param errorMessage   the error message
     * @return the errored test case result
     */
    public static TestCaseResult getErroredTestCaseResult(String expectedOutput, String errorMessage) {
        RuntimeError error = new RuntimeError(errorMessage);
        return new TestCaseResult(false, expectedOutput, "", Optional.of(error));
    }

    /**
     * Is successful boolean.
     *
     * @return the boolean
     */
    public boolean isSuccessful() {
        return isSuccessful;
    }

    /**
     * Gets expected output.
     *
     * @return the expected output
     */
    public String getExpectedOutput() {
        return expectedOutput;
    }

    /**
     * Gets actual output.
     *
     * @return the actual output
     */
    public String getActualOutput() {
        return actualOutput;
    }

    /**
     * Gets runtime error.
     *
     * @return the runtime error
     */
    public Optional<RuntimeError> getRuntimeError() {
        return this.runtimeError;
    }

    @Override
    public String toString() {
        return "Success: " + this.isSuccessful + "\n"
                + "Expected: " + this.expectedOutput
                + "Actual: " + this.actualOutput
                + "Error: " + this.runtimeError.isPresent() + "\n";
    }
}
