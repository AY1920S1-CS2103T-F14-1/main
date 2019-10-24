package com.dukeacademy.model.question;

import static com.dukeacademy.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a user's submission for a question.
 */
public class UserProgram {
    public static final String CLASS_NAME_VALIDATION_REGEX = "[^\\s].*";

    private final String className;
    private final String sourceCode;

    /**
     * Instantiates a new User program.
     *
     * @param className  the class name
     * @param sourceCode the source code
     */
    public UserProgram(String className, String sourceCode) {
        requireAllNonNull(className, sourceCode);
        if (!className.matches(CLASS_NAME_VALIDATION_REGEX)) {
            throw new IllegalArgumentException();
        }

        this.className = className;
        this.sourceCode = sourceCode;
    }

    /**
     * Gets class name.
     *
     * @return the class name
     */
    public String getClassName() {
        return this.className;
    }

    /**
     * Gets source code as string.
     *
     * @return the source code as string
     */
    public String getSourceCodeAsString() {
        return this.sourceCode;
    }

    /**
     * Returns the canonical name of the program which is the name of the class prepended by the package. For example,
     * "com.DukeAcademy.model.solution.UserProgram".
     *
     * @return the canonical name of the user's program.
     */
    public String getCanonicalName() {
        // Check to see if the file has a package specified
        String packageStatement = this.sourceCode.split(";")[0];
        if (!packageStatement.startsWith("package")) {
            return className;
        }

        // Convert the package to an actual path
        return packageStatement.replace("package", "").trim() + "." + className;
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof UserProgram) {
            return ((UserProgram) o).className.equals(this.className)
                    && ((UserProgram) o).sourceCode.equals(this.sourceCode);
        }

        return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Class Name: ")
               .append(getCanonicalName())
               .append(" Source code: ")
               .append(getSourceCodeAsString());
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, sourceCode);
    }
}
