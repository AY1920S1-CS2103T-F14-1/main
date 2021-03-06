package com.dukeacademy.testexecutor.executor;

import java.util.concurrent.CompletableFuture;

import com.dukeacademy.testexecutor.executor.exceptions.ProgramExecutorException;
import com.dukeacademy.testexecutor.models.ClassFile;
import com.dukeacademy.testexecutor.models.ProgramInput;
import com.dukeacademy.testexecutor.models.ProgramOutput;

/**
 * Represents a Java program executor..
 */
public interface ProgramExecutor {
    /**
     * Returns a completable future that will execute the given Java class file with the given program input.
     *
     * @param program the program to be executed
     * @param input   the input to be fed into the program
     * @return the program output
     * @throws ProgramExecutorException if the execution fails
     */
    CompletableFuture<ProgramOutput> executeProgram(ClassFile program, ProgramInput input)
            throws ProgramExecutorException;
}
