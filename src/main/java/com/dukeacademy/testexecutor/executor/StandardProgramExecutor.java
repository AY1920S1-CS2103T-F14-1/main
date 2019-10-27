package com.dukeacademy.testexecutor.executor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.testexecutor.exceptions.ProgramExecutorException;
import com.dukeacademy.testexecutor.models.ClassFile;
import com.dukeacademy.testexecutor.models.ProgramInput;
import com.dukeacademy.testexecutor.models.ProgramOutput;

/**
 * Standard executor for Java class files used by the application.
 */
public class StandardProgramExecutor implements ProgramExecutor {
    private static final String MESSAGE_PROGRAM_EXECUTION_FAILED = "The program failed to execute.";

    private Runtime runtime;
    private final Logger logger = LogsCenter.getLogger(StandardProgramExecutor.class);

    /**
     * Instantiates a new Standard program executor.
     */
    public StandardProgramExecutor() {
        this.runtime = Runtime.getRuntime();
    }

    @Override
    public ProgramOutput executeProgram(ClassFile program, ProgramInput input) throws ProgramExecutorException {
        Process process = this.getExecutionProcess(program);

        logger.info("Starting program execution: " + program.getAbsolutePath());
        logger.info("Feeding program input: " + program.getAbsolutePath());

        this.feedProgramInput(process, input);

        logger.info("Processing program output: " + program.getAbsolutePath());
        ProgramOutput output = this.getProgramOutput(process);

        logger.info("Program successfully executed: " + program.getAbsolutePath());
        return output;
    }

    @Override
    public ProgramOutput executeProgram(ClassFile program) throws ProgramExecutorException {
        Process process = this.getExecutionProcess(program);
        return this.getProgramOutput(process);
    }

    /**
     * Gets the execution process using Java's RunTime standard library.
     * @param program the program to be executed as a ClassFile.
     * @return the execution process.
     * @throws ProgramExecutorException if the program fails to be executed.
     */
    private Process getExecutionProcess(ClassFile program) throws ProgramExecutorException {
        // Generate the correct command for the process
        String className = program.getCanonicalName();
        String classPath = program.getClassPath();
        String command = "java -cp " + classPath + " " + className;

        try {
            return this.runtime.exec(command);
        } catch (IOException e) {
            throw new ProgramExecutorException(MESSAGE_PROGRAM_EXECUTION_FAILED);
        }
    }

    /**
     * Gets the application's model of a program output from an execution process.
     * @param process the executing process.
     * @return the program output of the process.
     */
    private ProgramOutput getProgramOutput(Process process) {
        // Retrieve the error stream of the process
        InputStream err = process.getErrorStream();

        // Read any errors
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(err));
        String error = errorReader.lines().collect(Collectors.joining());

        // Return errored program output if present
        if (!"".equals(error)) {
            return ProgramOutput.getErroredProgramOutput(error);
        }

        // Retrieve the output stream of the process
        InputStream stdout = process.getInputStream();

        // Read the output of the process
        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
        ProgramOutput programOutput = ProgramOutput.getEmptyProgramOutput();

        // Append the output to our model
        programOutput = reader.lines().reduce(programOutput, ProgramOutput::appendNewLine,
                ProgramOutput::appendNewLine);

        // Important to kill the asynchronous process
        process.destroy();

        return programOutput;
    }

    /**
     * Feeds input into a currently executing execution process.
     * @param process the process to be fed.
     * @param programInput the inputs to be fed to the program.
     * @throws ProgramExecutorException if the input fails to be fed.
     */
    private void feedProgramInput(Process process, ProgramInput programInput) throws ProgramExecutorException {
        // Retrieve the input stream of the process
        OutputStream stdin = process.getOutputStream();

        // Try to write the input into the process
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
            writer.write(programInput.getInput());
            writer.flush();
            writer.close();

        } catch (IOException e) {
            throw new ProgramExecutorException(MESSAGE_PROGRAM_EXECUTION_FAILED);
        }
    }
}
