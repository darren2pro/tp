package seedu.address.logic.commands;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyApplicantBook;
import seedu.address.model.ReadOnlyPositionBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.ApplicantParticulars;
import seedu.address.model.applicant.Name;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;



/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
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
    public Applicant addApplicantWithParticulars(ApplicantParticulars particulars) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasApplicantWithName(Name name) {
        throw new AssertionError("This method should not be called.");
    }


    @Override
    public boolean hasApplicantsApplyingTo(Position position) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addApplicant(Applicant applicant) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Applicant getApplicantWithName(Name name) {
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
    public void deleteApplicant(Applicant target) {
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
    public ObservableList<Applicant> getFilteredApplicantList() {
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
    public Position getPositionWithTitle(Title title) {
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
        throw new AssertionError("This method should not be called.");
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
