package com.dukeacademy.commons.core;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Level;

/**
 * Config values used by the app
 */
public class Config {

    /**
     * The constant DEFAULT_CONFIG_FILE.
     */
    public static final Path DEFAULT_CONFIG_FILE = Paths.get("config.json");

    // Config values customizable through config file
    private Level logLevel = Level.INFO;

    public Level getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(Level logLevel) {
        this.logLevel = logLevel;
    }

    public Path getTestOutputPath() {
        return testOutputPath;
    }

    public void setTestOutputPath(Path testOutputPath) {
        this.testOutputPath = testOutputPath;
    }

    public Path getLogsPath() {
        return logsPath;
    }

    public void setLogsPath(Path logsPath) {
        this.logsPath = logsPath;
    }

    public Path getDataPath() {
        return dataPath;
    }

    public void setDataPath(Path dataPath) {
        this.dataPath = dataPath;
    }

    private Path testOutputPath = Paths.get("DukeAcademy/tests");
    private Path logsPath = Paths.get("DukeAcademy/logs");
    private Path dataPath = Paths.get("DukeAcademy/data");



    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Config)) { //this handles null as well.
            return false;
        }

        Config o = (Config) other;

        return Objects.equals(logLevel, o.logLevel)
                && Objects.equals(testOutputPath, o.testOutputPath)
                && Objects.equals(logsPath, o.logsPath)
                && Objects.equals(dataPath, o.dataPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logLevel, testOutputPath, logsPath, dataPath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current log level : ").append(logLevel);
        sb.append("Tests output path : ").append(logLevel);
        sb.append("Logs output path : ").append(logLevel);
        sb.append("Data path : ").append(logLevel);
        return sb.toString();
    }

}
