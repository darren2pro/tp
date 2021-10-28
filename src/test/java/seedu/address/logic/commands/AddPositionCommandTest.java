package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyApplicantBook;
import seedu.address.model.ReadOnlyPositionBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.ApplicantParticulars;
import seedu.address.model.applicant.Name;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;
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
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Applicant addApplicantWithParticulars(ApplicantParticulars particulars) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasApplicantWithName(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Applicant getApplicantByNameIgnoreCase(Name applicantName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addApplicant(Applicant applicant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getApplicantBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getPositionBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteApplicant(Applicant target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplicant(Applicant target, Applicant editedApplicant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasApplicant(Applicant applicant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPosition(Position target, Position editedPosition) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Applicant> getFilteredApplicantList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredApplicantList(Predicate<Applicant> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPositionBook(ReadOnlyPositionBook positionBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyPositionBook getPositionBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPosition(Position toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPositionWithTitle(Title title) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Position getPositionByTitle(Title title) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPosition(Position toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePosition(Position positionToDelete) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Position> getFilteredPositionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPositionList(Predicate<Position> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public float calculateRejectionRate(Title p) {
            throw new AssertionError("This method should not be called.");
        }

        public void updateApplicantsWithPosition(Position positionToEdit, Position newPosition) {
            throw new AssertionError("This method should not be called.");
        }

        public void setApplicantBook(ReadOnlyApplicantBook applicantBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyApplicantBook getApplicantBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Model getCopiedModel() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addToHistory(Command command) {
            return;
        }

        @Override
        public boolean hasHistory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String recoverHistory() {
            throw new AssertionError("This method should not be called.");
        }
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
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public Model getCopiedModel() {
            return this;
        }

        @Override
        public void addToHistory(Command command) {
            return;
        }


        @Override
        public boolean hasHistory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String recoverHistory() {
            throw new AssertionError("This method should not be called.");
        }
    }
}
