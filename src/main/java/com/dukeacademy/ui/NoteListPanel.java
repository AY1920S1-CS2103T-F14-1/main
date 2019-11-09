package com.dukeacademy.ui;

import com.dukeacademy.model.notes.Note;
import com.dukeacademy.model.question.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

public class NoteListPanel extends UiPart<Region> {
    private static final String FXML = "NoteListPanel.fxml";

    @FXML
    private ListView<Note> notesListView;

    public NoteListPanel(ObservableList<Note> notesList) {
        super(FXML);
        notesListView.setItems(notesList);
        notesListView.setCellFactory(listView -> new NoteListCellView());
    }

    private static class NoteListCellView extends ListCell<Note> {
        @Override
        protected void updateItem(Note note, boolean empty) {
            super.updateItem(note, empty);

            if (empty || note == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new NoteCard(note, this.getIndex() + 1).getRoot());
            }
        }
    }
}
