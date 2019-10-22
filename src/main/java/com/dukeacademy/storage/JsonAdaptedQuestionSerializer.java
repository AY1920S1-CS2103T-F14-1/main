package com.dukeacademy.storage;

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
       gen.writeStringField("title", value.title);
       gen.writeStringField("topic", value.topic);
       gen.writeStringField("status", value.status);
       gen.writeStringField("difficulty", value.difficulty);
       gen.writeStringField("description", value.description);
       gen.writeStringField("userProgramFilePath", value.userProgramFilePath);
       List<JsonAdaptedTag> tagged = new ArrayList<>(value.tagged);
       gen.writeObjectField("tagged", tagged);
       List<JsonAdaptedTestCase> testCases = new ArrayList<>(value.testCases);
       gen.writeObjectField("testCases", testCases);
       gen.writeEndObject();
    }
}
