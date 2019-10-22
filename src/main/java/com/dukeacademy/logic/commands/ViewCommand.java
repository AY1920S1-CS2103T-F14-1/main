package com.dukeacademy.logic.commands;

import com.dukeacademy.commons.core.Messages;
import com.dukeacademy.commons.core.index.Index;
import com.dukeacademy.logic.commands.exceptions.CommandException;
import com.dukeacademy.model.Model;
import com.dukeacademy.model.question.Question;

import static java.util.Objects.requireNonNull;

import java.util.List;

/**
 * Views a question identified using it's displayed index from the question
 * bank.
 */
public class ViewCommand extends Command {

    /**
     * The constant COMMAND_WORD.
     */
    public static final String COMMAND_WORD = "view";

    /**
     * The constant MESSAGE_USAGE.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Views the question identified by the index number used in the "
        + "displayed question list.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    /**
     * The constant MESSAGE_DELETE_QUESTION_SUCCESS.
     */
    public static final String MESSAGE_VIEW_QUESTION_SUCCESS = "Viewing "
        + "Question: %1$s";

    private final Index targetIndex;

    /**
     * Instantiates a new View command.
     *
     * @param targetIndex the target index
     */
    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Question> lastShownList = model.getFilteredQuestionList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
        }

        Question questionToView = lastShownList.get(targetIndex.getZeroBased());
        System.out.println("!!!View Command passing the question to model: " + questionToView.getTitle());
        model.setQuestionToView(questionToView);
        System.out.println("!!!View Command passed the question to model: " + questionToView.getTitle());
        return new CommandResult(String.format(MESSAGE_VIEW_QUESTION_SUCCESS,
            questionToView), false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ViewCommand // instanceof handles nulls
            && targetIndex.equals(((ViewCommand) other).targetIndex)); // state
        // check
    }
}
