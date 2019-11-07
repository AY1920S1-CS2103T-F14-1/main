package com.dukeacademy.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class NotesPage extends UiPart<Region> {
    private static final String FXML = "NotesPage.fxml";

    @FXML
    private StackPane problemStatementPlaceholder;

    @FXML
    private StackPane textNotesPlaceholder;

    @FXML
    private AnchorPane canvasPlaceholder;

    public NotesPage() {
        super(FXML);
    }

    
}
