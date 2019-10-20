package com.dukeacademy.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import com.dukeacademy.commons.core.GuiSettings;
import com.dukeacademy.model.question.Question;

import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Question> PREDICATE_SHOW_ALL_QUESTIONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' question bank file path.
     */
    Path getQuestionBankFilePath();

    /**
     * Sets the user prefs' question bank file path.
     */
    void setQuestionBankFilePath(Path questionBankFilePath);

    /**
     * Replaces question bank data with the data in {@code questionBank}.
     */
    void setStandardQuestionBank(QuestionBank standardQuestionBank);

    /** Returns the QuestionBank */
    QuestionBank getStandardQuestionBank();

    /**
     * Returns true if a question with the same identity as {@code question} exists in the question bank.
     */
    boolean hasQuestion(Question question);

    /**
     * Deletes the given question.
     * The question must exist in the question bank.
     */
    void deleteQuestion(Question target);

    /**
     * Adds the given question.
     * {@code question} must not already exist in the question bank.
     */
    void addQuestion(Question question);

    /**
     * Replaces the given question {@code target} with {@code editedQuestion}.
     * {@code target} must exist in the question bank.
     * The question identity of {@code editedQuestion} must not be the same as
     * another existing question in the question bank.
     */
    void setQuestion(Question target, Question editedQuestion);

    /** Returns an unmodifiable view of the filtered question list */
    ObservableList<Question> getFilteredQuestionList();

    /**
     * Updates the filter of the filtered question list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredQuestionList(Predicate<Question> predicate);
}
