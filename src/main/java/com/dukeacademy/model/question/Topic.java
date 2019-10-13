package com.dukeacademy.model.question;

import static com.dukeacademy.commons.util.AppUtil.checkArgument;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Question's topic number in the question bank.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Topic {


    /**
     * The constant MESSAGE_CONSTRAINTS.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Topic numbers should only contain numbers, and it should be at least 3 digits long";
    /**
     * The constant VALIDATION_REGEX.
     */
    public static final String VALIDATION_REGEX = "\\d{3,}";
    /**
     * The Value.
     */
    public final String value;

    /**
     * Constructs a {@code Topic}.
     *
     * @param topic A valid topic number.
     */
    public Topic(String topic) {
        requireNonNull(topic);
        checkArgument(isValidPhone(topic), MESSAGE_CONSTRAINTS);
        value = topic;
    }

    /**
     * Returns true if a given string is a valid topic number.
     *
     * @param test the test
     * @return the boolean
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Topic // instanceof handles nulls
                && value.equals(((Topic) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
