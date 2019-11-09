package com.dukeacademy.logic.commands.notes;

import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.commands.CommandResult;
import com.dukeacademy.logic.commands.exceptions.CommandException;
import com.dukeacademy.logic.notes.NotesLogic;
import com.dukeacademy.logic.notes.exceptions.NoteNotFoundRuntimeException;
import com.dukeacademy.model.notes.Note;
import com.dukeacademy.model.state.Activity;
import com.dukeacademy.model.state.ApplicationState;

import java.util.logging.Logger;

public class OpenNoteCommand implements Command {
    private final ApplicationState applicationState;
    private final NotesLogic notesLogic;
    private final int index;
    private final Logger logger;

    public OpenNoteCommand(ApplicationState applicationState, NotesLogic notesLogic, int index) {
        this.applicationState = applicationState;
        this.notesLogic = notesLogic;
        this.index = index - 1;
        this.logger = LogsCenter.getLogger(OpenNoteCommand.class);
    }

    @Override
    public CommandResult execute() throws CommandException {
        logger.info("Attempting to save previously opened note...");
        this.notesLogic.saveNoteFromNoteSubmissionChannel();

        Note selectedNote;
        try {
            logger.info("Attempting to open note at index : " + index);
            selectedNote = this.notesLogic.selectNote(index);
        } catch (NoteNotFoundRuntimeException e) {
            logger.warning("No note found at index : " + index);
            throw new CommandException("No note found at index : " + (index + 1));
        }

        applicationState.setCurrentActivity(Activity.NOTE);
        logger.info("Successfully opened note at index : " + selectedNote);
        return new CommandResult("Successfully opened note : " + selectedNote.getTitle(), false);
    }
}
