package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.Address;
import seedu.address.model.applicant.Email;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.Phone;
import seedu.address.model.application.Application.ApplicationStatus;
import seedu.address.model.position.Description;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ApplicantParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String PLACEHOLDER_DESCRIPTION = "This is a placeholder description";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String position} into a {@code Position} with a placeholder description.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param position the string title of the related position.
     * @return the Position object of this corresponding position.
     */
    public static Position parsePosition(String position) {
        requireNonNull(position);
        String trimmedPosition = position.trim();
        return new Position(new Title(trimmedPosition), new Description(PLACEHOLDER_DESCRIPTION));
    }

    // All position related methods

    /**
     * Parses a {@code String title} into a {@code Title}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code title} is invalid.
     */
    public static Title parseTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();
        if (!Title.isValidTitle(trimmedTitle)) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(trimmedTitle);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String applicationStatus} into a {@code ApplicationStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code applicationStatus} is invalid.
     */
    public static ApplicationStatus parseApplicationStatus(String applicationStatus) throws ParseException {
        requireNonNull(applicationStatus);
        String trimmedApplicationStatus = applicationStatus.trim();
        try {
            return ApplicationStatus.fromString(trimmedApplicationStatus);
        } catch (IllegalArgumentException iae) {
            throw new ParseException(ApplicationStatus.MESSAGE_CONSTRAINTS);
        }
    }
}
