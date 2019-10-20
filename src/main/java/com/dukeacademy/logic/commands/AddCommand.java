package com.dukeacademy.logic.commands;

import static java.util.Objects.requireNonNull;

import com.dukeacademy.logic.commands.exceptions.CommandException;
import com.dukeacademy.logic.parser.CliSyntax;
import com.dukeacademy.model.Model;
import com.dukeacademy.model.question.Question;

/**
 * Adds a question to the question bank.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a question to the question bank. "
            + "Parameters: "
            + CliSyntax.PREFIX_TITLE + "NAME "
            + CliSyntax.PREFIX_TOPIC + "PHONE "
            + CliSyntax.PREFIX_STATUS + "EMAIL "
            + CliSyntax.PREFIX_DIFFICULTY + "ADDRESS "
            + "[" + CliSyntax.PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_TITLE + "John Doe "
            + CliSyntax.PREFIX_TOPIC + "98765432 "
            + CliSyntax.PREFIX_STATUS + "johnd@example.com "
            + CliSyntax.PREFIX_DIFFICULTY + "311, Clementi Ave 2, #02-25 "
            + CliSyntax.PREFIX_TAG + "friends "
            + CliSyntax.PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New question added: %1$s";
    public static final String MESSAGE_DUPLICATE_QUESTION = "This question already exists in the question bank";

    private final Question toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Question}
     */
    public AddCommand(Question question) {
        requireNonNull(question);
        toAdd = question;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.addQuestion(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
