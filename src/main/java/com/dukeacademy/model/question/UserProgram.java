package com.dukeacademy.model.question;

import static com.dukeacademy.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a user's submission for a question.
 */
public class UserProgram {
    public static final String CLASS_NAME_VALIDATION_REGEX = "[^\\s].*";

    private final String className;
    private final String sourceCode;

    public UserProgram(String className, String sourceCode) {
        requireAllNonNull(className, sourceCode);
        if (!className.matches(CLASS_NAME_VALIDATION_REGEX)) {
            throw new IllegalArgumentException();
        }

        this.className = className;
        this.sourceCode = sourceCode;
    }

    public String getClassName() {
        return this.className;
    }

    public String getSourceCode() {
        return this.sourceCode;
    }

    /**
     * Returns the canonical name of the program which is the name of the class prepended by the package. For example,
     * "com.DukeAcademy.model.solution.UserProgram".
     * @return the canonical name of the user's program.
     */
    public String getCanonicalName() {
        // Check to see if the file has a package specified
        String packageStatement = this.sourceCode.split(";")[0];
        if (!packageStatement.startsWith("package")) {
            return className;
        }

        // Convert the package to an actual path
        return packageStatement.replaceFirst("package", "").trim() + "." + className;
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof UserProgram) {
            return ((UserProgram) o).className.equals(this.className)
                    && ((UserProgram) o).sourceCode.equals(this.sourceCode);
        }

        return false;
    }
}
