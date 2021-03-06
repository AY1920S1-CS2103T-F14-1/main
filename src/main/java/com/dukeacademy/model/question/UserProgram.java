package com.dukeacademy.model.question;

import static com.dukeacademy.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a user's submission for a question.
 */
public class UserProgram {
    private final String canonicalName;
    private final String sourceCode;

    /**
     * Instantiates a new User program.
     *
     * @param canonicalName the canonical name
     * @param sourceCode    the source code
     */
    public UserProgram(String canonicalName, String sourceCode) {
        requireAllNonNull(canonicalName, sourceCode);

        this.canonicalName = canonicalName;
        this.sourceCode = sourceCode;
    }

    /**
     * Gets canonical name.
     *
     * @return the canonical name
     */
    public String getCanonicalName() {
        return this.canonicalName;
    }

    /**
     * Gets source code.
     *
     * @return the source code
     */
    public String getSourceCode() {
        return this.sourceCode;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof UserProgram) {
            UserProgram other = (UserProgram) object;
            return other.canonicalName.equals(this.canonicalName)
                    && other.sourceCode.equals(this.sourceCode);
        }

        return false;
    }

    @Override
    public String toString() {
        return "Canonical name : " + canonicalName + "\nSource code : " + sourceCode;
    }
}
