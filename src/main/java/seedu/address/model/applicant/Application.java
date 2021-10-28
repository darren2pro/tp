package seedu.address.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

/**
 * Represents an application to a specific job position.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Application {
    private final Position position;
    private final ApplicationStatus status;

    /**
     * Constructor for a job application.
     * Invoked whenever the add-applicant command is called.
      */
    public Application(Position position) {
        this(position, ApplicationStatus.PENDING);
    }

    /**
     * Internal constructor for a job application which specifies an application status.
     */
    public Application(Position position, ApplicationStatus applicationStatus) {
        requireAllNonNull(position, applicationStatus);
        this.position = position;
        this.status = applicationStatus;
    }

    /**
     * Returns a new Application with the status updated as specified.
     */
    public Application markAs(ApplicationStatus applicationStatus) {
        return new Application(position, applicationStatus);
    }

    /**
     * Returns a description of the application and its status.
     */
    public String getDescription() {
        return position.getTitle() + "; Status: " + status;
    }

    public Position getPosition() {
        return position;
    }

    public Title getTitle() {
        return position.getTitle();
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Application)) {
            return false;
        }

        Application otherApplication = (Application) other;
        return position.equals(otherApplication.position)
                && status.equals(otherApplication.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, status);
    }

    @Override
    public String toString() {
        return "{ "
                + "Position: " + position.getTitle()
                + ", Status: " + status
                + " }";
    }

    public Application getCopiedApplication() {
        return new Application(position.getCopiedPosition(), status);
    }

    /**
     * The status of a job application.
     */
    public enum ApplicationStatus {
        PENDING, ACCEPTED, REJECTED;

        public static final String MESSAGE_CONSTRAINTS;

        static {
            Stream<String> messageConstraints = Stream.of(values())
                    .map(applicationStatus -> applicationStatus.toString().toLowerCase());
            MESSAGE_CONSTRAINTS = messageConstraints.collect(
                    Collectors.joining(", ", "Application status must be one of: ", "."));
        }

        /**
         * Parses the given string into an ApplicationStatus.
         * Case-insensitive. Input must match exactly with the enum name.
         */
        public static ApplicationStatus fromString(String inputString) {
            requireNonNull(inputString);
            return valueOf(inputString.toUpperCase());
        }

        @Override
        public String toString() {
            return StringUtil.toProperCase(super.toString());
        }

    }

}
