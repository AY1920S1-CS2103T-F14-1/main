package com.dukeacademy.model.question;

import static com.dukeacademy.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dukeacademy.model.program.UserProgram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestionTest {
    private final String validTitle = "Test question";
    private final Status validStatus = Status.ATTEMPTED;
    private final Difficulty validDifficulty = Difficulty.EASY;
    private final Set<Topic> validTopics = new HashSet<>();
    private final List<TestCase> validTestCases = new ArrayList<>();
    private final UserProgram validUserProgram = new UserProgram("Test", "");

    @BeforeEach
    void populateTopicsAndTestCases() {
        this.validTopics.add(Topic.TREE);
        this.validTopics.add(Topic.DYNAMIC_PROGRAMMING);

        this.validTestCases.add(new TestCase("1", "1"));
        this.validTestCases.add(new TestCase("2", "2"));
        this.validTestCases.add(new TestCase("3", "3"));
    }

    @Test
    void constructor() {
        assertThrows(NullPointerException.class, () -> new Question(null, validStatus, validDifficulty, validTopics, validTestCases, validUserProgram));
        assertThrows(NullPointerException.class, () -> new Question(validTitle, null, validDifficulty, validTopics, validTestCases, validUserProgram));
        assertThrows(NullPointerException.class, () -> new Question(validTitle, validStatus, null, validTopics, validTestCases, validUserProgram));
        assertThrows(NullPointerException.class, () -> new Question(validTitle, validStatus, validDifficulty, null, validTestCases, validUserProgram));
        assertThrows(NullPointerException.class, () -> new Question(validTitle, validStatus, validDifficulty, validTopics, null, validUserProgram));
        assertThrows(NullPointerException.class, () -> new Question(validTitle, validStatus, validDifficulty, validTopics, validTestCases, null));

        Question validQuestion = new Question(validTitle, validStatus, validDifficulty, validTopics, validTestCases, validUserProgram);
        assertEquals(validTitle, validQuestion.getTitle());
        assertEquals(validStatus, validQuestion.getStatus());
        assertEquals(validDifficulty, validQuestion.getDifficulty());
        assertEquals(validTopics, validQuestion.getTopics());
        assertEquals(validTestCases, validQuestion.getTestCases());
        assertEquals(validUserProgram, validQuestion.getUserProgram());
    }

    @Test
    void checkValidTitle() {
        assertTrue(Question.checkValidTitle("Sum 123!@#%"));
        assertTrue(Question.checkValidTitle("!"));
        assertFalse(Question.checkValidTitle(""));
        assertFalse(Question.checkValidTitle("    "));
    }
}
