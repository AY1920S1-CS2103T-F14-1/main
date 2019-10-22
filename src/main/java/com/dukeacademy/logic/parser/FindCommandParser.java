package com.dukeacademy.logic.parser;

import static com.dukeacademy.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import com.dukeacademy.logic.commands.FindCommand;
import com.dukeacademy.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        // return new FindCommand(new TitleContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        return new FindCommand();
    }

}