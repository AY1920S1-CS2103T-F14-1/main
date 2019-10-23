package com.dukeacademy.model.prefs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

public class UserPrefsTest {
    @TempDir
    public static Path tempFolder;

    @Test
    void constructor() {
        UserPrefs prefs = new UserPrefs(tempFolder);
        assertEquals(tempFolder, prefs.getAppDirectoryPath());
        assertEquals(tempFolder.resolve("questionBank.json"), prefs.getQuestionBankFilePath());
        assertEquals(tempFolder.resolve("tests"), prefs.getTestExecutorOutputPath());
    }
}
