package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyApplicantBook;
import seedu.address.model.ReadOnlyPositionBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.storage.applicant.ApplicantBookStorage;
import seedu.address.storage.position.PositionBookStorage;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private UserPrefsStorage userPrefsStorage;
    private ApplicantBookStorage applicantBookStorage;
    private PositionBookStorage positionBookStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(UserPrefsStorage userPrefsStorage,
                          ApplicantBookStorage applicantBookStorage, PositionBookStorage positionBookStorage) {
        super();
        this.userPrefsStorage = userPrefsStorage;
        this.applicantBookStorage = applicantBookStorage;
        this.positionBookStorage = positionBookStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }



    // ================ PositionBook methods ==============================

    @Override
    public Path getPositionBookFilePath() {
        return positionBookStorage.getPositionBookFilePath();
    }

    @Override
    public Optional<ReadOnlyPositionBook> readPositionBook() throws DataConversionException, IOException {
        return readPositionBook(positionBookStorage.getPositionBookFilePath());
    }

    @Override
    public Optional<ReadOnlyPositionBook> readPositionBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read position data from file: " + filePath);
        return positionBookStorage.readPositionBook(filePath);
    }

    @Override
    public void savePositionBook(ReadOnlyPositionBook positionBook) throws IOException {
        savePositionBook(positionBook, positionBookStorage.getPositionBookFilePath());
    }

    @Override
    public void savePositionBook(ReadOnlyPositionBook positionBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to position book data file: " + filePath);
        positionBookStorage.savePositionBook(positionBook, filePath);
    }

    // ================ ApplicantBook methods ==============================

    @Override
    public Path getApplicantBookFilePath() {
        return applicantBookStorage.getApplicantBookFilePath();
    }

    @Override
    public Optional<ReadOnlyApplicantBook> readApplicantBook(ReadOnlyPositionBook positionBook) throws
            DataConversionException, IOException {
        return readApplicantBook(applicantBookStorage.getApplicantBookFilePath(), positionBook);
    }

    @Override
    public Optional<ReadOnlyApplicantBook> readApplicantBook(Path filePath, ReadOnlyPositionBook positionBook) throws
            DataConversionException, IOException {
        logger.fine("Attempting to read applicant data from file: " + filePath);
        return applicantBookStorage.readApplicantBook(filePath, positionBook);
    }

    @Override
    public void saveApplicantBook(ReadOnlyApplicantBook applicantBook) throws IOException {
        saveApplicantBook(applicantBook, applicantBookStorage.getApplicantBookFilePath());
    }

    @Override
    public void saveApplicantBook(ReadOnlyApplicantBook applicantBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to applicant book data file: " + filePath);
        applicantBookStorage.saveApplicantBook(applicantBook, filePath);
    }
}
