package seedu.address.storage.applicant;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyApplicantBook;
import seedu.address.model.ReadOnlyPositionBook;

/**
 * Represents a storage for {@link seedu.address.model.ApplicantBook}.
 */
public interface ApplicantBookStorage {

    /**
     * Returns the file path of the applicant data file.
     */
    Path getApplicantBookFilePath();

    /**
     * Returns ApplicantBook data as a {@link ReadOnlyApplicantBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyApplicantBook> readApplicantBook(ReadOnlyPositionBook positionBook) throws
            DataConversionException, IOException;

    /**
     * @see #getApplicantBookFilePath()
     */
    Optional<ReadOnlyApplicantBook> readApplicantBook(Path filePath, ReadOnlyPositionBook positionBook) throws
            DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyApplicantBook} to the storage.
     * @param applicantBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveApplicantBook(ReadOnlyApplicantBook applicantBook) throws IOException;

    /**
     * @see #saveApplicantBook(ReadOnlyApplicantBook)
     */
    void saveApplicantBook(ReadOnlyApplicantBook applicantBook, Path filePath) throws IOException;

}
