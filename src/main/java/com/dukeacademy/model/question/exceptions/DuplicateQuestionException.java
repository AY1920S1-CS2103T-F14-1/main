package com.dukeacademy.model.question.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
class DuplicateQuestionException extends RuntimeException {
    /**
     * Instantiates a new Duplicate question exception.
     */
    public DuplicateQuestionException() {
        super("Operation would result in duplicate persons");
    }
}
