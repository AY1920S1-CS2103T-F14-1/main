package com.dukeacademy.logic.commands.submit;

import java.util.Optional;

import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.commands.CommandResult;
import com.dukeacademy.logic.commands.exceptions.CommandException;
import com.dukeacademy.logic.program.ProgramSubmissionLogic;
import com.dukeacademy.logic.question.QuestionsLogic;
import com.dukeacademy.model.program.TestResult;
import com.dukeacademy.model.question.Question;
import com.dukeacademy.model.question.UserProgram;
import com.dukeacademy.model.question.entities.Status;
import com.dukeacademy.testexecutor.exceptions.EmptyUserProgramException;
import com.dukeacademy.testexecutor.exceptions.IncorrectCanonicalNameException;

/**
 * Submit command that submits the user's current work from the registered UserProgram channel of the
 * ProgramSubmissionLogic.
 */
public class SubmitCommand implements Command {
    private QuestionsLogic questionsLogic;
    private ProgramSubmissionLogic programSubmissionLogic;

    public SubmitCommand(QuestionsLogic questionsLogic, ProgramSubmissionLogic programSubmissionLogic) {
        this.questionsLogic = questionsLogic;
        this.programSubmissionLogic = programSubmissionLogic;
    }

    @Override
    public CommandResult execute() throws CommandException {
        Optional<Question> currentlyAttemptingQuestion = this.programSubmissionLogic.getCurrentQuestion();
        UserProgram userProgram = programSubmissionLogic.getUserProgramFromSubmissionChannel();

        if (currentlyAttemptingQuestion.isEmpty()) {
            throw new CommandException("You have not attempted a question yet.");
        }

        // Save the user's program first
        Question question = currentlyAttemptingQuestion.get();
        Question questionWithNewProgram = question.withNewUserProgram(userProgram);
        this.questionsLogic.replaceQuestion(question, questionWithNewProgram);

        // Submit the user's program
        Optional<TestResult> resultsOptional;
        try {
            resultsOptional = this.programSubmissionLogic.submitUserProgram(userProgram);
        } catch (IncorrectCanonicalNameException e) {
            return new CommandResult("Main class needed as entry point.", false, false);
        } catch (EmptyUserProgramException e) {
            return new CommandResult("Program must not be empty.", false, false);
        }

        if (resultsOptional.isEmpty()) {
            throw new CommandException("Tests failed unexpectedly. Please report this bug at "
                    + "https://github.com/AY1920S1-CS2103T-F14-1/main");
        }

        // Update question with result status
        boolean isSuccessful = resultsOptional.get().getNumPassed() == questionWithNewProgram.getTestCases().size();

        if (isSuccessful) {
            Question successfulQuestion = questionWithNewProgram.withNewStatus(Status.PASSED);
            this.questionsLogic.replaceQuestion(questionWithNewProgram, successfulQuestion);
        } else {
            Question failedQuestion = questionWithNewProgram.withNewStatus(Status.ATTEMPTED);
            this.questionsLogic.replaceQuestion(questionWithNewProgram, failedQuestion);
        }

        // Give user feedback
        String feedback = "Submitted your program for question : " + question.getTitle() + "\nResult : ";
        if (isSuccessful) {
            feedback = feedback + "success";
        } else {
            feedback = feedback + "failed";
        }
        return new CommandResult(feedback, false, false);
    }
}
