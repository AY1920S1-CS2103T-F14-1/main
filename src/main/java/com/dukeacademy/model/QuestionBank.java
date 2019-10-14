package com.dukeacademy.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.model.question.Question;
import com.dukeacademy.model.question.UniqueQuestionList;

import javafx.collections.ObservableList;

/**
 * Wraps all data at the question-bank level
 * Duplicates are not allowed (by .isSameQuestion comparison)
 */
public class QuestionBank implements ReadOnlyQuestionBank {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final UniqueQuestionList questions;
    private final ArrayList<String> profile;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        questions = new UniqueQuestionList();
        profile = new ArrayList<>();
        profile.add("initialized dummy text");
    }

    /**
     * Instantiates a new Question bank.
     */
    public QuestionBank() {}

    /**
     * Creates an QuestionBank using the Questions in the {@code toBeCopied}
     *
     * @param toBeCopied the to be copied
     */
    public QuestionBank(ReadOnlyQuestionBank toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the question list with {@code questions}.
     * {@code questions} must not contain duplicate questions.
     *
     * @param questions the questions
     */
    public void setQuestions(List<Question> questions) {
        this.questions.setQuestions(questions);
    }

    /**
     * Resets the existing data of this {@code QuestionBank} with {@code newData}.
     *
     * @param newData the new data
     */
    public void resetData(ReadOnlyQuestionBank newData) {
        requireNonNull(newData);

        setQuestions(newData.getQuestionList());
    }

    //// question-level operations

    /**
     * Returns true if a question with the same identity as {@code question} exists in the question bank.
     *
     * @param question the question
     * @return the boolean
     */
    public boolean hasQuestion(Question question) {
        requireNonNull(question);
        return questions.contains(question);
    }

    /**
     * Adds a question to the question bank.
     * The question must not already exist in the question bank.
     *
     * @param p the p
     */
    public void addQuestion(Question p) {
        questions.add(p);
    }

    /**
     * Replaces the given question {@code target} in the list with {@code editedQuestion}.
     * {@code target} must exist in the question bank.
     * The question identity of {@code editedQuestion} must not be the same as
     * another existing question in the question bank.
     *
     * @param target         the target
     * @param editedQuestion the edited question
     */
    public void setQuestion(Question target, Question editedQuestion) {
        requireNonNull(editedQuestion);

        questions.setQuestion(target, editedQuestion);
    }

    /**
     * Removes {@code key} from this {@code QuestionBank}.
     * {@code key} must exist in the question bank.
     *
     * @param key the key
     */
    public void removeQuestion(Question key) {
        questions.remove(key);
    }

    /**
     * Update profile.
     *
     * @param s the string that represents profile information.
     */
    public void updateProfile(String s) {
        profile.clear();
        profile.add(s);
        logger.info("profile updated in questionbank " + profile.get(0));
    }

    /**
     * Gets profile.
     *
     * @return the profile
     */
    public ArrayList<String> getProfile() {
        return profile;
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
                || (other instanceof QuestionBank // instanceof handles nulls
                && questions.equals(((QuestionBank) other).questions));
    }

    @Override
    public int hashCode() {
        return questions.hashCode();
    }
}
