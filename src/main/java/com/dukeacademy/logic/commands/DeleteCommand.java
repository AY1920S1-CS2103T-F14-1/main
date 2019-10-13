package com.dukeacademy.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import com.dukeacademy.commons.core.Messages;
import com.dukeacademy.commons.core.index.Index;
import com.dukeacademy.logic.commands.exceptions.CommandException;
import com.dukeacademy.model.Model;
import com.dukeacademy.model.question.Question;

/**
 * Deletes a question identified using it's displayed index from the question bank.
 */
public class DeleteCommand extends Command {

    /**
     * The constant COMMAND_WORD.
     */
    public static final String COMMAND_WORD = "delete";

    /**
     * The constant MESSAGE_USAGE.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the question identified by the index number used in the displayed question list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    /**
     * The constant MESSAGE_DELETE_QUESTION_SUCCESS.
     */
    public static final String MESSAGE_DELETE_QUESTION_SUCCESS = "Deleted Question: %1$s";

    private final Index targetIndex;

    /**
     * Instantiates a new Delete command.
     *
     * @param targetIndex the target index
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Question> lastShownList = model.getFilteredQuestionList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
        }

        Question questionToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteQuestion(questionToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_QUESTION_SUCCESS,
            questionToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
