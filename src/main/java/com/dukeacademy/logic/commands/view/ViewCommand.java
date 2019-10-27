package com.dukeacademy.logic.commands.view;

import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.commands.CommandResult;
import com.dukeacademy.logic.commands.exceptions.CommandException;
import com.dukeacademy.logic.program.ProgramSubmissionLogic;
import com.dukeacademy.logic.question.QuestionsLogic;
import com.dukeacademy.model.question.Question;
import com.dukeacademy.model.question.entities.Status;

/**
 * Command for viewing a question.
 */
public class ViewCommand implements Command {
    private QuestionsLogic questionsLogic;
    private int index;

    public ViewCommand(int index, QuestionsLogic questionsLogic) {
        this.index = index - 1;
        this.questionsLogic = questionsLogic;
    }

    @Override
    public CommandResult execute() throws CommandException {
        try {
            // Update status of question
            Question questionToView =
                this.questionsLogic.getQuestion(index);
            this.questionsLogic.setProblemStatement(questionToView.getDescription());

            String feedback =
                "Viewing question " + (index + 1) + " : " + questionToView.getTitle();
            return new CommandResult(feedback, false, false, false, true);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Index entered out of range for current list of questions.");
        }
    }
}

