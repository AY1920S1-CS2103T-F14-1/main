package com.dukeacademy.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import com.dukeacademy.model.question.Question;
import com.dukeacademy.model.question.UniqueQuestionList;

import javafx.collections.ObservableList;

/**
 * Wraps all data at the question-bank level
 * Duplicates are not allowed (by .isSameQuestion comparison)
 */
public class StandardQuestionBank implements QuestionBank {

    private final UniqueQuestionList questions;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        questions = new UniqueQuestionList();
    }

    public StandardQuestionBank() {}

    /**
     * Creates an QuestionBank using the Questions in the {@code toBeCopied}
     */
    public StandardQuestionBank(QuestionBank toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the question list with {@code questions}.
     * {@code questions} must not contain duplicate questions.
     */
    public void setQuestions(List<Question> questions) {
        this.questions.setQuestions(questions);
    }

    /**
     * Resets the existing data of this {@code QuestionBank} with {@code newData}.
     */
    public void resetData(QuestionBank newData) {
        requireNonNull(newData);

        setQuestions(newData.getQuestionList());
    }

    //// question-level operations

    /**
     * Returns true if a question with the same identity as {@code question} exists in the question bank.
     */
    public boolean hasQuestion(Question question) {
        requireNonNull(question);
        return questions.contains(question);
    }

    /**
     * Adds a question to the question bank.
     * The question must not already exist in the question bank.
     */
    public void addQuestion(Question p) {
        questions.add(p);
    }

    /**
     * Replaces the given question {@code target} in the list with {@code editedQuestion}.
     * {@code target} must exist in the question bank.
     * The question identity of {@code editedQuestion} must not be the same as
     * another existing question in the question bank.
     */
    public void setQuestion(Question target, Question editedQuestion) {
        requireNonNull(editedQuestion);

        questions.setQuestion(target, editedQuestion);
    }

    /**
     * Removes {@code key} from this {@code QuestionBank}.
     * {@code key} must exist in the question bank.
     */
    public void removeQuestion(Question key) {
        questions.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return questions.asUnmodifiableObservableList().size() + " questions";
        // TODO: refine later
    }

    @Override
    public ObservableList<Question> getQuestionList() {
        return questions.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StandardQuestionBank // instanceof handles nulls
                && questions.equals(((StandardQuestionBank) other).questions));
    }

    @Override
    public int hashCode() {
        return questions.hashCode();
    }
}
