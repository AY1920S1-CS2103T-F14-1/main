package com.dukeacademy.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dukeacademy.commons.exceptions.IllegalValueException;
import com.dukeacademy.model.QuestionBank;
import com.dukeacademy.model.ReadOnlyQuestionBank;
import com.dukeacademy.model.question.Question;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * An Immutable QuestionBank that is serializable to JSON format.
 */
@JsonDeserialize(using = JsonSerializableQuestionBankDeserializer.class)
class JsonSerializableQuestionBank implements Serializable {

    /**
     * The constant MESSAGE_DUPLICATE_QUESTION.
     */
    public static final String MESSAGE_DUPLICATE_QUESTION = "Questions list "
        + "contains duplicate question(s).";

    @JsonProperty("questions")
    private final List<JsonAdaptedQuestion> questions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableQuestionBank} with the given questions.
     *
     * @param questions the questions
     */
    @JsonCreator
    public JsonSerializableQuestionBank(@JsonProperty("questions") List<JsonAdaptedQuestion> questions) {
        this.questions.addAll(questions);
    }

    /**
     * Converts a given {@code ReadOnlyQuestionBank} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableQuestionBank}.
     */
    public JsonSerializableQuestionBank(ReadOnlyQuestionBank source) {
        questions.addAll(source.getQuestionList().stream().map(JsonAdaptedQuestion::new).collect(Collectors.toList()));
    }

    /**
     * Converts this question bank into the model's {@code QuestionBank} object.
     *
     * @return the question bank
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public QuestionBank toModelType() throws IllegalValueException {
        QuestionBank questionBank = new QuestionBank();
        for (JsonAdaptedQuestion jsonAdaptedQuestion : questions) {
            Question question = jsonAdaptedQuestion.toModelType();
            if (questionBank.hasQuestion(question)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_QUESTION);
            }
            questionBank.addQuestion(question);
        }
        return questionBank;
    }

}
