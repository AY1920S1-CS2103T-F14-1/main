package com.dukeacademy.logic.notes;

import com.dukeacademy.data.Pair;
import com.dukeacademy.model.notes.Note;
import com.dukeacademy.observable.Observable;
import javafx.collections.ObservableList;
import javafx.scene.image.WritableImage;

import java.util.function.Predicate;

public interface NotesLogic {
    void setNoteSubmissionChannel(NoteSubmissionChannel noteSubmissionChannel);

    SketchManager getSketchManager();

    ObservableList<Note> getAllNotesList();

    ObservableList<Note> getFilteredNotesList();

    void filterNotesList(Predicate<Note> predicate);

    void addNoteWithSketch(Note note, WritableImage sketch);

    void addNote(Note note);

    void saveNoteFromNoteSubmissionChannel();

    void replaceNote(Note oldNote, Note newNote, WritableImage newSketch);

    Observable<Pair<Note, WritableImage>> getSelectedNote();

    void selectNote(int index);

    void selectNote(Note note);

    void deleteNote(int index);
}
