package seedu.address.logic.descriptors;

import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.applicant.Application;
import seedu.address.model.applicant.Application.ApplicationStatus;
import seedu.address.model.position.Position;

public class EditApplicationDescriptor {
    private Position position;
    private ApplicationStatus status;


    public EditApplicationDescriptor() {}

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(position, status);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Optional<Position> getPosition() {
        return Optional.ofNullable(position);
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public Optional<ApplicationStatus> getStatus() {
        return Optional.ofNullable(status);
    }

    /**
     * Creates and returns a {@code Application} with the details of {@code applicationToEdit}
     * edited with {@code editApplicationDescriptor}.
     */

    public Application createEditedApplication(Application applicationToEdit) {
        assert applicationToEdit != null;
        Position updatedPosition = getPosition().orElse(applicationToEdit.getPosition());
        ApplicationStatus updatedStatus = getStatus().orElse(applicationToEdit.getStatus());

        return new Application(updatedPosition, updatedStatus);
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditApplicationDescriptor)) {
            return false;
        }

        // state check
        EditApplicationDescriptor e = (EditApplicationDescriptor) other;

        return getPosition().equals(e.getPosition())
                && getStatus().equals(e.getStatus());
    }

}
