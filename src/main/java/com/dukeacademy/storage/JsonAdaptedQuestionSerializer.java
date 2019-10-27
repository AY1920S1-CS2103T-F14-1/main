package com.dukeacademy.storage;

import com.dukeacademy.storage.question.JsonAdaptedQuestion;
import com.dukeacademy.storage.question.JsonAdaptedTestCase;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonAdaptedQuestionSerializer extends StdSerializer<JsonAdaptedQuestion> {
   public JsonAdaptedQuestionSerializer() {
       this(null);
   }

   public JsonAdaptedQuestionSerializer(Class<JsonAdaptedQuestion> question) {
       super(question);
   }

    @Override
    public void serialize(JsonAdaptedQuestion value, JsonGenerator gen,
                          SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("title", value.getTitle());
        List<String> topics = new ArrayList<>(value.getTopics());
        gen.writeObjectField("topic", topics);
        gen.writeStringField("status", value.getStatus());
        gen.writeStringField("difficulty", value.getDifficulty());
        gen.writeStringField("description", value.getDescription());
        gen.writeObjectField("userProgram", value.getUserProgram());
        List<JsonAdaptedTestCase> testCases = new ArrayList<>(value.getTestCases());
        gen.writeObjectField("testCases", testCases);
        gen.writeEndObject();
    }
}
