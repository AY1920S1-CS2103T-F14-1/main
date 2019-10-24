package com.dukeacademy.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dukeacademy.model.question.Question;
import com.dukeacademy.model.question.entities.Status;
import com.dukeacademy.observable.Observable;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class HomePage extends UiPart<Region> {
    private static final String FXML = "HomePage.fxml";
    private static final String[] skillTiers = {"Novice", "Apprentice", "Master", "Grandmaster", "Duke"};
    private static final int[] skillTierCeilings = {29, 49, 69, 89, 100};

    @FXML
    private ProgressIndicator indicator;

    @FXML
    private Text numDone;

    @FXML
    private Text numTotal;

    @FXML
    private Text currentTier;

    @FXML
    private Text numToNextTier;

    @FXML
    private Text nextTier;

    @FXML
    private Text progressDescription;

    @FXML
    private AnchorPane attempting;

    @FXML
    private AnchorPane bookmarked;

    @FXML
    private Button userGuideButton;

    @FXML
    private Button devGuideButton;

    @FXML
    private Button aboutUsButton;

    @FXML
    private Button contactUsButton;


    public HomePage(ObservableList<Question> questions) {
        super(FXML);
        int done = computeNumDone(questions);
        int total = computeNumTotal(questions);
        updateNumDone(done);
        updateNumTotal(total);
        double progress = computeProgress(done, total);
        updateIndicator(progress);

        String current = computeCurrentTier(progress);
        updateCurrentTier(current);

        if (current.equals("Duke")) {
            removeProgressDescription();
        } else {
            String next = computeNextTier(progress);
            updateNextTier(next);
            int toNextTier = computeNumToNextTier(progress, current, total);
            updateNumToNextTier(toNextTier);
        }

        updateAttempting(questions);
        updateBookmarked(questions);
    }

    private int computeNumDone(ObservableList<Question> questions) {
        int numDone = 0;
        for (Question q : questions) {
            if (q.getStatus() == Status.PASSED) {
                numDone++;
            }
        }
        return numDone;
    }

    private int computeNumTotal(ObservableList<Question> questions) {
        return questions.size();
    }

    private double computeProgress(int numDone, int numTotal) {
        double numDoneDouble = (double) numDone;
        double numTotalDouble = (double) numTotal;
        return numDoneDouble / numTotalDouble;
    }

    private String computeCurrentTier(double progress) {
        double progressInPercent = progress * 100;
        int roundedProgress = (int) Math.round(progressInPercent);

        int currentTierIndex = -1;

        // Trivial case - Progress within the first skill tier value range, so current tier is the lowest tier
        if (roundedProgress <= skillTierCeilings[0]) {
            currentTierIndex = 0;
        }
        // Trivial case - Progress is 100%, so current tier is the highest tier
        if (roundedProgress == 100) {
            currentTierIndex = skillTierCeilings.length - 1;
        }

        // Else, when it comes to non-trivial cases
        for (int i = 0; i < skillTierCeilings.length; i++) {
            if (roundedProgress > skillTierCeilings[i]) {
                // continue traversing the array
            } else {
                currentTierIndex = i;
                break;
            }
        }

        String currentTier = skillTiers[currentTierIndex];
        return currentTier;
    }

    // This method will only be called if current tier is not already the highest tier.
    private String computeNextTier(double progress) {
        double progressInPercent = progress * 100;
        int roundedProgress = (int) Math.round(progressInPercent);

        int nextTierIndex = -1;

        // Trivial case - Progress within the first skill tier value range, so current tier is the lowest tier
        // Its next tier will be the second lowest tier
        if (roundedProgress <= skillTierCeilings[0]) {
            nextTierIndex = 1;
        }

        // Else, when it comes to non-trivial cases
        for (int i = 0; i < skillTierCeilings.length; i++) {
            if (roundedProgress > skillTierCeilings[i]) {
                // continue traversing the array
            } else {
                nextTierIndex = i + 1;
                break;
            }
        }

        String nextTier = skillTiers[nextTierIndex];
        return nextTier;
    }

    // This method will only be called if current tier is not already the highest tier.
    private int computeNumToNextTier(double progress, String currentTier, int numTotal) {
        List<String> skillTiersList = Arrays.asList(skillTiers);
        int currentTierIndex = skillTiersList.indexOf(currentTier);

        int ceilingOfCurrentTier = skillTierCeilings[currentTierIndex];
        int floorOfNextTier = ceilingOfCurrentTier + 1;

        double progressInPercent = progress * 100;
        int roundedProgress = (int) Math.round(progressInPercent);

        int gapInPercent = floorOfNextTier - roundedProgress;
        double gapInDouble = (double) gapInPercent / 100 * numTotal;
        int numToNextTier = (int) Math.round(gapInDouble);

        return numToNextTier;
    }

    private void updateIndicator(double progress) {
        indicator.setProgress(progress);
    }

    private void updateNumDone(int inputNumDone) {
        String numDoneString = inputNumDone + "";
        numDone.setText(numDoneString);
    }

    private void updateNumTotal(int inputNumTotal) {
        String numTotalString = inputNumTotal + "";
        numTotal.setText(numTotalString);
    }

    private void updateCurrentTier(String tier) {
        currentTier.setText(tier);
    }

    private void updateNextTier(String tier) {
        nextTier.setText(tier);
    }

    private void updateNumToNextTier(int num) {
        String numToNextTierString = num + "";
        numToNextTier.setText(numToNextTierString);
    }

    private void updateAttempting(ObservableList<Question> questions) {
        ListView<Label> attemptingListView = new ListView<>();
        for (Question q : questions) {
            if (q.getStatus() == Status.ATTEMPTED) {
                Label qTitle = new Label(q.getTitle());
                attemptingListView.getItems().add(qTitle);
            }
        }
        attemptingListView.setPrefWidth(300);
        attempting.getChildren().add(attemptingListView);
    }

    private void updateBookmarked(ObservableList<Question> questions) {
        ListView<Label> bookmarkedListView = new ListView<>();
        for (Question q : questions) {
            if (q.isBookmarked() == true) {
                Label qTitle = new Label(q.getTitle());
                bookmarkedListView.getItems().add(qTitle);
            }
        }
        bookmarkedListView.setPrefWidth(300);
        bookmarked.getChildren().add(bookmarkedListView);
    }

    private void removeProgressDescription() {
        numToNextTier.setText("");
        progressDescription.setText("");
        nextTier.setText("");
    }
}