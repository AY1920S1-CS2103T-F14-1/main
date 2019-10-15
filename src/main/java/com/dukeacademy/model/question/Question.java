package com.dukeacademy.model.question;

import static com.dukeacademy.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import com.dukeacademy.model.solution.TestCase;
import com.dukeacademy.model.solution.UserProgram;
import com.dukeacademy.model.tag.Tag;

/**
 * Represents a Question in the question bank.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Question {

    // Identity fields
    private final Title title;

    // Description fields
    private final Topic topic;
    private final Set<Tag> tags = new HashSet<>();
    private final Difficulty difficulty;
    private final Description description;

    // Data fields
    private final Status status;
    private UserProgram userProgram = null;
    private final List<TestCase> testCases;


    /**
     * Every field must be present and not null.
     *
     * @param title      the title
     * @param topic      the topic
     * @param status     the status
     * @param difficulty the difficulty
     * @param tags       the tags
     * @param description the description
     */
    public Question(Title title, Topic topic, Status status,
                    Difficulty difficulty, Set<Tag> tags,
                    Description description, List<TestCase> testCases) {
        requireAllNonNull(title, topic, status, difficulty, tags, description
            , testCases);
        this.title = title;
        this.topic = topic;
        this.status = status;
        this.difficulty = difficulty;
        this.tags.addAll(tags);
        this.description = description;
        this.testCases = testCases;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public Title getTitle() {
        return title;
    }

    /**
     * Gets topic.
     *
     * @return the topic
     */
    public Topic getTopic() {
        return topic;
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
        return difficulty;
    }

    /**
     * Gets question description.
     *
     * @return the description
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return the tags
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns the user program currently attempted by the user.
     * If not attempted, the userProgram is null.
     *
     * @return the userProgram
     */
    public Optional<UserProgram> getUserProgram() {
        return Optional.of(userProgram);
    }

    /**
     * Returns the test cases of the question.
     *
     * @return the testcases
     */
    public List<TestCase> getTestCases() {
        // zj - can make it not modifiable
        return testCases;
    }

    /**
     * Returns true if both questions of the same title have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two questions.
     *
     * @param otherQuestion the other question
     * @return the boolean
     */
    public boolean isSameQuestion(Question otherQuestion) {
        if (otherQuestion == this) {
            return true;
        }

        return otherQuestion != null
                && otherQuestion.getTitle().equals(getTitle())
                && otherQuestion.getDescription().equals(getDescription());
    }

    /**
     * Returns true if both questions have the same identity and data fields.
     * This defines a stronger notion of equality between two questions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Question)) {
            return false;
        }

        Question otherQuestion = (Question) other;
        return otherQuestion.getTitle().equals(getTitle())
                && otherQuestion.getTopic().equals(getTopic())
                && otherQuestion.getStatus().equals(getStatus())
                && otherQuestion.getDifficulty().equals(getDifficulty())
                && otherQuestion.getTags().equals(getTags())
                && otherQuestion.getDescription().equals(getDescription())
                && otherQuestion.getUserProgram().equals(getUserProgram())
                && otherQuestion.getTestCases().equals(getTestCases());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, topic, status, difficulty, tags, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Topic: ")
                .append(getTopic())
                .append(" Status: ")
                .append(getStatus())
                .append(" Difficulty: ")
                .append(getDifficulty())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        builder.append(" Description: ")
               .append(getDescription());
        return builder.toString();
    }

}
