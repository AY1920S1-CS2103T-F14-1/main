package com.dukeacademy.logic.notes;

import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.model.notes.Note;
import com.dukeacademy.observable.Observable;
import javafx.collections.ObservableList;

import java.util.function.Predicate;
import java.util.logging.Logger;

public interface NotesLogic {
    ObservableList<Note> getAllNotesList();

    ObservableList<Note> getFilteredNotesList();

    void filterNotesList(Predicate<Note> predicate);

    void addNote(Note note);

    void replaceNote(Note oldNote, Note newNote);

    Observable<Note> getSelectedNote();

    void selectNote(int id);
}
