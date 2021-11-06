package seedu.address.storage.position;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyPositionBook;

/**
 * A class to access PositionBook data stored as a json file on the hard disk.
 */
public class JsonPositionBookStorage implements PositionBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPositionBookStorage.class);

    private Path filePath;

    public JsonPositionBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPositionBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPositionBook> readPositionBook() throws DataConversionException {
        return readPositionBook(filePath);
    }

    /**
     * Similar to {@link #readPositionBook()}.
     *
     * @param filePath location of the position data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyPositionBook> readPositionBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePositionBook> jsonPositionBook = JsonUtil.readJsonFile(
                filePath, JsonSerializablePositionBook.class);
        if (!jsonPositionBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonPositionBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void savePositionBook(ReadOnlyPositionBook positionBook) throws IOException {
        savePositionBook(positionBook, filePath);
    }

    /**
     * Similar to {@link #savePositionBook(ReadOnlyPositionBook)}.
     *
     * @param filePath location of the position data. Cannot be null.
     */
    public void savePositionBook(ReadOnlyPositionBook positionBook, Path filePath) throws IOException {
        requireNonNull(positionBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePositionBook(positionBook), filePath);
    }

}
