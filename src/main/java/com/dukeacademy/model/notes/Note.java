package com.dukeacademy.model.notes;

import java.util.UUID;

import static com.dukeacademy.commons.util.CollectionUtil.requireAllNonNull;

public class Note {
    private static int noteCount = 1;

    private final String title;
    private final String content;
    private final UUID sketchId;

    public Note(String title, String content) {
        requireAllNonNull(title, content);

        this.title = title;
        this.content = content;
        this.sketchId = UUID.randomUUID();
    }

    public Note(String title, String content, UUID sketchId) {
        this.title = title;
        this.content = content;
        this.sketchId = sketchId;
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
        return new Note(this.title, notes, this.sketchId);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Note) {
            return this.sketchId.equals(((Note) o).sketchId);
        }

        return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Title: ")
                .append(title)
                .append(" Content: ")
                .append(content)
                .append(" SketchId: ")
                .append(sketchId);
        return builder.toString();
    }
}
