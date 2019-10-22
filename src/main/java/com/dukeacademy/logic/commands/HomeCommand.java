package com.dukeacademy.logic.commands;

import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.logic.commands.exceptions.CommandException;
import com.dukeacademy.model.Model;

import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

/**
 * Returns to homepage and displays user profile.
 */
public class HomeCommand extends Command{
    /**
     * The Counter.
     */
    static int counter = 0;

    /**
     * The constant COMMAND_WORD.
     */
    public static final String COMMAND_WORD = "home";

    /**
     * The constant MESSAGE_USAGE.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Returns to the homepage.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * The constant HOMEPAGE_MESSAGE.
     */
    public static final String HOMEPAGE_MESSAGE = "Welcome back!";

    private final Logger logger = LogsCenter.getLogger(getClass());
    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        model.updateProfile("This is the updated profile " + counter);
        counter +=1;
        return new CommandResult(HOMEPAGE_MESSAGE, false, false, true,false);
    }
}
