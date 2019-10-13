package com.dukeacademy.logic.parser;

import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 *
 * @param <T> the type parameter
 */
public interface Parser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput the user input
     * @return the t
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParseException;
}
