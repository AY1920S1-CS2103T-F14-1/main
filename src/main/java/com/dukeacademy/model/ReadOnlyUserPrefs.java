package com.dukeacademy.model;

import java.nio.file.Path;

import com.dukeacademy.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    /**
     * Gets gui settings.
     *
     * @return the gui settings
     */
    GuiSettings getGuiSettings();

    /**
     * Gets question bank file path.
     *
     * @return the question bank file path
     */
    Path getQuestionBankFilePath();

}
