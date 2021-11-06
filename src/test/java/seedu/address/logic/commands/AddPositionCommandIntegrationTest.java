package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPositions.getTypicalPositionBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.position.Position;
import seedu.address.testutil.PositionBuilder;


/**
 * Contains integration tests (interaction with the Model) for {@code AddPositionCommand}.
 */
public class AddPositionCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPositionBook(), new UserPrefs());
    }

    @Test
    public void execute_newPosition_success() {
        Position validPosition = new PositionBuilder().build();

        Model expectedModel = new ModelManager(model.getPositionBook(), new UserPrefs());
        expectedModel.addPosition(validPosition);

        assertCommandSuccess(new AddPositionCommand(validPosition), model,
                String.format(AddPositionCommand.MESSAGE_SUCCESS, validPosition), expectedModel);
    }

    @Test
    public void execute_duplicatePosition_throwsCommandException() {
        Position positionInList = model.getPositionBook().getPositionList().get(0);
        assertCommandFailure(new AddPositionCommand(positionInList), model,
                AddPositionCommand.MESSAGE_DUPLICATE_POSITION);
    }

}
