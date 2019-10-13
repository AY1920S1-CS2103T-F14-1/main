package com.dukeacademy.solution.models;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Represents a java class file in the application. The canonical name refers to the name of the class you would
 * use in an import statement. This is needed to execute packaged classes from the root classpath.
 */
public class ClassFile {
    private String canonicalName;
    private String classPath;

    /**
     * Instantiates a new Class file.
     *
     * @param canonicalName the canonical name
     * @param classPath     the class path
     * @throws FileNotFoundException the file not found exception
     */
    public ClassFile(String canonicalName, String classPath) throws FileNotFoundException {
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

    /**
     * Gets file.
     *
     * @return the file
     */
    public File getFile() {
        String absolutePath = classPath + canonicalName.replace(".", File.separator) + ".class";
        return new File(absolutePath);
    }
}
