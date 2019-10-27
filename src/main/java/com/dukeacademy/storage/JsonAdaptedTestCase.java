package com.dukeacademy.storage;

import com.dukeacademy.commons.exceptions.IllegalValueException;
import com.dukeacademy.model.question.entities.TestCase;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Jackson-friendly version of {@link TestCase}.
 */
public class JsonAdaptedTestCase {
    @JsonProperty("input")
    private final String input;
    @JsonProperty("expectedResult")
    private final String expectedResult;

    /**
     * Constructs a {@code JsonAdaptedTestCase} with the given {@code input}
     * and {@code expectedResult}.
     *
     * @param input the input of the user
     * @param expectedResult the expected program output.
     */
    @JsonCreator
    public JsonAdaptedTestCase(@JsonProperty("input") String input,
                               @JsonProperty("expectedResult") String expectedResult) {
        this.input = input;
        this.expectedResult = expectedResult;
    }

    /**
     * Converts a given {@code TestCase} into this class for Jackson use.
     *
     * @param source the source
     */
    public JsonAdaptedTestCase(TestCase source) {
        input = source.getInput();
        expectedResult = source.getExpectedResult();
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
     * @return the expected output of the program
     */
    public String getExpectedResult() {
        return expectedResult;
    }

    /**
     * Converts this Jackson-friendly adapted test case object into the model's
     * {@code TestCase} object.
     *
     * @return the test case
     * @throws IllegalValueException if there were any data constraints
     * violated in the adapted test case.
     */
    public TestCase toModelType() throws IllegalValueException {
        // zj - add validity check for test cases: both input and output
        // cannot be null.
        return new TestCase(input, expectedResult);
    }
}