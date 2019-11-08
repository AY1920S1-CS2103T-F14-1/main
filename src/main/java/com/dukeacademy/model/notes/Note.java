package com.dukeacademy.model.notes;

import java.util.UUID;

import static com.dukeacademy.commons.util.CollectionUtil.requireAllNonNull;

public class Note {
    private static int noteCount = 1;

    private final int id;
    private final String title;
    private final String content;
    private final UUID sketchId;

    private Note(int id, String title, String content, UUID sketchId) {
        requireAllNonNull(id ,title, content, sketchId);

        this.id = id;
        this.title = title;
        this.content = content;
        this.sketchId = sketchId;
    }

    public Note(String title, String content) {
        requireAllNonNull(title, content);

        this.id = noteCount++;
        this.title = title;
        this.content = content;
        this.sketchId = UUID.randomUUID();
    }

    public Note(String title, String content, UUID sketchId) {
        this.id = noteCount++;
        this.title = title;
        this.content = content;
        this.sketchId = sketchId;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public UUID getSketchId() {
        return sketchId;
    }

    public Note withNewNotes(String notes) {
        return new Note(this.id, this.title, notes, this.sketchId);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Note) {
            return this.id == ((Note) o).id;
        }

        return false;
    }
}
