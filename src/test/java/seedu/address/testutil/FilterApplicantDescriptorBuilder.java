package seedu.address.testutil;

import static seedu.address.model.applicant.Application.ApplicationStatus;

import seedu.address.logic.descriptors.FilterApplicantDescriptor;
import seedu.address.model.position.Title;

/**
 * A utility class to help with building FilterApplicantDescriptor objects.
 */
public class FilterApplicantDescriptorBuilder {

    private FilterApplicantDescriptor descriptor;

    public FilterApplicantDescriptorBuilder() {
        descriptor = new FilterApplicantDescriptor();
    }

    public FilterApplicantDescriptorBuilder(FilterApplicantDescriptor descriptor) {
        this.descriptor = new FilterApplicantDescriptor(descriptor);
    }

    /**
     * Sets the position {@code Title} of the {@code FilterApplicantDescriptor} that we are building.
     */
    public FilterApplicantDescriptorBuilder withPositionTitle(String title) {
        descriptor.setPositionTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code ApplicationStatus} of the {@code FilterApplicantDescriptor} that we are building.
     */
    public FilterApplicantDescriptorBuilder withApplicationStatus(ApplicationStatus applicationStatus) {
        descriptor.setApplicationStatus(applicationStatus);
        return this;
    }

    public FilterApplicantDescriptor build() {
        return descriptor;
    }
}
