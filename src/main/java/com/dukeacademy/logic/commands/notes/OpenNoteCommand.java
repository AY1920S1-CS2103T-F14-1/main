package com.dukeacademy.logic.commands.notes;

import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.logic.commands.Command;
import com.dukeacademy.logic.commands.CommandResult;
import com.dukeacademy.logic.commands.exceptions.CommandException;
import com.dukeacademy.logic.notes.NotesLogic;
import com.dukeacademy.logic.notes.exceptions.NoteNotFoundRuntimeException;
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
        try {
            logger.info("Attempting to save previously opened note...");
            this.notesLogic.saveNoteFromNoteSubmissionChannel();
            this.notesLogic.selectNote(index);
        } catch (NoteNotFoundRuntimeException e) {
            throw new CommandException("No note at index " + index + " found");
        }

        applicationState.setCurrentActivity(Activity.NOTE);
        return new CommandResult("Successfully opened note " + index, false);
    }
}
