package com.dukeacademy.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.dukeacademy.model.QuestionBank;
import com.dukeacademy.model.ReadOnlyQuestionBank;
import com.dukeacademy.model.question.Description;
import com.dukeacademy.model.question.Difficulty;
import com.dukeacademy.model.question.Question;
import com.dukeacademy.model.question.Status;
import com.dukeacademy.model.question.Title;
import com.dukeacademy.model.question.Topic;
import com.dukeacademy.model.question.TestCase;
import com.dukeacademy.model.question.UserProgramFilePath;
import com.dukeacademy.model.tag.Tag;

/**
 * Contains utility methods for populating {@code QuestionBank} with sample data.
 */
public class SampleDataUtil {
    /**
     * Get sample questions question [ ].
     *
     * @return the question [ ]
     */
    public static Question[] getSampleQuestions() {
        return new Question[] {
            new Question(new Title("Two Sum"), new Topic("Arrays"),
                new Status("New"),
                new Difficulty("Easy"),
                getTagSet("java"), new Description("description 1"),
                getTestCaseSet(new TestCase("input1", "result1"),
                    new TestCase("input2", "result2")),
                new UserProgramFilePath("")),
            new Question(new Title("Search Insert Position"), new Topic("Binary Search"), new Status("New"),
                new Difficulty("Easy"),
                getTagSet("java", "Google2019"), new Description("description"
                + " 2"),
                getTestCaseSet(new TestCase("input1", "result1"),
                    new TestCase("input2", "result2")),
                new UserProgramFilePath("")),
            new Question(new Title("Linked List Cycle"), new Topic("Linked List"), new Status("Passed"),
                new Difficulty("Easy"),
                getTagSet("java", "CS2040"), new Description("description 3"),
                getTestCaseSet(new TestCase("input1", "result1"),
                    new TestCase("input2", "result2")),
                new UserProgramFilePath("")),
            new Question(new Title("Happy Number"), new Topic("Hash Table"), new Status("Passed"),
                new Difficulty("Easy"),
                getTagSet("java"), new Description("description 4"),
                getTestCaseSet(new TestCase("input1", "result1"),
                    new TestCase("input2", "result2")),
                new UserProgramFilePath("")),
            new Question(new Title("Triangle"), new Topic("Dynamic Programming"), new Status("Attempted"),
                new Difficulty("Medium"),
                getTagSet("java", "100 days of Java"), new Description(
                    "description 5"),
                getTestCaseSet(new TestCase("input1", "result1"),
                    new TestCase("input2", "result2")),
                new UserProgramFilePath("")),
            new Question(new Title("Freedom Trial"), new Topic("Divide and Conquer"), new Status("New"),
                new Difficulty("Hard"),
                getTagSet("C++", "Java"), new Description("description 1"),
                getTestCaseSet(new TestCase("input1", "result1"),
                    new TestCase("input2", "result2")),
                new UserProgramFilePath(""))
        };
    }

    /**
     * Gets sample question bank.
     *
     * @return the sample question bank
     */
    public static ReadOnlyQuestionBank getSampleQuestionBank() {
        QuestionBank sampleAb = new QuestionBank();
        for (Question sampleQuestion : getSampleQuestions()) {
            sampleAb.addQuestion(sampleQuestion);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     *
     * @param strings the strings
     * @return the tag set
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a testcase list containing the list of testcases given.
     *
     * @param testcases the testcases
     * @return the testcase list
     */
    public static ArrayList<TestCase> getTestCaseSet(TestCase... testcases) {
        return new ArrayList<>(Arrays.stream(testcases)
                     .collect(Collectors.toList()));
    }

}
