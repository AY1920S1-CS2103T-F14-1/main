package com.dukeacademy.model.tag;

import static com.dukeacademy.commons.util.AppUtil.checkArgument;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Tag in the question bank.
 * Guarantees: immutable; title is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    /**
     * The constant MESSAGE_CONSTRAINTS.
     */
    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
<<<<<<< HEAD
    /**
     * The constant VALIDATION_REGEX.
     */
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";
=======
    public static final String VALIDATION_REGEX = "[^\\s].*";
>>>>>>> zj_refactor

    /**
     * The Tag name.
     */
    public final String tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     *
     * @param test the test
     * @return the boolean
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagName.equals(((Tag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

}
