package com.dukeacademy.logic.commands;

import static java.util.Objects.requireNonNull;

import com.dukeacademy.commons.core.Messages;
import com.dukeacademy.model.Model;

/**
 * Finds and lists all persons in question bank whose title contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {
//
    public static final String COMMAND_WORD = "find";
//
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";
//
//    private final TitleContainsKeywordsPredicate predicate;
//
//    public FindCommand(TitleContainsKeywordsPredicate predicate) {
//        this.predicate = predicate;
//    }

    @Override
    public CommandResult execute(Model model) {
//        requireNonNull(model);
//        model.updateFilteredQuestionList(predicate);
//        return new CommandResult(
//                String.format(Messages.MESSAGE_QUESTIONS_LISTED_OVERVIEW,
//                    model.getFilteredQuestionList().size()));
        return new CommandResult("");
    }

}
