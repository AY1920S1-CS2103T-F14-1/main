package com.dukeacademy.ui;

import com.dukeacademy.model.question.Question;
import com.dukeacademy.model.question.entities.Status;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

/**
 * Controller class for profile page, which captures useful information specific to the user's learning
 * journey and learning experience.
 */
public class ProfilePage extends UiPart<Region> {
    private static final String FXML = "ProfilePage.fxml";

    @FXML
    private TabPane profilePane;

    @FXML
    private ProgressIndicator indicator;

    @FXML
    private Text numDone;

    @FXML
    private Text numTotal;

    @FXML
    private Text skillLevel;

    @FXML
    private AnchorPane attempting;

    @FXML
    private AnchorPane bookmarked;


    public ProfilePage(ObservableList<Question> questions) {
        super(FXML);
        indicator.setMinWidth(130);
        indicator.setMinHeight(130);
        double progress = 71.0 / 80.0;
        indicator.setProgress(progress);
        numDone.setText("71");
        numTotal.setText("80");
        fillSkillLevel(progress);
        fillAttempting(attempting, questions);
        fillBookmarked(bookmarked, questions);
    }

    public void fillAttempting(AnchorPane attempting, ObservableList<Question> questions) {
        ListView<Label> listView = new ListView<>();
        for (Question q : questions) {
            if (q.getStatus() == Status.ATTEMPTED) {
                Label qTitle = new Label(q.getTitle());
                listView.getItems().add(qTitle);
            }
        }
        attempting.getChildren().add(listView);
    }

    public void fillBookmarked(AnchorPane bookmarked, ObservableList<Question> questions) {
        ListView<Label> listView = new ListView<>();
        for (Question q : questions) {
            if (q.isBookmarked() == true) {
                Label qTitle = new Label(q.getTitle());
                listView.getItems().add(qTitle);
            }
        }
        bookmarked.getChildren().add(listView);
    }

    /**
     * Closes the profile page on click of the button.
     * @param event the ActionEvent
     */
    @FXML
    public void onCloseButtonClick(ActionEvent event) {
        profilePane.setVisible(false);
    }

    /**
     * Fills the skill level of the user.
     * @param progress the degree of completion of questions achieved by the user
     */
    private void fillSkillLevel(double progress) {
        if (progress < 0.3) {
            skillLevel.setText("Novice");
        }

        if (progress > 0.3 && progress < 0.6) {
            skillLevel.setText("Apprentice");
        }

        if (progress > 0.6 && progress < 0.8) {
            skillLevel.setText("Master");
        }

        if (progress > 0.8) {
            skillLevel.setText("Grandmaster");
        }


    }
}
