package com.dukeacademy.model.profile;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;
import static com.dukeacademy.commons.util.AppUtil.checkArgument;

/**
 * The type Profile.
 */
public class Profile {
    /**
     * The Profile.
     */
    public final String profile;
    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
    }

    /**
     * Instantiates a new Profile.
     *
     * @param s the s
     */
    public Profile(String s) {
        profile = s;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return profile;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof Profile)) {
            return false;
        }

        // state check
        Profile other = (Profile) obj;
        return profile.equals(other.profile);
    }
}
