package com.dukeacademy.storage.prefs;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import com.dukeacademy.commons.exceptions.DataConversionException;

import com.dukeacademy.model.prefs.ReadOnlyUserPrefs;
import com.dukeacademy.model.prefs.UserPrefs;

/**
 * Represents a storage for {@link UserPrefs}.
 */
public interface UserPrefsStorage {

    /**
     * Returns the file path of the UserPrefs data file.
     */
    Path getUserPrefsFilePath();

    /**
     * Returns UserPrefs data from storage.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;
}
