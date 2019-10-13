package com.dukeacademy.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import com.dukeacademy.commons.exceptions.DataConversionException;
import com.dukeacademy.model.QuestionBank;
import com.dukeacademy.model.ReadOnlyQuestionBank;

/**
 * Represents a storage for {@link QuestionBank}.
 */
public interface QuestionBankStorage {

    /**
     * Returns the file path of the data file.
     *
     * @return the address book file path
     */
    Path getAddressBookFilePath();

    /**
     * Returns QuestionBank data as a {@link ReadOnlyQuestionBank}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @return the optional
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyQuestionBank> readQuestionBank() throws DataConversionException, IOException;

    /**
     * Read question bank optional.
     *
     * @param filePath the file path
     * @return the optional
     * @throws DataConversionException the data conversion exception
     * @throws IOException             the io exception
     * @see #getAddressBookFilePath() #getAddressBookFilePath()
     */
    Optional<ReadOnlyQuestionBank> readQuestionBank(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyQuestionBank} to the storage.
     *
     * @param questionBank cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveQuestionBank(ReadOnlyQuestionBank questionBank) throws IOException;

    /**
     * Save question bank.
     *
     * @param questionBank the question bank
     * @param filePath     the file path
     * @throws IOException the io exception
     * @see #saveQuestionBank(ReadOnlyQuestionBank) #saveQuestionBank(ReadOnlyQuestionBank)
     */
    void saveQuestionBank(ReadOnlyQuestionBank questionBank, Path filePath) throws IOException;

}
