package com.dukeacademy.logic.commands.notes;

import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.commands.CommandFactory;
import com.dukeacademy.logic.commands.exceptions.InvalidCommandArgumentsException;
import com.dukeacademy.logic.notes.NotesLogic;

public class OpenNoteCommandFactory implements CommandFactory {
    private final NotesLogic notesLogic;

    public OpenNoteCommandFactory(NotesLogic notesLogic) {
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
            return new OpenNoteCommand(notesLogic, index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentsException("Index should be a valid number.");
        }
    }
}
