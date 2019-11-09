package com.dukeacademy.logic.commands.notes;

import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.commands.CommandResult;
import com.dukeacademy.logic.commands.exceptions.CommandException;
import com.dukeacademy.logic.notes.NotesLogic;
import com.dukeacademy.model.notes.Note;
import com.dukeacademy.model.state.Activity;
import com.dukeacademy.model.state.ApplicationState;

import java.util.Optional;
import java.util.logging.Logger;

public class SaveNoteCommand implements Command {
    private final NotesLogic notesLogic;
    private final ApplicationState applicationState;
    private final Logger logger;

    public SaveNoteCommand(NotesLogic notesLogic, ApplicationState applicationState) {
        this.notesLogic = notesLogic;
        this.applicationState = applicationState;
        this.logger = LogsCenter.getLogger(SaveNoteCommand.class);
    }

    @Override
    public CommandResult execute() throws CommandException {
        logger.info("Attempting to save current working note...");

        Optional<Note> savedNote = notesLogic.saveNoteFromNoteSubmissionChannel();

        if (savedNote.isEmpty()) {
            logger.warning("No note currently opened. Skipping save...");
            throw new CommandException("No note is currently opened for saving");
        }

        logger.info("Successfully saved note : " + savedNote.get());

        this.applicationState.setCurrentActivity(Activity.NOTE);
        return new CommandResult("Your note has been successfully saved : " + savedNote.get().getTitle());
    }
}
