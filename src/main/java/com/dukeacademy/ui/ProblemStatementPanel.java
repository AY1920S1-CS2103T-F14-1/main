package com.dukeacademy.ui;

import com.dukeacademy.model.question.Question;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

import static java.util.Objects.requireNonNull;

public class ProblemStatementPanel extends UiPart<Region> {
    private static final String FXML = "ProblemStatement.fxml";

    /**
     * Instantiates a new Problem Statement Display.
     */
    public ProblemStatementPanel() {
        super(FXML);
    }

    @FXML
    private TextArea problemStatementDisplay;

    /**
     * Instantiates a new problem statement panel.
     *
     * @param problemStatement the problem statement
     */
    public ProblemStatementPanel(String problemStatement) {
        super(FXML);
        problemStatementDisplay.setText(problemStatement);
        problemStatementDisplay.setWrapText(true);
    }

    /**
     * Displays problem statement for a particular question.
     *
     * @param problemStatement the feedback to user
     */
    public void setProblemStatement(String problemStatement) {
        requireNonNull(problemStatement);
        problemStatementDisplay.setText(problemStatement);
    }
}
