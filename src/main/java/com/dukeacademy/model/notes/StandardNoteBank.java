package com.dukeacademy.model.notes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;

public class StandardNoteBank implements NoteBank {
    private final ObservableList<Note> notesList = FXCollections.observableArrayList();
    private final ObservableList<Note> unmodifiableNotesList = FXCollections.unmodifiableObservableList(notesList);

    public StandardNoteBank() {}

    public StandardNoteBank(Collection<Note> notes) {
        this();
        this.notesList.addAll(notes);
    }

    @Override
    public ObservableList<Note> getReadOnlyNotesObservableList() {
        return this.unmodifiableNotesList;
    }

    @Override
    public void addNote(Note note) {
        this.notesList.add(note);
    }

    @Override
    public void replaceNote(Note oldNote, Note newNote) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteNote(Note oldNote) {
        throw new UnsupportedOperationException();
    }
}
