package seedu.address.model.position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DATASCIENTIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DATAENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DATASCIENTIST;
import static seedu.address.testutil.TypicalApplicants.BOB;
import static seedu.address.testutil.TypicalPositions.DATAENGINEER;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PositionBuilder;


public class PositionTest {
    @Test
    public void isSamePosition() {
        // same object -> returns true
        assertTrue(DATAENGINEER.isSamePosition(DATAENGINEER));

        // null -> returns false
        assertFalse(DATAENGINEER.isSamePosition(null));

        // Same title, all other attributes different -> returns true
        Position editedDataEngineer = new PositionBuilder(DATAENGINEER)
                .withDescription(VALID_DESCRIPTION_DATASCIENTIST).build();
        assertTrue(DATAENGINEER.isSamePosition(editedDataEngineer));

        // title differs in case, other attributes same -> returns true
        editedDataEngineer = new PositionBuilder(DATAENGINEER)
                .withTitle(VALID_TITLE_DATAENGINEER.toLowerCase()).build();
        assertTrue(DATAENGINEER.isSamePosition(editedDataEngineer));

        // different title, all other attributes same -> returns false
        editedDataEngineer = new PositionBuilder(DATAENGINEER).withTitle(VALID_TITLE_DATASCIENTIST).build();
        assertFalse(DATAENGINEER.isSamePosition(editedDataEngineer));

        // title has trailing spaces, all other attributes same -> returns false
        String titleWithTrailingSpaces = VALID_TITLE_DATAENGINEER + " ";
        editedDataEngineer = new PositionBuilder(DATAENGINEER).withTitle(titleWithTrailingSpaces).build();
        assertFalse(DATAENGINEER.isSamePosition(editedDataEngineer));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Position dataEngineerCopy = new PositionBuilder(DATAENGINEER).build();
        assertEquals(DATAENGINEER, dataEngineerCopy);

        // same object -> returns true
        assertEquals(DATAENGINEER, DATAENGINEER);

        // null -> returns false
        assertNotEquals(null, DATAENGINEER);

        // different type -> returns false
        assertNotEquals(5, DATAENGINEER);

        // different applicant -> returns false
        assertNotEquals(DATAENGINEER, BOB);

        // different name -> returns false
        Position editedDataEngineer = new PositionBuilder(DATAENGINEER).withTitle(VALID_TITLE_DATASCIENTIST).build();
        assertNotEquals(DATAENGINEER, editedDataEngineer);

        // different description -> returns false
        editedDataEngineer = new PositionBuilder(DATAENGINEER).withDescription(VALID_DESCRIPTION_DATASCIENTIST).build();
        assertNotEquals(DATAENGINEER, editedDataEngineer);
    }

}
