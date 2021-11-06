package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPositions.DATAENGINEER;
import static seedu.address.testutil.TypicalPositions.DATASCIENTIST;

import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.position.TitleContainsAllKeywordsPredicate;
import seedu.address.testutil.PositionBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setPositionBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setPositionBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }


    // tests for position and position books
    @Test
    public void hasPosition_nullPosition_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPosition(null));
    }

    @Test
    public void hasPosition_positionNotInPositionBook_returnsFalse() {
        assertFalse(modelManager.hasPosition(DATAENGINEER));
    }

    @Test
    public void hasPosition_positionInPositionBook_returnsTrue() {
        modelManager.addPosition(DATAENGINEER);
        assertTrue(modelManager.hasPosition(DATAENGINEER));
    }

    @Test
    public void getFilteredPositionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPositionList().remove(0));
    }

    @Test
    public void equals() {

        PositionBook positionBook = new PositionBookBuilder().withPosition(DATAENGINEER)
                .withPosition(DATASCIENTIST).build();
        PositionBook differentPositionBook = new PositionBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(positionBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(positionBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different positionBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentPositionBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = DATAENGINEER.getTitle().fullTitle.split("\\s+");
        modelManager.updateFilteredPositionList(new TitleContainsAllKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(positionBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPositionList(Model.PREDICATE_SHOW_ALL_POSITIONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setPositionBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(differentUserPrefs)));
    }
}
