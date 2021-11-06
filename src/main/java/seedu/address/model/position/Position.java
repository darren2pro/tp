package seedu.address.model.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Position in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Position {

    // Identity fields
    private final Title title;

    private final Description description;

    /**
     * Every field must be present and not null.
     */
    public Position(Title title, Description description) {
        requireAllNonNull(title, description);
        this.title = title;
        this.description = description;
    }

    /**
     * Returns true if this position has the specified title.
     */
    public boolean hasTitle(Title title) {
        requireNonNull(title);
        return this.title.equals(title);
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns true if both positions have the same title.
     * This defines a weaker notion of equality between two positions.
     */
    public boolean isSamePosition(Position otherPosition) {
        if (otherPosition == this) {
            return true;
        }

        return otherPosition != null
                && otherPosition.getTitle().equals(getTitle());
    }

    /**
     * Returns true if both positions have the same identity and data fields.
     * This defines a stronger notion of equality between two positions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Position)) {
            return false;
        }

        Position otherPosition = (Position) other;
        return otherPosition.getTitle().equals(getTitle())
                && otherPosition.getDescription().equals(getDescription());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing
        return Objects.hash(title, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Description: ")
                .append(getDescription());

        return builder.toString();
    }

    public Position getCopiedPosition() {
        return new Position(title.getCopiedTitle(), description.getCopiedDescription());
    }
}
