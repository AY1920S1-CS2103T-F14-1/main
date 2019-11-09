package com.dukeacademy.ui;

import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.data.Pair;
import com.dukeacademy.logic.notes.NotesLogic;
import com.dukeacademy.logic.notes.exceptions.NoNoteSetException;
import com.dukeacademy.model.notes.Note;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.util.logging.Logger;

public class NotesPage extends UiPart<Region> {
    private static final String FXML = "NotesPage.fxml";

    @FXML
    private StackPane notesListPanelPlaceholder;

    @FXML
    private AnchorPane canvasPlaceholder;

    @FXML
    private TextArea notes;

    @FXML
    private TextField currentNoteTitle;

    private Logger logger;
    private NotesCanvas notesCanvas;
    private Note selectedNote;
    private final NotesLogic notesLogic;

    public NotesPage(NotesLogic notesLogic) {
        super(FXML);
        this.logger = LogsCenter.getLogger(NotesPage.class);

        this.notesLogic = notesLogic;
        notesLogic.setNoteSubmissionChannel(this::getCurrentNoteAndSketch);

        notesCanvas = new NotesCanvas();
        canvasPlaceholder.getChildren().add(notesCanvas.getRoot());

        NoteListPanel noteListPanel = new NoteListPanel(notesLogic.getFilteredNotesList());
        noteListPanel.getRoot().addEventFilter(MouseEvent.MOUSE_PRESSED, Event::consume);
        notesListPanelPlaceholder.getChildren().add(noteListPanel.getRoot());

        notesLogic.getSelectedNote().addListener(this::loadNoteAndSketch);
    }

    private Pair<Note, WritableImage> getCurrentNoteAndSketch() throws NoNoteSetException {
        if (selectedNote == null) {
            throw new NoNoteSetException();
        }

        Note currentNote = selectedNote.withNewNotes(notes.getText().strip());
        selectedNote = currentNote;
        return new Pair<>(currentNote, notesCanvas.getImage());
    }

    private void loadNoteAndSketch(Pair<Note, WritableImage> noteAndSketchPair) {
        if (noteAndSketchPair == null) {
            return;
        }

        Note note = noteAndSketchPair.getHead();
        WritableImage sketch = noteAndSketchPair.getTail();

        selectedNote = note;
        currentNoteTitle.setText(note.getTitle());
        notes.setText(note.getContent());

        if (sketch != null) {
            notesCanvas.clearCanvas();
            notesCanvas.drawImage(sketch);
        }
    }
}
