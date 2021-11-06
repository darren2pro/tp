package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DATA_ENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DATA_SCIENTIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DATASCIENTIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DATASCIENTIST;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPositionAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_POSITION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_POSITION;
import static seedu.address.testutil.TypicalPositions.getTypicalPositionBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.descriptors.EditPositionDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PositionBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.position.Position;
import seedu.address.testutil.EditPositionDescriptorBuilder;
import seedu.address.testutil.PositionBuilder;


public class EditPositionCommandTest {

    private Model model = new ModelManager(getTypicalPositionBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Position editedPosition = new PositionBuilder().build();
        EditPositionDescriptor descriptor = new EditPositionDescriptorBuilder(editedPosition).build();
        EditPositionCommand editPositionCommand = new EditPositionCommand(INDEX_FIRST_POSITION, descriptor);

        String expectedMessage = String.format(EditPositionCommand.MESSAGE_EDIT_POSITION_SUCCESS, editedPosition);

        Model expectedModel = new ModelManager(new PositionBook(model.getPositionBook()), new UserPrefs());
        expectedModel.setPosition(model.getFilteredPositionList().get(0), editedPosition);

        assertCommandSuccess(editPositionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPosition = Index.fromOneBased(model.getFilteredPositionList().size());
        Position lastPosition = model.getFilteredPositionList().get(indexLastPosition.getZeroBased());

        PositionBuilder positionInList = new PositionBuilder(lastPosition);
        Position editedPosition = positionInList.withTitle(VALID_TITLE_DATASCIENTIST)
                .withDescription(VALID_DESCRIPTION_DATASCIENTIST).build();

        EditPositionDescriptor descriptor = new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_DATASCIENTIST)
                .withDescription(VALID_DESCRIPTION_DATASCIENTIST).build();
        EditPositionCommand editPositionCommand = new EditPositionCommand(indexLastPosition, descriptor);

        String expectedMessage = String.format(EditPositionCommand.MESSAGE_EDIT_POSITION_SUCCESS, editedPosition);

        Model expectedModel = new ModelManager(new PositionBook(model.getPositionBook()), new UserPrefs());
        expectedModel.setPosition(lastPosition, editedPosition);

        assertCommandSuccess(editPositionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditPositionCommand editPositionCommand = new EditPositionCommand(INDEX_FIRST_POSITION,
                new EditPositionDescriptor());
        Position editedPosition = model.getFilteredPositionList().get(INDEX_FIRST_POSITION.getZeroBased());

        String expectedMessage = String.format(EditPositionCommand.MESSAGE_EDIT_POSITION_SUCCESS, editedPosition);

        Model expectedModel = new ModelManager(new PositionBook(model.getPositionBook()), new UserPrefs());

        assertCommandSuccess(editPositionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPositionAtIndex(model, INDEX_FIRST_POSITION);

        Position positionInFilteredList = model.getFilteredPositionList().get(INDEX_FIRST_POSITION.getZeroBased());
        Position editedPosition = new PositionBuilder(positionInFilteredList)
                .withDescription(VALID_TITLE_DATASCIENTIST).build();
        EditPositionCommand editPositionCommand = new EditPositionCommand(INDEX_FIRST_POSITION,
                new EditPositionDescriptorBuilder().withDescription(VALID_TITLE_DATASCIENTIST).build());

        String expectedMessage = String.format(EditPositionCommand.MESSAGE_EDIT_POSITION_SUCCESS, editedPosition);

        Model expectedModel = new ModelManager(new PositionBook(model.getPositionBook()), new UserPrefs());
        expectedModel.setPosition(model.getFilteredPositionList().get(0), editedPosition);

        assertCommandSuccess(editPositionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePositionUnfilteredList_failure() {
        Position firstPosition = model.getFilteredPositionList().get(INDEX_FIRST_POSITION.getZeroBased());
        EditPositionDescriptor descriptor = new EditPositionDescriptorBuilder(firstPosition).build();
        EditPositionCommand editPositionCommand = new EditPositionCommand(INDEX_SECOND_POSITION, descriptor);

        assertCommandFailure(editPositionCommand, model, EditPositionCommand.MESSAGE_DUPLICATE_POSITION);
    }

    @Test
    public void execute_duplicatePositionFilteredList_failure() {
        showPositionAtIndex(model, INDEX_FIRST_POSITION);

        // edit person in filtered list into a duplicate in address book
        Position positionInList = model.getPositionBook().getPositionList().get(INDEX_SECOND_POSITION.getZeroBased());
        EditPositionCommand editPositionCommand = new EditPositionCommand(INDEX_FIRST_POSITION,
                new EditPositionDescriptorBuilder(positionInList).build());

        assertCommandFailure(editPositionCommand, model, EditPositionCommand.MESSAGE_DUPLICATE_POSITION);
    }

    @Test
    public void execute_invalidPositionIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPositionList().size() + 1);
        EditPositionDescriptor descriptor = new EditPositionDescriptorBuilder()
                .withTitle(VALID_TITLE_DATASCIENTIST).build();
        EditPositionCommand editPositionCommand = new EditPositionCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editPositionCommand, model, Messages.MESSAGE_INVALID_POSITION_DISPLAYED_INDEX);
    }


    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPositionIndexFilteredList_failure() {
        showPositionAtIndex(model, INDEX_FIRST_POSITION);
        Index outOfBoundIndex = INDEX_SECOND_POSITION;
        // ensures that outOfBoundIndex is still in bounds of position book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPositionBook().getPositionList().size());

        EditPositionCommand editPositionCommand = new EditPositionCommand(outOfBoundIndex,
                new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_DATASCIENTIST).build());

        assertCommandFailure(editPositionCommand, model, Messages.MESSAGE_INVALID_POSITION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditPositionCommand standardCommand = new EditPositionCommand(INDEX_FIRST_POSITION, DESC_DATA_ENGINEER);

        // same values -> returns true
        EditPositionDescriptor copyDescriptor = new EditPositionDescriptor(DESC_DATA_ENGINEER);
        EditPositionCommand commandWithSameValues = new EditPositionCommand(INDEX_FIRST_POSITION, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditPositionCommand(INDEX_SECOND_POSITION, DESC_DATA_ENGINEER)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditPositionCommand(INDEX_FIRST_POSITION, DESC_DATA_SCIENTIST)));
    }
}
