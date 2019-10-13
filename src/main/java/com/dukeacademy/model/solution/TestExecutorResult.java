package com.dukeacademy.model.solution;

import java.util.List;
import java.util.Optional;

import com.dukeacademy.solution.models.CompileError;

/**
 * Represents the results of running a user's solution against the specified test cases
 */
public class TestExecutorResult {
    private final List<TestCaseResult> results;
    private final long numPassed;
    private final Optional<CompileError> compileError;

    /**
     * Instantiates a new Test executor result.
     *
     * @param results the results
     */
    public TestExecutorResult(List<TestCaseResult> results) {
        this.results = results;
        this.numPassed = results.parallelStream().filter(TestCaseResult::isSuccessful).count();
        this.compileError = Optional.empty();
    }

    /**
     * Instantiates a new Test executor result.
     *
     * @param results      the results
     * @param compileError the compile error
     */
    public TestExecutorResult(List<TestCaseResult> results, CompileError compileError) {
        this.results = results;
        this.numPassed = results.parallelStream().filter(TestCaseResult::isSuccessful).count();
        this.compileError = Optional.of(compileError);
    }

    /**
     * Gets results.
     *
     * @return the results
     */
    public List<TestCaseResult> getResults() {
        return results;
    }

    /**
     * Gets num passed.
     *
     * @return the num passed
     */
    public long getNumPassed() {
        return numPassed;
    }

    /**
     * Gets compile error.
     *
     * @return the compile error
     */
    public Optional<CompileError> getCompileError() {
        return compileError;
    }
}
