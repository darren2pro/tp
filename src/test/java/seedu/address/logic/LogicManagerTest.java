package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_POSITION_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_DATAENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_DATAENGINEER;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPositions.DATAENGINEER;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.AddPositionCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListPositionCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyApplicantBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.position.Position;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.applicant.JsonApplicantBookStorage;
import seedu.address.storage.position.JsonPositionBookStorage;


public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonPositionBookStorage positionBookStorage = new JsonPositionBookStorage(
                temporaryFolder.resolve("positionBook.json"));
        JsonApplicantBookStorage applicantBookStorage = new JsonApplicantBookStorage(
                temporaryFolder.resolve("applicantBook.json"));
        StorageManager storage = new StorageManager(userPrefsStorage,
                applicantBookStorage, positionBookStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deletePositionCommand = "delete-position 9";
        assertCommandException(deletePositionCommand, MESSAGE_INVALID_POSITION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listPositionCommand = ListPositionCommand.COMMAND_WORD;
        assertCommandSuccess(listPositionCommand, ListPositionCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        JsonApplicantBookStorage applicantBookStorage =
                new JsonApplicantBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionApplicantBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        JsonPositionBookStorage positionBookStorage = new JsonPositionBookStorage(
                temporaryFolder.resolve("positionBook.json"));
        StorageManager storage = new StorageManager(userPrefsStorage,
                applicantBookStorage, positionBookStorage);
        logic = new LogicManager(model, storage);

        // Execute add position command
        String addPositionCommand = AddPositionCommand.COMMAND_WORD + TITLE_DESC_DATAENGINEER
                + DESCRIPTION_DESC_DATAENGINEER;
        Position expectedPosition = DATAENGINEER.getCopiedPosition();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addPosition(expectedPosition);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addPositionCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPositionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPositionList().remove(0));
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
        CommandResult result = logic.execute(inputCommand);
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
        Model expectedModel = new ModelManager(model.getPositionBook(), new UserPrefs());
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
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonApplicantBookIoExceptionThrowingStub extends JsonApplicantBookStorage {
        private JsonApplicantBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveApplicantBook(ReadOnlyApplicantBook applicantBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }

    }
}
