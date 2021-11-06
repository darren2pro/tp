package seedu.address.logic.descriptors;

import static seedu.address.model.applicant.ApplicantMatchesFiltersPredicate.passesFilter;

import seedu.address.model.Model;

/**
 * A descriptor used to verify the validity of a FilterApplicantDescriptor.
 */
public class FilterApplicantDescriptorVerifier extends FilterApplicantDescriptor {

    /**
     * Constructor for a FilterApplicantDescriptorVerifier.
     * Verifies the validity of each filter in the specified {@code descriptor} against the given {@code model}.
     */
    public FilterApplicantDescriptorVerifier(Model model, FilterApplicantDescriptor descriptor) {
        super();

        if (!passesFilter(descriptor::getPositionTitle, model::hasPositionWithTitle)) {
            setPositionTitle(descriptor.getPositionTitle().get());
        }
    }

}
