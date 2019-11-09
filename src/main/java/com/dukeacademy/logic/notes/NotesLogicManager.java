package com.dukeacademy.logic.notes;

import com.dukeacademy.commons.core.LogsCenter;
import com.dukeacademy.commons.exceptions.DataConversionException;
import com.dukeacademy.data.Pair;
import com.dukeacademy.logic.notes.exceptions.NoNoteSetException;
import com.dukeacademy.logic.notes.exceptions.NoteNotFoundRuntimeException;
import com.dukeacademy.logic.program.exceptions.SubmissionChannelNotSetException;
import com.dukeacademy.model.notes.Note;
import com.dukeacademy.model.notes.NoteBank;
import com.dukeacademy.model.notes.StandardNoteBank;
import com.dukeacademy.observable.Observable;
import com.dukeacademy.observable.StandardObservable;
import com.dukeacademy.storage.notes.NoteBankStorage;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.image.WritableImage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.logging.Logger;

public class NotesLogicManager implements NotesLogic {
    private static final WritableImage emptySketch = new WritableImage(2000, 2000);

    private final Logger logger;
    private final NoteBankStorage storage;
    private final NoteBank noteBank;
    private final FilteredList<Note> filteredList;
    private final StandardObservable<Pair<Note, WritableImage>> selectedNoteAndSketch;

    private NoteSubmissionChannel noteSubmissionChannel;
    private SketchManager sketchManager;

    public NotesLogicManager(NoteBankStorage storage) {
        this.logger = LogsCenter.getLogger(NotesLogicManager.class);
        this.storage = storage;
        this.noteBank = this.loadNotesFromStorage();
        this.filteredList = new FilteredList<>(this.noteBank.getReadOnlyNotesObservableList());
        this.selectedNoteAndSketch = new StandardObservable<>();

        Path sketchStoragePath = storage.getNoteBankFilePath().getParent().resolve("sketches");
        this.sketchManager = new SketchManager(sketchStoragePath);
    }

    @Override
    public SketchManager getSketchManager() {
        return this.sketchManager;
    }

    @Override
    public void setNoteSubmissionChannel(NoteSubmissionChannel noteSubmissionChannel) {
        this.noteSubmissionChannel = noteSubmissionChannel;
        logger.info("Successfully added note submission channel : " + noteSubmissionChannel);
    }

    @Override
    public ObservableList<Note> getAllNotesList() {
        return noteBank.getReadOnlyNotesObservableList();
    }

    @Override
    public ObservableList<Note> getFilteredNotesList() {
        return this.filteredList;
    }

    @Override
    public void filterNotesList(Predicate<Note> predicate) {
        this.filteredList.setPredicate(predicate);
    }

    @Override
    public void addNote(Note note) {
        try {
            this.sketchManager.saveSketch(note.getSketchId(), NotesLogicManager.emptySketch);
        } catch (IOException e) {
            logger.warning("Unable to save sketch : " + note.getSketchId());
        }

        this.noteBank.addNote(note);
        this.saveNotesToStorage(this.noteBank);
    }

    @Override
    public void addNoteWithSketch(Note note, WritableImage sketch) {
        try {
            this.sketchManager.saveSketch(note.getSketchId(), sketch);
        } catch (IOException e) {
            logger.warning("Unable to save sketch : " + note.getSketchId());
        }

        this.noteBank.addNote(note);
        this.saveNotesToStorage(this.noteBank);
    }

    @Override
    public Optional<Note> saveNoteFromNoteSubmissionChannel() {
        if (this.noteSubmissionChannel == null) {
            throw new SubmissionChannelNotSetException();
        }

        Pair<Note, WritableImage> noteAndSketchPair;

        try {
             noteAndSketchPair = this.noteSubmissionChannel.getNoteAndSketchPair();
        } catch (NoNoteSetException e) {
            logger.info("No current note set, skipping save...");
            return Optional.empty();
        }

        Note note = noteAndSketchPair.getHead();
        WritableImage sketch = noteAndSketchPair.getTail();

        Optional<Note> oldNote = this.getAllNotesList().stream()
                .filter(note::equals)
                .findFirst();

        if (oldNote.isEmpty()) {
            this.addNoteWithSketch(note, sketch);
            logger.info("New note detected, creating new note : " + note);
            return Optional.of(note);
        }

        this.replaceNote(oldNote.get(), note, sketch);
        return Optional.of(note);
    }

    @Override
    public void replaceNote(Note oldNote, Note newNote, WritableImage newSketch) {
        logger.info("Replacing old note : " + oldNote + " with new note : " + newNote);
        UUID sketchId = newNote.getSketchId();

        try {
            this.sketchManager.saveSketch(sketchId, newSketch);
        } catch (IOException e) {
            logger.warning("Unable to save sketch : " + sketchId);
        }

        this.noteBank.replaceNote(oldNote, newNote);
        this.saveNotesToStorage(this.noteBank);
    }

    @Override
    public Observable<Pair<Note, WritableImage>> getSelectedNote() {
        return this.selectedNoteAndSketch;
    }

    @Override
    public void selectNote(int index) {
        try {
            Note selectedNote = this.getAllNotesList().get(index);
            this.selectNote(selectedNote);
        } catch (IndexOutOfBoundsException e) {
            throw new NoteNotFoundRuntimeException();
        }
    }

    @Override
    public void selectNote(Note note) {
        Note selectedNote = this.getAllNotesList().stream()
                .filter(note::equals)
                .findFirst()
                .orElseThrow(NoteNotFoundRuntimeException::new);

        WritableImage sketch;
        try {
            sketch = this.sketchManager.loadSketch(selectedNote.getSketchId());
        } catch (IOException e) {
            logger.warning("Unable to load sketch : " + selectedNote.getSketchId());
            sketch = null;
        }

        this.selectedNoteAndSketch.setValue(new Pair<>(selectedNote, sketch));
    }

    @Override
    public void deleteNote(int index) {
        try {
            Note selectedNote = this.getAllNotesList().get(index);
            this.noteBank.deleteNote(selectedNote);
        } catch (IndexOutOfBoundsException e) {
            throw new NoteNotFoundRuntimeException();
        }

        this.saveNotesToStorage(this.noteBank);
    }

    private NoteBank loadNotesFromStorage() {
        logger.info("Storage instance built, trying to load notes...");
        try {
            return this.storage.readNoteBank().get();
        } catch (IOException | DataConversionException | NullPointerException e) {
            logger.info("Unable to load note bank from : " + storage.getNoteBankFilePath()
                    + ".\n Creating new note bank...");
            return new StandardNoteBank();
        }
    }

    private void saveNotesToStorage(NoteBank noteBank) {
        try {
            storage.saveNoteBank(noteBank);
        } catch (IOException e) {
            logger.warning("Unable to save note bank to :" + storage.getNoteBankFilePath());
        }
    }
}
