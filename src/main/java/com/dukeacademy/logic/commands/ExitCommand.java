package com.dukeacademy.logic.commands;

import com.dukeacademy.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    /**
     * The constant COMMAND_WORD.
     */
    public static final String COMMAND_WORD = "exit";

    /**
     * The constant MESSAGE_EXIT_ACKNOWLEDGEMENT.
     */
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Difficulty Book as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false);
    }

}
