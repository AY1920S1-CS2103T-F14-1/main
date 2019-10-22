package com.dukeacademy.logic.parser;

import com.dukeacademy.commons.core.index.Index;
import com.dukeacademy.logic.commands.ViewCommand;
import com.dukeacademy.logic.parser.exceptions.ParseException;

import static com.dukeacademy.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand>{
    /**
     * Parses the given {@code String} of arguments in the context of the
     * ViewCommand
     * and returns an ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewCommand.MESSAGE_USAGE), pe);
        }
    }
}








