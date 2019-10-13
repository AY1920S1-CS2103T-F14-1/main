package com.dukeacademy.model.solution;

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
}
