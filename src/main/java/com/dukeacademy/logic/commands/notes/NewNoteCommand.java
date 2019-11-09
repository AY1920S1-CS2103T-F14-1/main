package com.dukeacademy.logic.commands.notes;

import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.commands.CommandResult;
import com.dukeacademy.logic.commands.exceptions.CommandException;
import com.dukeacademy.logic.notes.NotesLogic;
import com.dukeacademy.model.notes.Note;
import com.dukeacademy.model.state.Activity;
import com.dukeacademy.model.state.ApplicationState;

import java.util.logging.Logger;

public class NewNoteCommand implements Command {
    private final NotesLogic notesLogic;
    private final ApplicationState applicationState;
    private final String noteTitle;
    private final Logger logger;

    public NewNoteCommand(ApplicationState applicationState, NotesLogic notesLogic, String noteTitle) {
        this.logger = LogsCenter.getLogger(NewNoteCommand.class);
        this.applicationState = applicationState;
        this.notesLogic = notesLogic;
        this.noteTitle = noteTitle;
    }


    @Override
    public CommandResult execute() throws CommandException {
        Note newNote = new Note(noteTitle, "");

        logger.info("Attempting to save previously opened note...");
        notesLogic.saveNoteFromNoteSubmissionChannel();

        logger.info("Creating new note : " + noteTitle);

        notesLogic.addNote(newNote);
        notesLogic.selectNote(newNote);

        logger.info("Opening new note : " + newNote);

        applicationState.setCurrentActivity(Activity.NOTE);

        return new CommandResult("New note successfully created : " + noteTitle, false);
    }
}
