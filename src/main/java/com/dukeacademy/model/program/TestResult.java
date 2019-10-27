package com.dukeacademy.model.program;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dukeacademy.testexecutor.models.CompileError;

/**
 * Represents the results of running a user's solution against the specified test cases
 */
public class TestResult {
    private final List<TestCaseResult> results;
    private final long numPassed;
    private final Optional<CompileError> compileError;


    /**
     * Instantiates a new Test executor result.
     *
     * @param results the results
     */
    public TestResult(List<TestCaseResult> results) {
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
    public TestResult(CompileError compileError) {
        this.results = new ArrayList<>();
        this.numPassed = 0;
        this.compileError = Optional.of(compileError);
    }

    public boolean isSuccessful() {
        return this.numPassed == results.size();
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

    @Override
    public boolean equals(Object o) {
        if (o instanceof TestResult) {
            return ((TestResult) o).results.equals(this.results)
                    && ((TestResult) o).numPassed == this.numPassed
                    && ((TestResult) o).compileError.equals(this.compileError);
        } else {
            return false;
        }
    }
}
