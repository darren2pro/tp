package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.position.Position;

/**
 * An UI component that displays information of a {@code Position}.
 */
public class PositionCard extends UiPart<Region> {

    private static final String FXML = "PositionListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4.</a>
     */

    public final Position position;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label description;

    /**
     * Creates a {@code PositionCard} with the given {@code Position} and index to display.
     */
    public PositionCard(Position position, int displayedIndex) {
        super(FXML);
        this.position = position;
        id.setText(displayedIndex + ". ");
        title.setText(position.getTitle().fullTitle);
        description.setText(position.getDescription().description);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PositionCard)) {
            return false;
        }

        // state check
        PositionCard card = (PositionCard) other;
        return id.getText().equals(card.id.getText())
                && position.equals(card.position);
    }
}
