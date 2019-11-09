package com.dukeacademy.logic.commands.notes;

import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.commands.CommandFactory;
import com.dukeacademy.logic.commands.exceptions.InvalidCommandArgumentsException;
import com.dukeacademy.logic.notes.NotesLogic;
import com.dukeacademy.model.state.ApplicationState;

public class NewNoteCommandFactory implements CommandFactory {
    private final NotesLogic notesLogic;
    private final ApplicationState applicationState;
    private static final String TITLE_VALIDATION_REGEX = "[^\\s].*";

    public NewNoteCommandFactory(ApplicationState applicationState, NotesLogic notesLogic) {
        this.applicationState = applicationState;
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

        return new NewNoteCommand(applicationState, notesLogic, commandArguments);
    }
}
