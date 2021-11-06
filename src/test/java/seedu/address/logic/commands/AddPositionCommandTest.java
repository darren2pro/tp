package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.position.Position;
import seedu.address.testutil.PositionBuilder;


public class AddPositionCommandTest {
    @Test
    public void constructor_nullPosition_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPositionCommand(null));
    }

    @Test
    public void execute_positionAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPositionAdded modelStub = new ModelStubAcceptingPositionAdded();
        Position validPosition = new PositionBuilder().build();

        CommandResult commandResult = new AddPositionCommand(validPosition).execute(modelStub);

        assertEquals(String.format(AddPositionCommand.MESSAGE_SUCCESS, validPosition),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPosition), modelStub.positionsAdded);
    }

    @Test
    public void execute_duplicatePosition_throwsCommandException() {
        Position validPosition = new PositionBuilder().build();
        AddPositionCommand addPositionCommand = new AddPositionCommand(validPosition);
        ModelStub modelStub = new ModelStubWithPosition(validPosition);

        assertThrows(CommandException.class, AddPositionCommand.MESSAGE_DUPLICATE_POSITION, ()
            -> addPositionCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Position dataEngineer = new PositionBuilder().withTitle("Data Engineer").build();
        Position dataScientist = new PositionBuilder().withTitle("Data Scientist").build();
        AddPositionCommand addDataEngineerCommand = new AddPositionCommand(dataEngineer);
        AddPositionCommand addDataScientistCommand = new AddPositionCommand(dataScientist);

        // same object -> returns true
        assertTrue(addDataEngineerCommand.equals(addDataEngineerCommand));

        // same values -> returns true
        AddPositionCommand addDataEngineerCommandCopy = new AddPositionCommand(dataEngineer);
        assertTrue(addDataEngineerCommand.equals(addDataEngineerCommandCopy));

        // different types -> returns false
        assertFalse(addDataEngineerCommand.equals(1));

        // null -> returns false
        assertFalse(addDataEngineerCommand.equals(null));

        // different position -> returns false
        assertFalse(addDataEngineerCommand.equals(addDataScientistCommand));
    }


    /**

     * A Model stub that contains a single position.
     */
    private class ModelStubWithPosition extends ModelStub {
        private final Position position;

        ModelStubWithPosition(Position position) {
            requireNonNull(position);
            this.position = position;
        }

        @Override
        public boolean hasPosition(Position position) {
            requireNonNull(position);
            return this.position.isSamePosition(position);
        }
    }

    /**
     * A Model stub that always accept the position being added.
     */
    private class ModelStubAcceptingPositionAdded extends ModelStub {
        final ArrayList<Position> positionsAdded = new ArrayList<>();

        @Override
        public boolean hasPosition(Position position) {
            requireNonNull(position);
            return positionsAdded.stream().anyMatch(position::isSamePosition);
        }

        @Override
        public void addPosition(Position position) {
            requireNonNull(position);
            positionsAdded.add(position);
        }

        @Override
        public Model getCopiedModel() {
            return this;
        }

        @Override
        public void addToHistory(Command command) {}

    }
}
