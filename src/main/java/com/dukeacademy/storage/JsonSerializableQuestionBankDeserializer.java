package com.dukeacademy.storage;

import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.ui.QuestionListPanel;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JsonSerializableQuestionBankDeserializer extends StdDeserializer<JsonSerializableQuestionBank> {

    private final Logger logger = LogsCenter.getLogger(QuestionListPanel.class);
    public JsonSerializableQuestionBankDeserializer() {
        this(null);
    }

    public JsonSerializableQuestionBankDeserializer(Class<?> vc) {
        super(vc);
    }

    // return JsonSerializableQuestionBank
    @Override public JsonSerializableQuestionBank deserialize(JsonParser p,
                                                     DeserializationContext ctxt)
        throws IOException, JsonProcessingException {
        JsonNode jsonQuestionBankNode = p.getCodec().readTree(p);
        logger.info(jsonQuestionBankNode.toString());
        ArrayNode rawJsonAdaptedQuestionList =
            (ArrayNode) jsonQuestionBankNode.get("questions");

        List<JsonAdaptedQuestion> jsonAdaptedQuestionList = new ArrayList<>();
        for (int i = 0; i < rawJsonAdaptedQuestionList.size(); i++) {
            JsonNode jsonAdaptedQuestionNode = rawJsonAdaptedQuestionList.get(i);
            String title = jsonAdaptedQuestionNode.get("title").textValue();
            String topic = jsonAdaptedQuestionNode.get("topic").textValue();
            String status = jsonAdaptedQuestionNode.get("status").textValue();
            String difficulty = jsonAdaptedQuestionNode.get("difficulty").textValue();
            ArrayNode rawTagList = (ArrayNode) jsonAdaptedQuestionNode.get("tagged");
            List<JsonAdaptedTag> tagList = new ArrayList<>();
            for (int j = 0; j < rawTagList.size(); j++) {
                String tagName = rawTagList.get(j).textValue();
                JsonAdaptedTag tag = new JsonAdaptedTag(tagName);
                tagList.add(tag);
            }
            String description = jsonAdaptedQuestionNode.get("description").textValue();
            ArrayNode rawTestCaseList = (ArrayNode) jsonAdaptedQuestionNode.get("testCases");
            List<JsonAdaptedTestCase> testCaseList = new ArrayList<>();
            for (int k = 0; k < rawTestCaseList.size(); k++) {
                String input = rawTestCaseList.get(k).get("input").textValue();
                String expectedResult =
                    rawTestCaseList.get(k).get("expectedResult").textValue();
                JsonAdaptedTestCase testCase =
                    new JsonAdaptedTestCase(input, expectedResult);
                testCaseList.add(testCase);
            }
            JsonAdaptedQuestion jsonAdaptedQuestion =
                new JsonAdaptedQuestion(title, topic, status, difficulty,
                    tagList, description, testCaseList);
            jsonAdaptedQuestionList.add(jsonAdaptedQuestion);
        }
        JsonSerializableQuestionBank jsonSerializableQuestionBank =
            new JsonSerializableQuestionBank(jsonAdaptedQuestionList);
        return jsonSerializableQuestionBank;
    }
}
