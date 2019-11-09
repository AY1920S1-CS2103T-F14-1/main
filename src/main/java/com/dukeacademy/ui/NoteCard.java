package com.dukeacademy.ui;

import com.dukeacademy.model.notes.Note;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class NoteCard extends UiPart<Region> {
    private static final String FXML = "NoteCard.fxml";

    @FXML
    private Label noteId;

    @FXML
    private Label noteTitle;

    @FXML
    private Label noteContent;

    public NoteCard(Note note, int index) {
        super(FXML);
        noteId.setText(String.valueOf(index));
        noteTitle.setText(note.getTitle());
        noteContent.setText(note.getContent());
    }
}
