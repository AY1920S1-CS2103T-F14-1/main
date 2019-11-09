package com.dukeacademy.logic.notes;

import com.dukeacademy.commons.core.LogsCenter;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Logger;

public class SketchManager {
    private final Path sketchStorageFolder;
    private final Logger logger;
    private final boolean isStorageFolderAvailable;

    public SketchManager(Path sketchStorageFolder) {
        this.logger = LogsCenter.getLogger(SketchManager.class);

        this.sketchStorageFolder = sketchStorageFolder;
        if (!sketchStorageFolder.toFile().isDirectory()) {
            logger.info("No sketch storage folder found at : " + sketchStorageFolder);
            logger.info("Creating new folder...");

            if (!sketchStorageFolder.toFile().mkdirs()) {
                logger.warning("Unable to make sketch storage directory, sketches will not be saved");
                isStorageFolderAvailable = false;
                return;
            }

            logger.info("Sketch storage folder successfully created at " + sketchStorageFolder);
        }

        isStorageFolderAvailable = true;
    }

    public Path getSketchStorageFolderPath() {
        return this.sketchStorageFolder;
    }

    public WritableImage loadSketch(UUID sketchId) throws IOException {
        if (!isStorageFolderAvailable) {
            logger.warning("Sketch storage file was not initialized, returning null...");
        }

        String sketchFileName = sketchId.getMostSignificantBits()
                + sketchId.getLeastSignificantBits() + ".png";

        File file = sketchStorageFolder.resolve(sketchFileName).toFile();

        logger.info("Loading sketch from : " + file.getPath());

        if (!file.exists()) {
            logger.warning("Sketch not found at : " + file.getPath());
            throw new FileNotFoundException();
        }

        BufferedImage image = ImageIO.read(file);

        logger.info("Successfully loaded sketch from : " + file.getPath());
        return SwingFXUtils.toFXImage(image, null);
    }

    public void saveSketch(UUID sketchId, WritableImage sketch) throws IOException {
        if (!isStorageFolderAvailable) {
            logger.warning("Sketch storage file was not initialized, sketch will not be saved...");
        }

        String sketchFileName = sketchId.getMostSignificantBits()
                + sketchId.getLeastSignificantBits() + ".png";

        File file = sketchStorageFolder.resolve(sketchFileName).toFile();

        logger.info("Saving sketch at : " + file.getPath());

        if (!file.createNewFile()) {
            logger.warning("Unable to create sketch image file at : " + file.getPath());
            throw new IOException("Unable to create image file : " + file.toPath());
        }

        RenderedImage drawing = SwingFXUtils.fromFXImage(sketch, null);
        ImageIO.write(drawing, "png", file);

        logger.info("Sketch  saved successfully at : " + file.getPath());
    }
}
