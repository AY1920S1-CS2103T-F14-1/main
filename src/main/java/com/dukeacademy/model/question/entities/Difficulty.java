package com.dukeacademy.model.question.entities;

/**
 * Represents a Question's difficulty in the question bank.
 */
public enum Difficulty {
    /**
     * Easy difficulty.
     */
    EASY,
    /**
     * Medium difficulty.
     */
    MEDIUM,
    /**
     * Hard difficulty.
     */
    HARD;

    public static boolean contains(String s)
    {
        for(Difficulty difficulty:values())
            if (difficulty.name().equals(s))
                return true;
        return false;
    }
}
