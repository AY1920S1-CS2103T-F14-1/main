package com.dukeacademy.model.question;

import com.dukeacademy.model.solution.UserProgram;

import static com.dukeacademy.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

public class UserProgramFilePath {
    public static final String MESSAGE_CONSTRAINTS = "User Program file paths"
        + " should either be empty string, or contains the relative file path"
        + " which stores the user program for a particular question. ";

    /**
     * The constant VALIDATION_REGEX.
     */
    /*
     * The first character of the difficulty must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    // zj - add validation check for file paths.
    public static final String VALIDATION_REGEX = "[^\\s].*";

    /**
     * The file path.
     */
    public final String filePath;

    /**
     * Constructs a {@code UserProgramFilePath}.
     *
     * @param filePath A valid file path.
     */
    public UserProgramFilePath(String filePath) {
        requireNonNull(filePath);
        checkArgument(isValidUserProgramFilePath(filePath), MESSAGE_CONSTRAINTS);
        this.filePath = filePath;
    }

    public static boolean isValidUserProgramFilePath(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return filePath;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UserProgramFilePath // instanceof handles nulls
            && filePath.equals(((UserProgramFilePath) other).filePath)); // state
        // check
    }

    @Override
    public int hashCode() {
        return filePath.hashCode();
    }
}
