package com.dukeacademy.logic.commands.notes;

import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.commands.CommandFactory;
import com.dukeacademy.logic.commands.attempt.AttemptCommand;
import com.dukeacademy.logic.commands.exceptions.InvalidCommandArgumentsException;
import com.dukeacademy.logic.notes.NotesLogic;
import com.dukeacademy.logic.question.QuestionsLogic;
import com.dukeacademy.model.state.ApplicationState;

public class DeleteNoteCommandFactory implements CommandFactory {
    private final NotesLogic notesLogic;
    private final ApplicationState applicationState;

    public DeleteNoteCommandFactory(ApplicationState applicationState, NotesLogic notesLogic) {
        this.applicationState = applicationState;
        this.notesLogic = notesLogic;
    }

    @Override
    public String getCommandWord() {
        return "deletenote";
    }

    @Override
    public Command getCommand(String commandArguments) throws InvalidCommandArgumentsException {
        try {
            int index = Integer.parseInt(commandArguments.strip());
            return new DeleteNoteCommand(applicationState, notesLogic, index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentsException("Index should be a valid number.");
        }
    }
}
