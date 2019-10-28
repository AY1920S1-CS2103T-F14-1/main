package com.dukeacademy.logic.commands.bookmark;

import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.commands.CommandFactory;
import com.dukeacademy.logic.commands.exceptions.InvalidCommandArgumentsException;
import com.dukeacademy.logic.commands.view.ViewCommand;
import com.dukeacademy.logic.problemstatement.ProblemStatementLogic;
import com.dukeacademy.logic.question.QuestionsLogic;

/**
 * Factory class to represent all the necessary components for creating an
 * ViewCommand instance.
 */
public class BookmarkCommandFactory implements CommandFactory {
    private final QuestionsLogic questionsLogic;
    private ProblemStatementLogic problemStatementLogic;

    /**
     * Instantiates a new Bookmark command factory.
     *
     * @param questionsLogic        the questions logic
     * @param problemStatementLogic the problem statement logic
     */
    public BookmarkCommandFactory(QuestionsLogic questionsLogic,
                              ProblemStatementLogic problemStatementLogic) {
        this.questionsLogic = questionsLogic;
        this.problemStatementLogic = problemStatementLogic;
    }

    @Override
    public String getCommandWord() {
        return "bookmark";
    }

    @Override
    public Command getCommand(String commandArguments) throws InvalidCommandArgumentsException {
        try {
            int index = Integer.parseInt(commandArguments.strip());
            return new ViewCommand(index, questionsLogic, problemStatementLogic);
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentsException("Invalid index entered.");
        }
    }
}
