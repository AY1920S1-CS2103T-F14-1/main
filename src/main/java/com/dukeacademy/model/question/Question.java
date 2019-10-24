package com.dukeacademy.model.question;

import static com.dukeacademy.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.dukeacademy.model.question.entities.Difficulty;
import com.dukeacademy.model.question.entities.Status;
import com.dukeacademy.model.question.entities.TestCase;
import com.dukeacademy.model.question.entities.Topic;

/**
 * Represents a Question in the question bank.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Question {
    public static final String TITLE_VALIDATION_REGEX = "[^\\s].*";

    private final String title;
    private final Status status;
    private final Difficulty difficulty;
    private final Set<Topic> topics = new HashSet<>();
    private final List<TestCase> testCases = new ArrayList<>();
    private final UserProgram userProgram;
    private final String description;
    /**
     * Every field must be present and not null.
     *
     * @param title      the title
     * @param topic      the topic
     * @param status     the status
     * @param difficulty the difficulty
     *
     * @param description the description
     */
    public Question(String title, Status status, Difficulty difficulty, Set<Topic> topics,
                    List<TestCase> testCases, UserProgram userProgram,
                    String description) {
        requireAllNonNull(title, status, difficulty, topics, testCases, userProgram);
        if (!Question.checkValidTitle(title)) {
            throw new IllegalArgumentException();
        }

        this.title = title;
        this.status = status;
        this.difficulty = difficulty;
        this.topics.addAll(topics);
        this.testCases.addAll(testCases);
        this.userProgram = new UserProgram(userProgram.getClassName(), userProgram.getSourceCodeAsString());
        this.description = description;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }
    /**
     * Gets status.
     *
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Gets difficulty.
     *
     * @return the difficulty
     */
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    /**
     * Gets topic.
     *
     * @return the topic
     */
    public Set<Topic> getTopics() {
        return Collections.unmodifiableSet(this.topics);
    }

    /**
     * Gets question description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }


    /**
     * Returns the file path which stores the user program currently attempted
     * by the user.
     * If not attempted, the file path is an empty string.
     *
     * @return the userProgram
     */
    public UserProgram getUserProgram() {
        return new UserProgram(this.userProgram.getClassName(), this.userProgram.getSourceCodeAsString());
    }

    /**
     * Returns the test cases of the question.
     *
     * @return the testcases
     */
    public List<TestCase> getTestCases() {
        return new ArrayList<>(this.testCases);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Status: ")
                .append(getStatus())
                .append(" Difficulty: ")
                .append(getDifficulty())
                .append(" Topics: ");
        this.getTopics().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Checks if the given string is a valid title for a question. Titles must be alphanumeric.
     * @param title the string to be checked.
     * @return true if the string is a valid title.
     */
    public static boolean checkValidTitle(String title) {
        return title.matches(TITLE_VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Question) {
            Question other = (Question) o;
            return other.getTitle().equals(this.title)
                    && other.getStatus().equals(this.status)
                    && other.getDifficulty().equals(this.difficulty)
                    && other.getTopics().equals(this.topics)
                    && other.getTestCases().equals(this.testCases)
                    && other.getUserProgram().equals(this.userProgram);
        }

        return false;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, topics, status, difficulty, description);
    }
}


