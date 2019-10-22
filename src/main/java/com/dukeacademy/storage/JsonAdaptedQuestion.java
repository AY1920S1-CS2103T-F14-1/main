package com.dukeacademy.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.dukeacademy.commons.exceptions.IllegalValueException;
import com.dukeacademy.model.program.UserProgram;
import com.dukeacademy.model.question.TestCase;
import com.dukeacademy.model.question.UserProgramFilePath;
import com.dukeacademy.model.question.Description;
import com.dukeacademy.model.question.Difficulty;
import com.dukeacademy.model.question.Question;
import com.dukeacademy.model.question.Status;
import com.dukeacademy.model.question.Title;
import com.dukeacademy.model.question.Topic;
import com.dukeacademy.model.tag.Tag;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Jackson-friendly version of {@link Question}.
 */
@JsonDeserialize(using = JsonSerializableQuestionBankDeserializer.class)
//@JsonSerialize(using = JsonAdaptedQuestionSerializer.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
class JsonAdaptedQuestion {

    /**
     * The constant MISSING_FIELD_MESSAGE_FORMAT.
     */
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Question's %s field is missing!";

    @JsonProperty("title")
    public final String title;
    @JsonProperty("topic")
    public final String topic;
    @JsonProperty("status")
    public final String status;
    @JsonProperty("difficulty")
    public final String difficulty;
    @JsonProperty("tagged")
    public final List<JsonAdaptedTag> tagged = new ArrayList<>();
    @JsonProperty("description")
    public final String description;
    @JsonProperty("testCases")
    public final List<JsonAdaptedTestCase> testCases = new ArrayList<>();
    @JsonProperty("userProgramFilePath")
    public final String userProgramFilePath;

    /**
     * Constructs a {@code JsonAdaptedQuestion} with the given question details.
     *  @param title      the title
     * @param topic      the topic
     * @param status     the status
     * @param difficulty the difficulty
     * @param tagged     the tagged
     * @param description the description
     * @param testCases the test cases
     * @param userProgramFilePath
     */
    @JsonCreator
    public JsonAdaptedQuestion(@JsonProperty("title") String title,
                               @JsonProperty("topic") String topic,
                               @JsonProperty("status") String status,
                               @JsonProperty("difficulty") String difficulty,
                               @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                               @JsonProperty("description") String description,
                               @JsonProperty("testcases")
                                   List<JsonAdaptedTestCase> testCases,
                               @JsonProperty("userProgramFilePath") String userProgramFilePath) {
        this.title = title;
        this.topic = topic;
        this.status = status;
        this.difficulty = difficulty;
        this.userProgramFilePath = userProgramFilePath;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.description = description;
        this.testCases.addAll(testCases);
    }

    /**
     * Converts a given {@code Question} into this class for Jackson use.
     *
     * @param source the source
     */
    public JsonAdaptedQuestion(Question source) {
        title = source.getTitle().fullTitle;
        topic = source.getTopic().value;
        status = source.getStatus().value;
        difficulty = source.getDifficulty().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        description = source.getDescription().description;
        testCases.addAll(source.getTestCases().stream()
                     .map(t -> new JsonAdaptedTestCase(t.getInput(), t.getExpectedResult()))
                     .collect(Collectors.toList()));
        userProgramFilePath = source.getUserProgramFilePath().toString();
        System.out.print("detected user program" + source.getUserProgramFilePath().toString());

    }

    /**
     * Converts this Jackson-friendly adapted question object into the model's {@code Question} object.
     *
     * @return the question
     * @throws IllegalValueException if there were any data constraints violated in the adapted question.
     */
    public Question toModelType() throws IllegalValueException {
        final List<Tag> questionTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            questionTags.add(tag.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (topic == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Topic.class.getSimpleName()));
        }
        if (!Topic.isValidTopic(topic)) {
            throw new IllegalValueException(Topic.MESSAGE_CONSTRAINTS);
        }
        final Topic modelTopic = new Topic(topic);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = new Status(status);

        if (difficulty == null) {
            throw new IllegalValueException(String
                .format(MISSING_FIELD_MESSAGE_FORMAT, Difficulty.class.getSimpleName()));
        }
        if (!Difficulty.isValidDifficulty(difficulty)) {
            throw new IllegalValueException(Difficulty.MESSAGE_CONSTRAINTS);
        }
        final Difficulty modelDifficulty = new Difficulty(difficulty);

        if (description == null) {
            throw new IllegalValueException(String
                .format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        final List<TestCase> questionTestCases = new ArrayList<>();
        for (JsonAdaptedTestCase testCase : testCases) {
            questionTestCases.add(testCase.toModelType());
        }

        final List<TestCase> modelTestCases =
            new ArrayList<>(questionTestCases);

        final Set<Tag> modelTags = new HashSet<>(questionTags);

        if (userProgramFilePath == null) {
            throw new IllegalValueException(String
                .format(MISSING_FIELD_MESSAGE_FORMAT,
                    UserProgramFilePath.class.getSimpleName()));
        }

        final UserProgramFilePath userProgramFilePath =
            new UserProgramFilePath(this.userProgramFilePath);

        Question question = new Question(modelTitle, modelTopic, modelStatus,
            modelDifficulty, modelTags, modelDescription, modelTestCases, userProgramFilePath);

        return question;
    }

}
