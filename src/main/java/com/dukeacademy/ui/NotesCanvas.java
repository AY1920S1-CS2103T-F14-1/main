package com.dukeacademy.ui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class NotesCanvas extends UiPart<Region> {
    private static final String FXML = "NotesCanvas.fxml";
    private static final double pencilWidth = 2;
    private static final double eraserWidth = 20;

    private final GraphicsContext graphicsContext;

    @FXML
    private ResizableCanvas canvas;

    @FXML
    private Button pencilButton;

    @FXML
    private Button eraserButton;

    @FXML
    private Button clearButton;

    public NotesCanvas() {
        super(FXML);
        graphicsContext = canvas.getGraphicsContext2D();
        initDraw(graphicsContext);
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickHandler());
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new MouseDragHandler());
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, new MouseReleaseHandler());

        pencilButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            graphicsContext.setStroke(Color.BLACK);
            graphicsContext.setLineWidth(pencilWidth);
            canvas.setCursor(Cursor.DEFAULT);
        });

        eraserButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            graphicsContext.setStroke(Color.WHITE);
            graphicsContext.setLineWidth(eraserWidth);

            Circle circle = new Circle(eraserWidth / 2, null);

            circle.setStroke(Color.BLACK);

            SnapshotParameters sp = new SnapshotParameters();
            sp.setFill(Color.TRANSPARENT);

            Image image = circle.snapshot(sp, null);
            canvas.setCursor(new ImageCursor(image));
        });

        clearButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> graphicsContext
                .clearRect(0, 0, canvas.getWidth(), canvas.getHeight()));
    }

    private void initDraw(GraphicsContext graphicsContext) {
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(pencilWidth);
    }

    private class MouseClickHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            graphicsContext.moveTo(event.getX(), event.getY());
            graphicsContext.beginPath();
            graphicsContext.stroke();
        }
    }

    private class MouseDragHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            graphicsContext.lineTo(event.getX(), event.getY());
            graphicsContext.stroke();
        }
    }

    private class MouseReleaseHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            graphicsContext.lineTo(event.getX(), event.getY());
            graphicsContext.stroke();
            graphicsContext.closePath();
        }
    }
}
