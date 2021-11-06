package seedu.address.model.applicant;

import seedu.address.model.position.Title;

/**
 * A read-only utility class used to hold an applicant's particulars.
 */
public class ApplicantParticulars {
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Address address;
    private final Title positionTitle;
    private ProfileUrl gitHubUrl;

    /**
     * Constructor for an ApplicantParticulars object.
     */
    public ApplicantParticulars(Name name, Phone phone, Email email, Address address, Title positionTitle,
                                ProfileUrl gitHubUrl) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.positionTitle = positionTitle;
        this.gitHubUrl = gitHubUrl;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Title getPositionTitle() {
        return positionTitle;
    }

    public ProfileUrl getGitHubUrl() {
        return gitHubUrl;
    }

}
