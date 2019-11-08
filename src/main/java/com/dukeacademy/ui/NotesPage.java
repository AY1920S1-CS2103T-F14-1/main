package com.dukeacademy.ui;

import com.dukeacademy.logic.notes.NotesLogic;
import com.dukeacademy.logic.notes.NotesLogicManager;
import com.dukeacademy.model.question.Question;
import com.dukeacademy.observable.Observable;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

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

    private NotesCanvas notesCanvas;

    public NotesPage(NotesLogic notesLogic) {
        super(FXML);

        notesCanvas = new NotesCanvas();
        canvasPlaceholder.getChildren().add(notesCanvas.getRoot());

        NoteListPanel noteListPanel = new NoteListPanel(notesLogic.getFilteredNotesList());
        noteListPanel.getRoot().addEventFilter(MouseEvent.MOUSE_PRESSED, Event::consume);
        notesListPanelPlaceholder.getChildren().add(noteListPanel.getRoot());

        test.setOnMouseClicked(event -> this.saveCanvasDrawing());

        this.loadImageOntoCanvas();
    }

    private void saveCanvasDrawing() {
        boolean success = Paths.get("DukeAcademy","data", "notes").toFile().mkdirs();

        try {
            File file = Paths.get("DukeAcademy","data", "notes", "pic.png").toFile();
            file.createNewFile();

            RenderedImage drawing = SwingFXUtils.fromFXImage(notesCanvas.getImage(), null);
            ImageIO.write(drawing, "png", file);
        } catch (IOException e) {
            System.out.println("failed");
        }
    }

    private void loadImageOntoCanvas() {
        File file = Paths.get("DukeAcademy","data", "notes", "pic.png").toFile();

        try {
            BufferedImage image = ImageIO.read(file);
            WritableImage imagefx = SwingFXUtils.toFXImage(image, null);

            this.notesCanvas.drawImage(imagefx);
        } catch (IOException e) {
            System.out.println("Fail");
        }

    }
}
