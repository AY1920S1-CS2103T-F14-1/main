package com.dukeacademy.storage.notes;

import com.dukeacademy.commons.exceptions.IllegalValueException;
import com.dukeacademy.model.notes.Note;
import com.dukeacademy.model.notes.NoteBank;
import com.dukeacademy.model.notes.StandardNoteBank;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonRootName(value = "noteBank")
public class JsonSerializableNoteBank {
    private final List<JsonAdaptedNote> notesList = new ArrayList<>();

    @JsonCreator
    public JsonSerializableNoteBank(@JsonProperty("notes") List<JsonAdaptedNote> notes) {
        this.notesList.addAll(notes);
    }

    public JsonSerializableNoteBank(NoteBank source) {
        notesList.addAll(source.getReadOnlyNotesObservableList().stream()
                .map(JsonAdaptedNote::new).collect(Collectors.toList()));
    }

    public NoteBank toModelType() throws IllegalValueException {
        StandardNoteBank noteBank = new StandardNoteBank();
        for (JsonAdaptedNote note : notesList) {
            noteBank.addNote(note.toModel());
        }

        return noteBank;
    }
}
