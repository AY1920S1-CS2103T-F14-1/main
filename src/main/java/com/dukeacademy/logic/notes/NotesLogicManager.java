package com.dukeacademy.logic.notes;

import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.commons.exceptions.DataConversionException;
import com.dukeacademy.model.notes.Note;
import com.dukeacademy.model.notes.NoteBank;
import com.dukeacademy.model.notes.StandardNoteBank;
import com.dukeacademy.observable.Observable;
import com.dukeacademy.observable.StandardObservable;
import com.dukeacademy.storage.notes.NoteStorage;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.io.IOException;
import java.util.function.Predicate;
import java.util.logging.Logger;

public class NotesLogicManager implements NotesLogic {
    private final Logger logger;
    private final NoteStorage storage;
    private final NoteBank noteBank;
    private final FilteredList<Note> filteredList;
    private final Observable<Note> selectedNote;

    public NotesLogicManager(NoteStorage storage) {
        this.logger = LogsCenter.getLogger(NotesLogicManager.class);
        this.storage = storage;
        this.noteBank = this.loadQuestionsFromStorage();
        this.filteredList = new FilteredList<>(this.noteBank.getReadOnlyNotesObservableList());
        this.selectedNote = new StandardObservable<>();
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
    }

    @Override
    public void replaceNote(Note oldNote, Note newNote) {
        this.noteBank.replaceNote(oldNote, newNote);
    }

    @Override
    public Observable<Note> getSelectedNote() {
        return this.selectedNote;
    }

    @Override
    public void selectNote(int id) {
        throw new UnsupportedOperationException();
    }


    private NoteBank loadQuestionsFromStorage() {
        logger.info("Storage instance built, trying to load notes...");
        try {
            return this.storage.readNoteBank().get();
        } catch (IOException | DataConversionException | NullPointerException e) {
            logger.info("Unable to load note bank from : " + storage.getNoteBankFilePath()
                    + ".\n Creating new note bank...");
            return new StandardNoteBank();
        }
    }
}
