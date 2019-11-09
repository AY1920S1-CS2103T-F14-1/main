package com.dukeacademy.logic.notes;

import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.commons.exceptions.DataConversionException;
import com.dukeacademy.logic.program.exceptions.SubmissionChannelNotSetException;
import com.dukeacademy.model.notes.Note;
import com.dukeacademy.model.notes.NoteBank;
import com.dukeacademy.model.notes.StandardNoteBank;
import com.dukeacademy.observable.Observable;
import com.dukeacademy.observable.StandardObservable;
import com.dukeacademy.storage.notes.NoteBankStorage;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

public class NotesLogicManager implements NotesLogic {
    private final Logger logger;
    private final NoteBankStorage storage;
    private final NoteBank noteBank;
    private final FilteredList<Note> filteredList;
    private final Observable<Note> selectedNote;
    private NoteSubmissionChannel noteSubmissionChannel;

    public NotesLogicManager(NoteBankStorage storage) {
        this.logger = LogsCenter.getLogger(NotesLogicManager.class);
        this.storage = storage;
        this.noteBank = this.loadNotesFromStorage();
        this.filteredList = new FilteredList<>(this.noteBank.getReadOnlyNotesObservableList());
        this.selectedNote = new StandardObservable<>();
    }

    @Override
    public SketchManager getSketchManager() {
        Path sketchStoragePath = storage.getNoteBankFilePath().getParent().resolve("sketches");
        return new SketchManager(sketchStoragePath);
    }

    @Override
    public void setNoteSubmissionChannel(NoteSubmissionChannel noteSubmissionChannel) {
        this.noteSubmissionChannel = noteSubmissionChannel;
        logger.info("Successfully added note submission channel : " + noteSubmissionChannel);
    }

    @Override
    public ObservableList<Note> getAllNotesList() {
        return noteBank.getReadOnlyNotesObservableList();
    }

    @Override
    public ObservableList<Note> getFilteredNotesList() {
        return this.filteredList;
    }

    @Override
    public void filterNotesList(Predicate<Note> predicate) {
        this.filteredList.setPredicate(predicate);
    }

    @Override
    public void addNote(Note note) {
        this.noteBank.addNote(note);
        this.saveNotesToStorage(this.noteBank);
    }

    @Override
    public void addNoteFromNoteSubmissionChannel() {
        if (this.noteSubmissionChannel == null) {
            throw new SubmissionChannelNotSetException();
        }

        this.addNote(this.noteSubmissionChannel.getNote());
    }

    @Override
    public void replaceNote(Note oldNote, Note newNote) {
        this.noteBank.replaceNote(oldNote, newNote);
        this.saveNotesToStorage(this.noteBank);
    }

    @Override
    public Observable<Note> getSelectedNote() {
        return this.selectedNote;
    }

    @Override
    public void selectNote(int id) {
        throw new UnsupportedOperationException();
    }


    private NoteBank loadNotesFromStorage() {
        logger.info("Storage instance built, trying to load notes...");
        try {
            return this.storage.readNoteBank().get();
        } catch (IOException | DataConversionException | NullPointerException e) {
            logger.info("Unable to load note bank from : " + storage.getNoteBankFilePath()
                    + ".\n Creating new note bank...");
            return new StandardNoteBank();
        }
    }

    private void saveNotesToStorage(NoteBank noteBank) {
        try {
            storage.saveNoteBank(noteBank);
        } catch (IOException e) {
            logger.warning("Unable to save note bank to :" + storage.getNoteBankFilePath());
        }
    }
}
