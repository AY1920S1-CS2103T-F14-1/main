package com.dukeacademy.logic.commands.factory;

import com.dukeacademy.logic.commands.Command;

/**
 * Encapsulates the creation of commands and its dependencies and exposes an interface that can be injected into
 * the command logic.
 */
public interface CommandFactory {
    /**
     * Gives the command word that will be used to match to this factory.
     * @return the command word.
     */
    String getCommandWord();

    /**
     * Returns the corresponding command class instance.
     * @param commandArguments the command text from the user's input.
     * @return the corresponding command class instance.
     */
    Command getCommand(String commandArguments);
}
