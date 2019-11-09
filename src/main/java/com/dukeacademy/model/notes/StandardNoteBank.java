package com.dukeacademy.model.notes;

import com.dukeacademy.logic.notes.exceptions.NoteNotFoundRuntimeException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;
import java.util.stream.IntStream;

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
        int oldNoteIndex = IntStream.range(0, notesList.size())
                .filter(i -> notesList.get(i).equals(oldNote))
                .findFirst()
                .orElseThrow(NoteNotFoundRuntimeException::new);

        notesList.remove(oldNoteIndex);
        notesList.add(oldNoteIndex, newNote);
    }

    @Override
    public void deleteNote(Note oldNote) {
        int oldNoteIndex = IntStream.range(0, notesList.size())
                .filter(i -> notesList.get(i).equals(oldNote))
                .findFirst()
                .orElseThrow(NoteNotFoundRuntimeException::new);

        notesList.remove(oldNoteIndex);
    }
}
