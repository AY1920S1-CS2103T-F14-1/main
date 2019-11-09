package com.dukeacademy.ui;

import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.logic.notes.NotesLogic;
import com.dukeacademy.logic.notes.SketchManager;
import com.dukeacademy.model.notes.Note;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    private Button test;

    private Logger logger;
    private NotesCanvas notesCanvas;
    private Note selectedNote;
    private final SketchManager sketchManager;
    private final NotesLogic notesLogic;

    public NotesPage(NotesLogic notesLogic) {
        super(FXML);
        this.logger = LogsCenter.getLogger(NotesPage.class);

        this.notesLogic = notesLogic;
        this.sketchManager = notesLogic.getSketchManager();
        notesLogic.setNoteSubmissionChannel(this::getCurrentNote);

        notesCanvas = new NotesCanvas();
        canvasPlaceholder.getChildren().add(notesCanvas.getRoot());

        NoteListPanel noteListPanel = new NoteListPanel(notesLogic.getFilteredNotesList());
        noteListPanel.getRoot().addEventFilter(MouseEvent.MOUSE_PRESSED, Event::consume);
        notesListPanelPlaceholder.getChildren().add(noteListPanel.getRoot());

        notesLogic.getSelectedNote().addListener(this::loadNote);

        test.setOnMouseClicked(event -> notesLogic.addNote(this.getCurrentNote()));
    }

    public void newNote() {
        this.selectedNote = null;
        this.notesCanvas.clearCanvas();
        this.notes.clear();
    }

    private void saveCurrentNote() {
        notesLogic.addNote(this.getCurrentNote());
    }

    private Note getCurrentNote() {
        String title = "Test";
        String content = notes.getText().strip();

        Note currentNote;

        if (selectedNote == null) {
            currentNote = new Note(title, content);
        } else {
            currentNote = selectedNote.withNewNotes(content);
        }

        try {
            sketchManager.saveSketch(currentNote.getSketchId(), notesCanvas.getImage());
        } catch (IOException e) {
            logger.warning("Unable to save current sketch : " + currentNote.getSketchId()
                    + " @ " + sketchManager.getSketchStorageFolderPath());
        }

        return currentNote;
    }

    private void loadNote(Note note) {
        if (note == null) {
            return;
        }

        selectedNote = note;

        notes.setText(note.getContent());

        WritableImage sketch;

        try {
            sketch = sketchManager.loadSketch(note.getSketchId());
        } catch (FileNotFoundException e) {
            logger.warning("Unable to find sketch : " + note.getSketchId()
                    + " @ " + sketchManager.getSketchStorageFolderPath());
            return;
        } catch (IOException e) {
            logger.warning("Unable to read sketch : " + note.getSketchId()
                    + " @ " + sketchManager.getSketchStorageFolderPath());
            return;
        }

        if (sketch != null) {
            notesCanvas.drawImage(sketch);
        }
    }
}
