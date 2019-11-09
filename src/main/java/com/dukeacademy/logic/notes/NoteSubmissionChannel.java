package com.dukeacademy.logic.notes;

import com.dukeacademy.data.Pair;
import com.dukeacademy.logic.notes.exceptions.NoNoteSetException;
import com.dukeacademy.model.notes.Note;
import javafx.scene.image.WritableImage;

public interface NoteSubmissionChannel {
    Pair<Note, WritableImage> getNoteAndSketchPair() throws NoNoteSetException;
}
