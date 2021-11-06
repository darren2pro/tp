package seedu.address.storage.applicant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyPositionBook;
import seedu.address.model.applicant.Address;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Application.ApplicationStatus;
import seedu.address.model.applicant.Email;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.Phone;
import seedu.address.model.applicant.ProfileUrl;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

/**
 * Jackson-friendly version of {@link Applicant}.
 */
public class JsonAdaptedApplicant {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Applicant's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String positionApplyingTo;
    private final String applicationStatus;
    private final String gitHubUrl;

    /**
     * Constructs a {@code JsonAdaptedApplicant} with the given applicant details.
     */
    @JsonCreator
    public JsonAdaptedApplicant(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                                @JsonProperty("email") String email, @JsonProperty("address") String address,
                                @JsonProperty("positionApplyingTo") String positionApplyingTo,
                                @JsonProperty("applicationStatus") String applicationStatus,
                                @JsonProperty("gitHubUrl") String gitHubUrl) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.positionApplyingTo = positionApplyingTo;
        this.applicationStatus = applicationStatus;
        this.gitHubUrl = gitHubUrl;
    }

    /**
     * Converts a given {@code Applicant} into this class for Jackson use.
     */
    public JsonAdaptedApplicant(Applicant source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        positionApplyingTo = source.getApplication().getPosition().getTitle().fullTitle;
        applicationStatus = source.getApplication().getStatus().name();
        this.gitHubUrl = source.getGitHubUrl().url;
    }

    /**
     * Converts this Jackson-friendly adapted applicant object into the model's {@code Applicant} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted applicant.
     */
    public Applicant toModelType(ReadOnlyPositionBook positionBook) throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (positionApplyingTo == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Position.class.getSimpleName()));
        }
        final Title modelTitle = new Title(positionApplyingTo);
        final Position modelPosition = positionBook.getPositionList().stream()
                .filter(position -> position.hasTitle(modelTitle))
                .findFirst()
                .orElseThrow(() -> new IllegalValueException(
                        String.format(MISSING_FIELD_MESSAGE_FORMAT, Position.class.getSimpleName())));

        if (applicationStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ApplicationStatus.class.getSimpleName()));
        }
        final ApplicationStatus modelApplicationStatus =
                ApplicationStatus.fromString(applicationStatus);

        if (gitHubUrl == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ProfileUrl.class.getSimpleName()));
        }
        final ProfileUrl modelGitHubUrl = ProfileUrl.ofNullable(gitHubUrl);

        Applicant modelApplicant = new Applicant(modelName, modelPhone, modelEmail, modelAddress, modelPosition,
                modelGitHubUrl);
        return modelApplicant.markAs(modelApplicationStatus);
    }

}
