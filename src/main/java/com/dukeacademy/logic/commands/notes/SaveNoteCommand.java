package com.dukeacademy.logic.commands.notes;

import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.commands.CommandResult;
import com.dukeacademy.logic.commands.exceptions.CommandException;
import com.dukeacademy.logic.notes.NotesLogic;
import com.dukeacademy.model.notes.Note;
import com.dukeacademy.model.state.Activity;
import com.dukeacademy.model.state.ApplicationState;

import java.util.Optional;

public class SaveNoteCommand implements Command {
    private final NotesLogic notesLogic;
    private final ApplicationState applicationState;

    public SaveNoteCommand(NotesLogic notesLogic, ApplicationState applicationState) {
        this.notesLogic = notesLogic;
        this.applicationState = applicationState;
    }

    @Override
    public CommandResult execute() throws CommandException {
        Optional<Note> savedNote = notesLogic.saveNoteFromNoteSubmissionChannel();

        if (savedNote.isEmpty()) {
            throw new CommandException("No note is currently opened for saving");
        }

        this.applicationState.setCurrentActivity(Activity.NOTE);
        return new CommandResult("Your note has been successfully saved : " + savedNote.get().getTitle());
    }
}
