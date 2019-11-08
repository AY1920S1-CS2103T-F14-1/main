package com.dukeacademy.ui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class NotesCanvas extends UiPart<Region> {
    private static final String FXML = "Canvas.fxml";
    private final GraphicsContext graphicsContext;

    @FXML
    private ResizableCanvas canvas;

    public NotesCanvas() {
        super(FXML);
        graphicsContext = canvas.getGraphicsContext2D();
        initDraw(graphicsContext);
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickHandler());
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new MouseDragHandler());
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, new MouseReleaseHandler());
    }

    private void initDraw(GraphicsContext graphicsContext) {
        double canvasWidth = graphicsContext.getCanvas().getWidth();
        double canvasHeight = graphicsContext.getCanvas().getHeight();

        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(5);
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
