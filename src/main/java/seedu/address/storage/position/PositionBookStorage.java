package seedu.address.storage.position;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyPositionBook;

/**
 * Represents a storage for {@link seedu.address.model.PositionBook}.
 */
public interface PositionBookStorage {

    /**
     * Returns the file path of the position data file.
     */
    Path getPositionBookFilePath();

    /**
     * Returns PositionBook data as a {@link ReadOnlyPositionBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyPositionBook> readPositionBook() throws DataConversionException, IOException;

    /**
     * @see #getPositionBookFilePath()
     */
    Optional<ReadOnlyPositionBook> readPositionBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyPositionBook} to the storage.
     * @param positionBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePositionBook(ReadOnlyPositionBook positionBook) throws IOException;

    /**
     * @see #savePositionBook(ReadOnlyPositionBook)
     */
    void savePositionBook(ReadOnlyPositionBook positionBook, Path filePath) throws IOException;

}
