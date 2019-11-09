package com.dukeacademy.logic.commands.notes;

import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.commands.CommandFactory;
import com.dukeacademy.logic.commands.exceptions.InvalidCommandArgumentsException;
import com.dukeacademy.logic.notes.NotesLogic;
import com.dukeacademy.model.state.ApplicationState;

public class SaveNoteCommandFactory implements CommandFactory {
    private final ApplicationState applicationState;
    private final NotesLogic notesLogic;

    public SaveNoteCommandFactory(ApplicationState applicationState, NotesLogic notesLogic) {
        this.applicationState = applicationState;
        this.notesLogic = notesLogic;
    }

    @Override
    public String getCommandWord() {
        return "savenote";
    }

    @Override
    public Command getCommand(String commandArguments) throws InvalidCommandArgumentsException {
        if (!commandArguments.matches("\\s*")) {
            throw new InvalidCommandArgumentsException("Save note command does not take any arguments");
        }

        return new SaveNoteCommand(notesLogic, applicationState);
    }
}
