package com.dukeacademy.ui;

import com.dukeacademy.model.program.TestCaseResult;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class CodeResultCard extends UiPart<Region> {
    private static final String FXML = "CodeResultCard.fxml";

    @FXML
    private Label testCaseId;

    @FXML
    private Label input;

    @FXML
    private Label expectedOutput;

    @FXML
    private Label actualOutput;

    @FXML
    private Label evaluation;

    @FXML
    private Label failReason;

    public CodeResultCard(TestCaseResult testCaseResult, int id) {
        super(FXML);
        testCaseId.setText(id + "");
        input.setText(testCaseResult.getInput());
        expectedOutput.setText(testCaseResult.getExpectedOutput());
        actualOutput.setText(testCaseResult.getActualOutput().get());
        evaluation.setText(testCaseResult.isSuccessful()?"PASS":"FAIL");
        failReason.setText(testCaseResult.isSuccessful()?"":"Incorrect Output");
    }
}
