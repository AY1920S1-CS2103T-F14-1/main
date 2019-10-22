package com.dukeacademy.model.program;

import com.dukeacademy.model.question.Description;

import java.util.Objects;

/**
 * Represents a user's submission for a question.
 */
public class UserProgram {
    private final String className;
    private final String sourceCode;

    /**
     * Instantiates a new User program.
     *
     * @param className  the class name
     * @param sourceCode the source code
     */
    public UserProgram(String className, String sourceCode) {
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
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Class Name: ")
               .append(getCanonicalName())
               .append(" Source code: ")
               .append(getSourceCodeAsString());
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UserProgram // instanceof handles nulls
            && className.equals(((UserProgram) other).className)
            && sourceCode.equals(((UserProgram) other).sourceCode)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, sourceCode);
    }
}
