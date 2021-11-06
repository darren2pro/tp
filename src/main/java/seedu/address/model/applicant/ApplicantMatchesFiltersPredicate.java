package seedu.address.model.applicant;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import seedu.address.logic.descriptors.FilterApplicantDescriptor;

/**
 * Tests that a {@code Applicant}'s {@code Name} matches the filters specified
 * in the given {@code filterApplicantDescriptor.}.
 */
public class ApplicantMatchesFiltersPredicate implements Predicate<Applicant> {

    private final FilterApplicantDescriptor descriptor;

    /**
     * Constructor for a ApplicantMatchesFiltersPredicate object.
     */
    public ApplicantMatchesFiltersPredicate(FilterApplicantDescriptor descriptor) {
        requireNonNull(descriptor);

        this.descriptor = descriptor;
    }

    @Override
    public boolean test(Applicant applicant) {
        return applicantMatchesFilters(applicant);
    }

    /**
     * Returns true if the given Applicant passes the specified filters.
     */
    public boolean applicantMatchesFilters(Applicant applicant) {
        return passesFilter(descriptor::getPositionTitle, applicant::isApplyingToPositionWithTitle)
                && passesFilter(descriptor::getApplicationStatus, applicant::hasApplicationStatus);
    }

    /**
     * Convenience method to test if an optional object passes a specified filter.
     * If the object is not present (i.e. Optional.empty), the filter is passed by default.
     */
    public static <T> boolean passesFilter(Supplier<Optional<T>> toTest, Function<T, Boolean> filter) {
        return toTest.get().map(filter).orElse(true);
    }

}
