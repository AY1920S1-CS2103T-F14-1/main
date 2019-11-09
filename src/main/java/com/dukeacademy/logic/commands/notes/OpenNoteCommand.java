package com.dukeacademy.logic.commands.notes;

import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.commands.CommandResult;
import com.dukeacademy.logic.commands.exceptions.CommandException;
import com.dukeacademy.logic.notes.NotesLogic;
import com.dukeacademy.logic.notes.exceptions.NoteNotFoundRuntimeException;

import java.util.logging.Logger;

public class OpenNoteCommand implements Command {
    private final NotesLogic notesLogic;
    private final int noteId;
    private final Logger logger;

    public OpenNoteCommand(NotesLogic notesLogic, int noteId) {
        this.notesLogic = notesLogic;
        this.noteId = noteId;
        this.logger = LogsCenter.getLogger(OpenNoteCommand.class);
    }

    @Override
    public CommandResult execute() throws CommandException {
        try {
            logger.info("Attempting to save previously opened note...");
            this.notesLogic.saveNoteFromNoteSubmissionChannel();
            this.notesLogic.selectNote(noteId);
        } catch (NoteNotFoundRuntimeException e) {
            throw new CommandException("No note with id " + noteId + "found");
        }

        return new CommandResult("Successfully opened note " + noteId, false);
    }
}
