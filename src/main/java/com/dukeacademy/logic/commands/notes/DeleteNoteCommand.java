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

public class DeleteNoteCommand implements Command {
    private final ApplicationState applicationState;
    private final NotesLogic notesLogic;
    private final int index;
    private final Logger logger;

    public DeleteNoteCommand(ApplicationState applicationState, NotesLogic notesLogic, int index) {
        this.applicationState = applicationState;
        this.notesLogic = notesLogic;
        this.index = index - 1;
        this.logger = LogsCenter.getLogger(DeleteNoteCommand.class);
    }

    @Override
    public CommandResult execute() throws CommandException {
        logger.info("Attempting to delete note at index : " + index);

        Note deletedNote;

        try {
            deletedNote = notesLogic.deleteNote(index);
        } catch (NoteNotFoundRuntimeException e) {
            logger.warning("No note found at index : " + index);
            throw new CommandException("No note at index " + index + " found");
        }

        logger.info("Successfully deleted note : " + deletedNote);

        applicationState.setCurrentActivity(Activity.NOTE);
        return new CommandResult("Successfully deleted your note : " + deletedNote.getTitle());
    }
}
