package com.dukeacademy.model.solution;

import java.util.Objects;

/**
 * Represents a test case for a question.
 */
public class TestCase {
    private final String input;
    private final String expectedResult;

    /**
     * Instantiates a new Test case.
     *
     * @param input          the input
     * @param expectedResult the expected result
     */
    public TestCase(String input, String expectedResult) {
        this.input = input;
        this.expectedResult = expectedResult;
    }

    /**
     * Gets input.
     *
     * @return the input
     */
    public String getInput() {
        return input;
    }

    /**
     * Gets expected result.
     *
     * @return the expected result
     */
    public String getExpectedResult() {
        return expectedResult;
    }

    @Override
    public String toString() {
        return "Input: " + input + "Expected: " + expectedResult;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TestCase // instanceof handles nulls
            && input.equals(((TestCase) other).getInput())
            && expectedResult.equals(((TestCase) other).getExpectedResult())); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(input, expectedResult);
    }
}
