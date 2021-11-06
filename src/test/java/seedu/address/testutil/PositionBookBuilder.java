package seedu.address.testutil;

import seedu.address.model.PositionBook;
import seedu.address.model.position.Position;

/**
 * A utility class to help with building PositionBook objects.
 * Example usage: <br>
 *     {@code PositionBook pb = new PositionBookBuilder().withPosition(DATAENGINEER).build();}
 */
public class PositionBookBuilder {

    private PositionBook positionBook;

    public PositionBookBuilder() {
        positionBook = new PositionBook();
    }

    public PositionBookBuilder(PositionBook positionBook) {
        this.positionBook = positionBook;
    }

    /**
     * Adds a new {@code Position} to the {@code PositionBook} that we are building.
     */
    public PositionBookBuilder withPosition(Position position) {
        positionBook.addPosition(position);
        return this;
    }

    public PositionBook build() {
        return positionBook;
    }

}
