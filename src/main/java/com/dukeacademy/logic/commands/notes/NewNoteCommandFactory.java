package com.dukeacademy.logic.commands.notes;

import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.commands.CommandFactory;
import com.dukeacademy.logic.commands.exceptions.InvalidCommandArgumentsException;
import com.dukeacademy.logic.notes.NotesLogic;

public class NewNoteCommandFactory implements CommandFactory {
    private final NotesLogic notesLogic;
    private static final String TITLE_VALIDATION_REGEX = "[^\\s].*";

    public NewNoteCommandFactory(NotesLogic notesLogic) {
        this.notesLogic = notesLogic;
    }

    @Override
    public String getCommandWord() {
        return "newnote";
    }

    @Override
    public Command getCommand(String commandArguments) throws InvalidCommandArgumentsException {
        if (!commandArguments.matches(TITLE_VALIDATION_REGEX)) {
            throw new InvalidCommandArgumentsException("Note title should not be empty!");
        }

        return new NewNoteCommand(notesLogic, commandArguments);
    }
}
