package com.dukeacademy.commons.util;

import static com.dukeacademy.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.dukeacademy.commons.core.Config;
import com.dukeacademy.commons.exceptions.DataConversionException;

class ConfigUtilTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "ConfigUtilTest");

    @TempDir public Path tempDir;

    @Test void read_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> read(null));
    }

    @Test void read_missingFile_emptyResult() throws DataConversionException {
        assertFalse(read("NonExistentFile.json").isPresent());
    }

    @Test void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> read("NotJsonFormatConfig.json"));
    }

    @Test void read_fileInOrder_successfullyRead() throws DataConversionException {

        Config expected = getTypicalConfig();

        Config actual = read("TypicalConfig.json").get();
        assertEquals(expected, actual);
    }

    @Test void read_valuesMissingFromFile_defaultValuesUsed() throws DataConversionException {
        Config actual = read("EmptyConfig.json").get();
        assertEquals(new Config(), actual);
    }

    @Test void read_extraValuesInFile_extraValuesIgnored() throws DataConversionException {
        Config expected = getTypicalConfig();
        Config actual = read("ExtraValuesConfig.json").get();

        assertEquals(expected, actual);
    }

    private Config getTypicalConfig() {
        Config config = new Config();
        config.setLogLevel(Level.INFO);
        return config;
    }

    private Optional<Config> read(String configFileInTestDataFolder) throws DataConversionException {
        Path configFilePath = addToTestDataPathIfNotNull(configFileInTestDataFolder);
        return ConfigUtil.readConfig(configFilePath);
    }

    @Test void save_nullConfig_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> save(null, "SomeFile.json"));
    }

    @Test void save_nullFile_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> save(new Config(), null));
    }

    @Test void saveConfig_allInOrder_success() throws DataConversionException, IOException {
        Config original = getTypicalConfig();

        Path configFilePath = tempDir.resolve("TempConfig.json");

        //Try writing when the file doesn't exist
        ConfigUtil.saveConfig(original, configFilePath);
        Config readBack = ConfigUtil.readConfig(configFilePath).get();
        assertEquals(original, readBack);

        //Try saving when the file exists
        original.setLogLevel(Level.FINE);
        ConfigUtil.saveConfig(original, configFilePath);
        readBack = ConfigUtil.readConfig(configFilePath).get();
        assertEquals(original, readBack);
    }

    private void save(Config config, String configFileInTestDataFolder) throws IOException {
        Path configFilePath = addToTestDataPathIfNotNull(configFileInTestDataFolder);
        ConfigUtil.saveConfig(config, configFilePath);
    }

    private Path addToTestDataPathIfNotNull(String configFileInTestDataFolder) {
        return configFileInTestDataFolder != null
                                  ? TEST_DATA_FOLDER.resolve(configFileInTestDataFolder)
                                  : null;
    }


}
