package com.dukeacademy.testexecutor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.dukeacademy.testexecutor.exceptions.ProgramExecutorException;
import com.dukeacademy.testexecutor.executor.StandardProgramExecutor;
import com.dukeacademy.testexecutor.models.ClassFile;
import com.dukeacademy.testexecutor.models.ProgramInput;
import com.dukeacademy.testexecutor.models.ProgramOutput;

class StandardProgramExecutorTest {
    private static StandardProgramExecutor executor;

    private static Path testProgramRootFolder = Paths.get("src", "test", "data", "TestPrograms",
            "ProgramExecutor");
    private static String noInputTestOutput;
    private static String withInputTestOutput;
    private static String input;

    @BeforeAll
    public static void initializeTest() throws IOException {
        noInputTestOutput = Files.readString(testProgramRootFolder.resolve("NoInputTestResult.txt"));
        withInputTestOutput = Files.readString(testProgramRootFolder.resolve("WithInputTestResult.txt"));
        input = Files.readString(testProgramRootFolder.resolve("Input.txt"));
        executor = new StandardProgramExecutor();
    }

    @Test
    public void executeProgramNoInput() throws FileNotFoundException, ProgramExecutorException {
        ClassFile programClassFile = new ClassFile("NoInputTest", testProgramRootFolder.toUri().getPath());
        ProgramOutput output = executor.executeProgram(programClassFile);

        assertFalse(output.getRuntimeError().isPresent());
        assertEquals(noInputTestOutput, output.getOutput());
    }

    @Test
    public void executeProgramWithInput() throws FileNotFoundException, ProgramExecutorException {
        ClassFile programClassFile = new ClassFile("WithInputTest", testProgramRootFolder.toUri().getPath());

        ProgramInput programInput = new ProgramInput(input);
        ProgramOutput output = executor.executeProgram(programClassFile, programInput);

        assertFalse(output.getRuntimeError().isPresent());
        assertEquals(withInputTestOutput, output.getOutput());
    }

    @Test
    public void testForRuntimeError() throws ProgramExecutorException, FileNotFoundException {
        ClassFile programClassFile = new ClassFile("OutOfBounds", testProgramRootFolder.toUri().getPath());

        ProgramOutput output = executor.executeProgram(programClassFile);
        assertTrue(output.getRuntimeError().isPresent());
    }
}
