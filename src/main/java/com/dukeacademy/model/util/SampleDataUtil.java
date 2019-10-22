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
                getTagSet("java"), new Description(String.format("Given an array of integers, return indices of the two numbers such that they add up to a specific target.\n\nYou may assume that each input would have exactly one solution, and you may not use the same element twice.\n\nExample:\n\nGiven nums = [2, 7, 11, 15], target = 9,\n\nBecause nums[0] + nums[1] = 2 + 7 = 9,\nreturn [0, 1].")),
                getTestCaseSet(new TestCase("input1", "result1"),
                    new TestCase("input2", "result2")),
                new UserProgramFilePath("")),
            new Question(new Title("Search Insert Position"), new Topic("Binary Search"), new Status("New"),
                new Difficulty("Easy"),
                getTagSet("java", "Google2019"),
                new Description(String. format("Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.\n\nYou may assume no duplicates in the array.\n\nExample 1:\n\nInput: [1,3,5,6], 5\nOutput: 2\nExample 2:\n\nInput: [1,3,5,6], 2\nOutput: 1\nExample 3:\n\nInput: [1,3,5,6], 7\nOutput: 4\nExample 4:\n\nInput: [1,3,5,6], 0\nOutput: 0")),
                getTestCaseSet(new TestCase("input1", "result1"),
                    new TestCase("input2", "result2")),
                new UserProgramFilePath("")),
            new Question(new Title("Linked List Cycle"), new Topic("Linked List"), new Status("Passed"),
                new Difficulty("Easy"),
                getTagSet("java", "CS2040"), new Description(String.format("Given a linked list, determine if it has a cycle in it.\n\nTo represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.\n\n \n\nExample 1:\n\nInput: head = [3,2,0,-4], pos = 1\nOutput: true\nExplanation: There is a cycle in the linked list, where tail connects to the second node.\n\n\nExample 2:\n\nInput: head = [1,2], pos = 0\nOutput: true\nExplanation: There is a cycle in the linked list, where tail connects to the first node.\n\n\nExample 3:\n\nInput: head = [1], pos = -1\nOutput: false\nExplanation: There is no cycle in the linked list.")),
                getTestCaseSet(new TestCase("input1", "result1"),
                    new TestCase("input2", "result2")),
                new UserProgramFilePath("")),
            new Question(new Title("Happy Number"), new Topic("Hash Table"), new Status("Passed"),
                new Difficulty("Easy"),
                getTagSet("java"), new Description(String.format("Write an algorithm to determine if a number is \"happy\".\n\nA happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.\n\nExample: \n\nInput: 19\nOutput: true\nExplanation: \n12 + 92 = 82\n82 + 22 = 68\n62 + 82 = 100\n12 + 02 + 02 = 1")),
                getTestCaseSet(new TestCase("input1", "result1"),
                    new TestCase("input2", "result2")),
                new UserProgramFilePath("")),
            new Question(new Title("Triangle"), new Topic("Dynamic Programming"), new Status("Attempted"),
                new Difficulty("Medium"),
                getTagSet("java", "100 days of Java"), new Description(
                    String.format("Given an array A of positive lengths, return the largest perimeter of a triangle with non-zero area, formed from 3 of these lengths.\n\nIf it is impossible to form any triangle of non-zero area, return 0.\n\n \n\nExample 1:\n\nInput: [2,1,2]\nOutput: 5\nExample 2:\n\nInput: [1,2,1]\nOutput: 0\nExample 3:\n\nInput: [3,2,3,4]\nOutput: 10\nExample 4:\n\nInput: [3,6,2,3]\nOutput: 8\n \n\nNote:\n\n3 <= A.length <= 10000\n1 <= A[i] <= 10^6")),
                getTestCaseSet(new TestCase("input1", "result1"),
                    new TestCase("input2", "result2")),
                new UserProgramFilePath("")),
            new Question(new Title("Freedom Trial"), new Topic("Divide and Conquer"), new Status("New"),
                new Difficulty("Hard"),
                getTagSet("C++", "Java"), new Description(String.format("Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.\n\nSymbol       Value\nI             1\nV             5\nX             10\nL             50\nC             100\nD             500\nM             1000\nFor example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.\n\nRoman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:\n\nI can be placed before V (5) and X (10) to make 4 and 9. \nX can be placed before L (50) and C (100) to make 40 and 90. \nC can be placed before D (500) and M (1000) to make 400 and 900.\nGiven a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.")),
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
