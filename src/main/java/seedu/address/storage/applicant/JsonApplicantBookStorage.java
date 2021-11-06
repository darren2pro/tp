package seedu.address.storage.applicant;

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
import seedu.address.model.ReadOnlyApplicantBook;
import seedu.address.model.ReadOnlyPositionBook;

/**
 * A class to access ApplicantBook data stored as a json file on the hard disk.
 */
public class JsonApplicantBookStorage implements ApplicantBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonApplicantBookStorage.class);

    private Path filePath;

    public JsonApplicantBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getApplicantBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyApplicantBook> readApplicantBook(ReadOnlyPositionBook positionBook) throws
            DataConversionException {
        return readApplicantBook(filePath, positionBook);
    }

    /**
     * Similar to {@link #readApplicantBook(ReadOnlyPositionBook positionBook)}.
     *
     * @param filePath location of the applicant data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyApplicantBook> readApplicantBook(Path filePath, ReadOnlyPositionBook positionBook) throws
            DataConversionException {
        requireNonNull(filePath);
        requireNonNull(positionBook);

        Optional<JsonSerializableApplicantBook> jsonApplicantBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableApplicantBook.class);
        if (!jsonApplicantBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonApplicantBook.get().toModelType(positionBook));
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveApplicantBook(ReadOnlyApplicantBook applicantBook) throws IOException {
        saveApplicantBook(applicantBook, filePath);
    }

    /**
     * Similar to {@link #saveApplicantBook(ReadOnlyApplicantBook)}.
     *
     * @param filePath location of the applicant data. Cannot be null.
     */
    public void saveApplicantBook(ReadOnlyApplicantBook applicantBook, Path filePath) throws IOException {
        requireNonNull(applicantBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableApplicantBook(applicantBook), filePath);
    }
}
