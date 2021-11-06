package seedu.address.storage.position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.PositionBook;
import seedu.address.model.ReadOnlyPositionBook;
import seedu.address.model.position.Position;

/**
 * An Immutable PositionBook that is serializable to JSON format.
 */
@JsonRootName(value = "positionbook")
class JsonSerializablePositionBook {

    public static final String MESSAGE_DUPLICATE_POSITION = "Positions list contains duplicate position(s).";

    private final List<JsonAdaptedPosition> positions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePositionBook} with the given positions.
     */
    @JsonCreator
    public JsonSerializablePositionBook(@JsonProperty("positions") List<JsonAdaptedPosition> positions) {
        this.positions.addAll(positions);
    }

    /**
     * Converts a given {@code ReadOnlyPositionBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePositionBook}.
     */
    public JsonSerializablePositionBook(ReadOnlyPositionBook source) {
        positions.addAll(source.getPositionList().stream().map(JsonAdaptedPosition::new).collect(Collectors.toList()));
    }

    /**
     * Converts this position book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PositionBook toModelType() throws IllegalValueException {
        PositionBook positionBook = new PositionBook();
        for (JsonAdaptedPosition jsonAdaptedPosition : positions) {
            Position position = jsonAdaptedPosition.toModelType();
            if (positionBook.hasPosition(position)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_POSITION);
            }
            positionBook.addPosition(position);
        }
        return positionBook;
    }

}
