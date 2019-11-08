package com.dukeacademy.storage.notes;

import com.dukeacademy.model.notes.Note;
import com.dukeacademy.model.notes.NoteBank;
import com.dukeacademy.model.notes.StandardNoteBank;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.List;

@JsonRootName(value = "noteBank")
public class JsonSerializableNoteBank {
    private final List<Note> notesList = new ArrayList<>();

    @JsonCreator
    public JsonSerializableNoteBank(@JsonProperty("notes") List<Note> notes) {
        this.notesList.addAll(notes);
    }

    public JsonSerializableNoteBank(NoteBank source) {
        notesList.addAll(source.getReadOnlyNotesObservableList());
    }

    public NoteBank toModelType() {
        return new StandardNoteBank(notesList);
    }
}
