package seedu.address.storage;

import java.io.IOException;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyApplicantBook;
import seedu.address.model.ReadOnlyPositionBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.storage.applicant.ApplicantBookStorage;
import seedu.address.storage.position.PositionBookStorage;

/**
 * API of the Storage component
 */
public interface Storage extends UserPrefsStorage, PositionBookStorage, ApplicantBookStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;


    @Override
    void saveApplicantBook(ReadOnlyApplicantBook applicantBook) throws IOException;

    @Override
    void savePositionBook(ReadOnlyPositionBook positionBook) throws IOException;
}
