package com.dukeacademy.logic.commands;

import com.dukeacademy.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    /**
     * The constant COMMAND_WORD.
     */
    public static final String COMMAND_WORD = "help";

    /**
     * The constant MESSAGE_USAGE.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * The constant SHOWING_HELP_MESSAGE.
     */
    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false, false,
            false);
    }
}
