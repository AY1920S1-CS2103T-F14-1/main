package com.dukeacademy.ui;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.model.profile.Profile;

import java.util.ArrayList;
import java.util.logging.Logger;


public class ProfilePanel extends UiPart<Region> {
    private final Logger logger = LogsCenter.getLogger(getClass());

    private static final String FXML = "ProfilePanel.fxml";

    @FXML
    private TextArea profileDisplay;


    public ProfilePanel(ArrayList<String> profile) {
        super(FXML);
        profileDisplay.setText(profile.get(0));
    }

    public void setProfileDisplay(String s) {
        profileDisplay.setText(s);
    }
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProfilePanel)) {
            return false;
        }

        // state check
        ProfilePanel card = (ProfilePanel) other;
        return profileDisplay.getText().equals(card.profileDisplay.getText());
    }


}
