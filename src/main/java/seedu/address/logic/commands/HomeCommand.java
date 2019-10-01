package seedu.address.logic.commands;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

/**
 * Returns to homepage and displays user profile.
 */
public class HomeCommand extends Command{
    static int counter = 0;

    public static final String COMMAND_WORD = "home";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Returns to the homepage.\n"
            + "Example: " + COMMAND_WORD;

    public static final String HOMEPAGE_MESSAGE = "Welcome back!";

    private final Logger logger = LogsCenter.getLogger(getClass());
    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        model.updateProfile("This is the updated profile " + counter);
        counter +=1;
        return new CommandResult(HOMEPAGE_MESSAGE, false, false, true);
    }
}
