package com.dukeacademy.storage.notes;

import com.dukeacademy.commons.exceptions.DataConversionException;
import com.dukeacademy.commons.util.FileUtil;
import com.dukeacademy.commons.util.JsonUtil;
import com.dukeacademy.model.notes.NoteBank;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public interface NoteBankStorage {

    Path getNoteBankFilePath();

    Optional<NoteBank> readNoteBank() throws DataConversionException, IOException;

    Optional<NoteBank> readNoteBank(Path filePath) throws DataConversionException, IOException;

    void saveNoteBank(NoteBank noteBank) throws IOException;

    static void saveNoteBank(NoteBank noteBank, Path filePath) throws IOException {
        requireNonNull(noteBank);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableNoteBank(noteBank), filePath);
    }
}
