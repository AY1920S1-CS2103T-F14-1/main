package com.dukeacademy.ui;

import com.dukeacademy.model.notes.Note;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

import java.awt.*;

public class NoteCard extends UiPart<Region> {
    private static final String FXML = "NoteCard.fxml";

    @FXML
    private Label noteTitle;

    @FXML
    private Label noteContent;

    public NoteCard(Note note) {
        super(FXML);
        noteTitle.setText(note.getTitle());
        noteContent.setText(note.getNotes());
    }
}
