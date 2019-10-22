package com.dukeacademy.testexecutor.models;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Represents a Java file in the application. The canonical name refers to the name of the class you would
 * use in an import statement. This is needed to execute packaged classes from the root classpath.
 */
public class JavaFile {
    private String canonicalName;
    private String classPath;

    /**
     * Instantiates a new Java file.
     *
     * @param canonicalName the canonical name
     * @param classPath     the class path
     * @throws FileNotFoundException the file not found exception
     */
    public JavaFile(String canonicalName, String classPath) throws FileNotFoundException {
        this.canonicalName = canonicalName;
        this.classPath = classPath;

        if (!this.getFile().exists()) {
            throw new FileNotFoundException();
        }
    }

    /**
     * Gets canonical name.
     *
     * @return the canonical name
     */
    public String getCanonicalName() {
        return this.canonicalName;
    }

    /**
     * Gets class path.
     *
     * @return the class path
     */
    public String getClassPath() {
        return this.classPath;
    }


    public String getAbsolutePath() {
        String trimmedClassPath = classPath;
        if (classPath.lastIndexOf(File.separator) == classPath.length() - 1) {
            trimmedClassPath = classPath.substring(0, classPath.length() - 1);
        }

        return trimmedClassPath + File.separator + canonicalName.replace(".", File.separator) + ".java";
    }

    public File getFile() {
        String trimmedClassPath = classPath;
        if (classPath.lastIndexOf(File.separator) == classPath.length() - 1) {
            trimmedClassPath = classPath.substring(0, classPath.length() - 1);
        }

        return new File(this.getAbsolutePath());
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof JavaFile) {
            return ((JavaFile) object).canonicalName.equals(this.canonicalName)
                    && ((JavaFile) object).classPath.equals(this.classPath);
        } else {
            return false;
        }
    }
}
