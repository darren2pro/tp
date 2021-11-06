package seedu.address.testutil;

import seedu.address.model.applicant.Address;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.ApplicantParticulars;
import seedu.address.model.applicant.Email;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.Phone;
import seedu.address.model.applicant.ProfileUrl;
import seedu.address.model.position.Position;


public class ApplicantBuilder {
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final Position DEFAULT_POSITION = new PositionBuilder().build();
    private static final ProfileUrl DEFAULT_GITHUB_PROFILE = ProfileUrl.ofNullable("https://github.com/empty");

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Position position;
    private ProfileUrl gitHubProfile;

    /**
     * Creates a {@code ApplicantBuilder} with the default details.
     */
    public ApplicantBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        position = DEFAULT_POSITION;
        gitHubProfile = DEFAULT_GITHUB_PROFILE;
    }

    /**
     * Initializes the ApplicantBuilder with the data of {@code applicantToCopy}.
     */
    public ApplicantBuilder(Applicant applicantToCopy) {
        name = applicantToCopy.getName();
        phone = applicantToCopy.getPhone();
        email = applicantToCopy.getEmail();
        address = applicantToCopy.getAddress();
        position = applicantToCopy.getApplication().getPosition();
        gitHubProfile = applicantToCopy.getGitHubUrl();
    }

    /**
     * Sets the {@code Name} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Position} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withPosition(Position position) {
        this.position = position;
        return this;
    }

    /**
     * Sets the {@code gitHubProfile} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withGitHubProfile(String githubUrl) {
        this.gitHubProfile = ProfileUrl.ofNullable(githubUrl);
        return this;
    }

    public ApplicantParticulars getParticulars() {
        return new ApplicantParticulars(name, phone, email, address, position.getTitle(), gitHubProfile);
    }

    public Applicant build() {
        return new Applicant(name, phone, email, address, position, gitHubProfile);
    }
}
