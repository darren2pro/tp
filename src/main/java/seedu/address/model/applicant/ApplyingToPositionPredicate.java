package seedu.address.model.applicant;

import java.util.function.Predicate;

import seedu.address.model.position.Position;

/**
 * Tests that an {@code Applicant} is applying to the specified {@code Position}.
 */
public class ApplyingToPositionPredicate implements Predicate<Applicant> {

    private final Position position;

    public ApplyingToPositionPredicate(Position position) {
        this.position = position;
    }

    @Override
    public boolean test(Applicant applicant) {
        return applicant.isApplyingTo(position);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ApplyingToPositionPredicate // instanceof handles nulls
                && position.equals(((ApplyingToPositionPredicate) other).position)); // state check
    }

}
