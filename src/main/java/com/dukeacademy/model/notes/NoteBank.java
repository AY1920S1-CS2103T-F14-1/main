package com.dukeacademy.model.notes;

import javafx.collections.ObservableList;

public interface NoteBank {
    ObservableList<Note> getReadOnlyNotesObservableList();

    void addNote(Note note);

    void replaceNote(Note oldNote, Note newNote);

    void deleteNote(Note oldNote);
}
