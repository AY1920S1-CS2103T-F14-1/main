package com.dukeacademy.logic.commands;

import static java.util.Objects.requireNonNull;

import com.dukeacademy.model.Model;

/**
 * Lists all persons in the question bank to the user.
 */
public class ListCommand extends Command {

    /**
     * The constant COMMAND_WORD.
     */
    public static final String COMMAND_WORD = "list";

    /**
     * The constant MESSAGE_SUCCESS.
     */
    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredQuestionList(Model.PREDICATE_SHOW_ALL_QUESTIONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
