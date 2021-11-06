package seedu.address.testutil;

import seedu.address.model.position.Description;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

/**
 * A utility class to help with building Position objects.
 */
public class PositionBuilder {
    public static final String DEFAULT_TITLE = "Software Engineer";
    public static final String DEFAULT_DESCRIPTION = "code every day";

    private Title title;
    private Description description;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PositionBuilder() {
        title = new Title(DEFAULT_TITLE);
        description = new Description(DEFAULT_DESCRIPTION);
    }

    /**
     * Initializes the PositionBuilder with the data of {@code positionToCopy}.
     */
    public PositionBuilder(Position positionToCopy) {
        title = positionToCopy.getTitle();
        description = positionToCopy.getDescription();
    }

    /**
     * Sets the {@code Title} of the {@code Position} that we are building.
     */
    public PositionBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Position} that we are building.
     */
    public PositionBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    public Position build() {
        return new Position(title, description);
    }

    public Title getTitle() {
        return title;
    }
}
