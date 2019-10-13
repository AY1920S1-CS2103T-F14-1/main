package com.dukeacademy.model;

import com.dukeacademy.model.question.Question;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an question bank
 */
public interface ReadOnlyQuestionBank {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     *
     * @return the question list
     */
    ObservableList<Question> getQuestionList();

}
