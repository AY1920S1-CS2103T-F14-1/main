package com.dukeacademy.logic;

import static com.dukeacademy.logic.commands.CommandTestUtil.DIFFICULTY_DESC_AMY;
import static com.dukeacademy.logic.commands.CommandTestUtil.STATUS_DESC_AMY;
import static com.dukeacademy.logic.commands.CommandTestUtil.TITLE_DESC_AMY;
import static com.dukeacademy.logic.commands.CommandTestUtil.TOPIC_DESC_AMY;
import static com.dukeacademy.testutil.Assert.assertThrows;
import static com.dukeacademy.testutil.TypicalQuestions.AMY;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.dukeacademy.commons.core.Messages;
import com.dukeacademy.logic.commands.AddCommand;
import com.dukeacademy.logic.commands.CommandResult;
import com.dukeacademy.logic.commands.ListCommand;
import com.dukeacademy.logic.commands.exceptions.CommandException;
import com.dukeacademy.logic.parser.exceptions.ParseException;
import com.dukeacademy.logic.question.QuestionsLogic;
import com.dukeacademy.logic.question.QuestionsLogicManager;
import com.dukeacademy.model.Model;
import com.dukeacademy.model.ModelManager;
import com.dukeacademy.model.ReadOnlyQuestionBank;
import com.dukeacademy.model.UserPrefs;
import com.dukeacademy.model.question.Question;
import com.dukeacademy.storage.JsonQuestionBankStorage;
import com.dukeacademy.storage.JsonUserPrefsStorage;
import com.dukeacademy.storage.StorageManager;
import com.dukeacademy.testutil.QuestionBuilder;

public class QuestionsLogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private QuestionsLogic questionsLogic;

    @BeforeEach
    public void setUp() {
        JsonQuestionBankStorage questionBankStorage =
                new JsonQuestionBankStorage(temporaryFolder.resolve("questionBank.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(questionBankStorage, userPrefsStorage);
        questionsLogic = new QuestionsLogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand,
            Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonQuestionBankIoExceptionThrowingStub
        JsonQuestionBankStorage questionBankStorage =
                new JsonQuestionBankIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionQuestionBank.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(questionBankStorage, userPrefsStorage);
        questionsLogic = new QuestionsLogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + TITLE_DESC_AMY + TOPIC_DESC_AMY + STATUS_DESC_AMY
                + DIFFICULTY_DESC_AMY;
        Question expectedQuestion = new QuestionBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addQuestion(expectedQuestion);
        String expectedMessage = QuestionsLogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> questionsLogic.getFilteredPersonList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = questionsLogic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getQuestionBank(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> questionsLogic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonQuestionBankIoExceptionThrowingStub
        extends JsonQuestionBankStorage {
        private JsonQuestionBankIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveQuestionBank(ReadOnlyQuestionBank questionBank, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
