package com.dukeacademy.model.question.entities;

/**
 * Represents a Question's topic number in the question bank.
 */
public enum Topic {
    /**
     * Array topic.
     */
    ARRAY,
    /**
     * Linked list topic.
     */
    LINKED_LIST,
    /**
     * Hashtable topic.
     */
    HASHTABLE,
    /**
     * Tree topic.
     */
    TREE,
    /**
     * Graph topic.
     */
    GRAPH,
    /**
     * Recursion topic.
     */
    RECURSION,
    /**
     * Divide and conquer topic.
     */
    DIVIDE_AND_CONQUER,
    /**
     * Dynamic programming topic.
     */
    DYNAMIC_PROGRAMMING,
    /**
     * Sorting topic.
     */
    SORTING,
    /**
     * Others topic.
     */
    OTHERS;

    /**
     * Contains boolean.
     *
     * @param s the s
     * @return the boolean
     */
    public static boolean contains(String s) {
        for (Topic topic:values()) {
            if (topic.name().equals(s)) {
                return true;
            }
        }
        return false;
    }
}
