package seedu.address.testutil;

import seedu.address.logic.descriptors.EditApplicantDescriptor;
import seedu.address.model.applicant.Address;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Application;
import seedu.address.model.applicant.Email;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.Phone;
import seedu.address.model.applicant.ProfileUrl;
import seedu.address.model.position.Title;

public class EditApplicantDescriptorBuilder {

    private EditApplicantDescriptor descriptor;

    public EditApplicantDescriptorBuilder() {
        this.descriptor = new EditApplicantDescriptor();
    }

    public EditApplicantDescriptorBuilder(EditApplicantDescriptor descriptor) {
        this.descriptor = new EditApplicantDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditApplicantDescriptor} with fields containing {@code applicant}'s details
     */
    public EditApplicantDescriptorBuilder(Applicant applicant) {
        descriptor = new EditApplicantDescriptor();
        descriptor.setName(applicant.getName());
        descriptor.setPhone(applicant.getPhone());
        descriptor.setEmail(applicant.getEmail());
        descriptor.setAddress(applicant.getAddress());
        descriptor.setApplication(applicant.getApplication());
        descriptor.setTitle(applicant.getTitle());
        descriptor.setGitHubProfile(applicant.getGitHubUrl());
    }

    /**
     * Sets the {@code Name} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withApplication(Application application) {
        descriptor.setApplication(application);
        return this;
    }

    /**
     * Sets the {@code Title} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code ProfileUrl} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withProfileUrl(String url) {
        descriptor.setGitHubProfile(new ProfileUrl(url));
        return this;
    }

    public EditApplicantDescriptor build() {
        return this.descriptor;
    }

}
