package com.dukeacademy.logic.commands.notes;

import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.commands.CommandFactory;
import com.dukeacademy.logic.commands.exceptions.InvalidCommandArgumentsException;
import com.dukeacademy.logic.notes.NotesLogic;
import com.dukeacademy.model.state.ApplicationState;

public class OpenNoteCommandFactory implements CommandFactory {
    private final ApplicationState applicationState;
    private final NotesLogic notesLogic;

    public OpenNoteCommandFactory(ApplicationState applicationState, NotesLogic notesLogic) {
        this.applicationState = applicationState;
        this.notesLogic = notesLogic;
    }


    @Override
    public String getCommandWord() {
        return "opennote";
    }

    @Override
    public Command getCommand(String commandArguments) throws InvalidCommandArgumentsException {
        try {
            int index = Integer.parseInt(commandArguments.strip());
            return new OpenNoteCommand(applicationState, notesLogic, index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentsException("Index should be a valid number.");
        }
    }
}
