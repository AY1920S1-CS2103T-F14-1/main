package com.dukeacademy.logic.commands;

import static java.util.Objects.requireNonNull;

import com.dukeacademy.model.Model;
import com.dukeacademy.model.QuestionBank;

/**
 * Clears the question bank.
 */
public class ClearCommand extends Command {

    /**
     * The constant COMMAND_WORD.
     */
    public static final String COMMAND_WORD = "clear";
    /**
     * The constant MESSAGE_SUCCESS.
     */
    public static final String MESSAGE_SUCCESS = "Difficulty book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setQuestionBank(new QuestionBank());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
