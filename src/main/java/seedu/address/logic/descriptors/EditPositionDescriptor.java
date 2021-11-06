package seedu.address.logic.descriptors;

import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.position.Description;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

/**
 * Stores the details to edit the position with. Each non-empty field value will replace the
 * corresponding field value of the position.
 */
public class EditPositionDescriptor {
    private Title title;
    private Description description;

    public EditPositionDescriptor() {}

    /**
     * Copy constructor.
     */
    public EditPositionDescriptor(EditPositionDescriptor toCopy) {
        setTitle(toCopy.title);
        setDescription(toCopy.description);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(title, description);
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Optional<Title> getTitle() {
        return Optional.ofNullable(title);
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Optional<Description> getDescription() {
        return Optional.ofNullable(description);
    }

    /**
     * Creates and returns a {@code Position} with the details of {@code positionToEdit}
     * edited with {@code editPositionDescriptor}.
     */
    public Position createEditedPosition(Position positionToEdit) {
        assert positionToEdit != null;

        Title updatedTitle = getTitle().orElse(positionToEdit.getTitle());
        Description updatedDescription = getDescription().orElse(positionToEdit.getDescription());
        return new Position(updatedTitle, updatedDescription);
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPositionDescriptor)) {
            return false;
        }

        // state check
        EditPositionDescriptor e = (EditPositionDescriptor) other;

        return getTitle().equals(e.getTitle())
                && getDescription().equals(e.getDescription());
    }

}
