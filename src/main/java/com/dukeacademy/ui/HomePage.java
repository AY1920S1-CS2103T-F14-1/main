package com.dukeacademy.ui;

import com.dukeacademy.model.question.Question;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class HomePage extends UiPart<Region> {
    private static final String FXML = "HomePage.fxml";

    @FXML
    Text numDone;

    @FXML
    Text numTotal;

    @FXML
    Text currentTier;

    @FXML
    Text numToNextTier;

    @FXML
    Text nextTier;

    @FXML
    AnchorPane attempting;

    @FXML
    AnchorPane bookmarked;

    @FXML
    Button userGuideButton;

    @FXML
    Button devGuideButton;

    @FXML
    Button aboutUsButton;

    @FXML
    Button contactUsButton;

    public HomePage(ObservableList<Question> questions) {
        super(FXML);
    }

}