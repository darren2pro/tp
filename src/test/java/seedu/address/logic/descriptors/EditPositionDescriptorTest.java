package seedu.address.logic.descriptors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DATA_ENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DATA_SCIENTIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DATASCIENTIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DATASCIENTIST;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditPositionDescriptorBuilder;


public class EditPositionDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditPositionDescriptor descriptorWithSameValues = new EditPositionDescriptor(DESC_DATA_ENGINEER);
        assertTrue(DESC_DATA_ENGINEER.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_DATA_ENGINEER.equals(DESC_DATA_ENGINEER));

        // null -> returns false
        assertFalse(DESC_DATA_ENGINEER.equals(null));

        // different types -> returns false
        assertFalse(DESC_DATA_ENGINEER.equals(5));

        // different values -> returns false
        assertFalse(DESC_DATA_ENGINEER.equals(DESC_DATA_SCIENTIST));

        // different title -> returns false
        EditPositionDescriptor editedDataEngineer = new EditPositionDescriptorBuilder(DESC_DATA_ENGINEER)
                .withTitle(VALID_TITLE_DATASCIENTIST).build();
        assertFalse(DESC_DATA_ENGINEER.equals(editedDataEngineer));

        // different title -> returns false
        editedDataEngineer = new EditPositionDescriptorBuilder(DESC_DATA_ENGINEER)
                .withDescription(VALID_DESCRIPTION_DATASCIENTIST).build();
        assertFalse(DESC_DATA_ENGINEER.equals(editedDataEngineer));


    }
}
