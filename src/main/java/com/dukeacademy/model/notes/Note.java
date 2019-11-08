package com.dukeacademy.model.notes;

import com.dukeacademy.model.question.Question;

import java.util.UUID;

import static com.dukeacademy.commons.util.CollectionUtil.requireAllNonNull;

public class Note {
    private static int noteCount = 1;

    private final int id;
    private final String title;
    private final String notes;
    private final String sketchFileName;

    private Note(int id, String title, String notes, String sketchFileName) {
        requireAllNonNull(id ,title, notes, sketchFileName);

        this.id = id;
        this.title = title;
        this.notes = notes;
        this.sketchFileName = sketchFileName;
    }

    public Note(String title, String notes, String sketchFileName) {
        requireAllNonNull(title, notes, sketchFileName);

        this.id = noteCount++;
        this.title = title;
        this.notes = notes;
        this.sketchFileName = sketchFileName;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNotes() {
        return notes;
    }

    public String getSketchFileName() {
        return sketchFileName;
    }

    public Note withNewNotes(String notes) {
        return new Note(this.id, this.title, notes, this.sketchFileName);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Note) {
            return this.id == ((Note) o).id;
        }

        return false;
    }
}
