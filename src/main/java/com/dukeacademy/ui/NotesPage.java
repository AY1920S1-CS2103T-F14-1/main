package com.dukeacademy.ui;

import com.dukeacademy.model.question.Question;
import com.dukeacademy.observable.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class NotesPage extends UiPart<Region> {
    private static final String FXML = "NotesPage.fxml";

    @FXML
    private StackPane problemStatementPanelPlaceholder;

    @FXML
    private AnchorPane canvasPlaceholder;

    @FXML
    private TextArea notes;

    private ProblemStatementPanel problemStatementPanel;

    public NotesPage(Observable<Question> attemptingQuestion) {
        super(FXML);

        NotesCanvas canvas = new NotesCanvas();
        canvasPlaceholder.getChildren().add(canvas.getRoot());

        problemStatementPanel = new ProblemStatementPanel();
        attemptingQuestion.addListener(question -> {
            if (question != null) {
                this.problemStatementPanel.setProblemStatement(question.getDescription());
                this.notes.setEditable(true);
            } else {
                this.notes.setEditable(false);
            }
        });
        problemStatementPanelPlaceholder.getChildren().add(problemStatementPanel.getRoot());
    }


}
