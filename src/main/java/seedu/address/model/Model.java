package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.Command;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.ApplicantParticulars;
import seedu.address.model.applicant.Name;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Position> PREDICATE_SHOW_ALL_POSITIONS = unused -> true;
    Predicate<Applicant> PREDICATE_SHOW_ALL_APPLICANTS = unused -> true;

    //=========== User prefs related methods =============================================================

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    //=========== Position related methods =============================================================

    /**
     * Replaces the given position {@code target} with {@code editedPosition}.
     * {@code target} must exist in MrTechRecruiter.
     * The position identity of {@code editedPosition} must not be the same as another existing position in
     * MrTechRecruiter.
     */
    void setPosition(Position position, Position editedPosition);

    /**
     * Returns true if a position with the same identity as {@code position} exists in the position book
     */
    boolean hasPosition(Position position);

    /**
     * Returns true if a position with {@code title} exists in the position book
     */
    boolean hasPositionWithTitle(Title title);

    /**
     * Returns the position with {@code title}
     */
    Position getPositionWithTitle(Title title);

    /** Returns an unmodifiable view of the filtered applicant list */
    ObservableList<Applicant> getFilteredApplicantList();

    /**
     * Returns an unmodifiable view of the filtered position list
     */
    ObservableList<Position> getFilteredPositionList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     * Adds the given position.
     * {@code position} must not already exist in the position book.
     */
    void addPosition(Position position);

    /**
     * Deletes the given position.
     * The position must exist in the position book.
     */
    void deletePosition(Position position);

    /**
     * Returns the user prefs' position book file path.
     */
    Path getPositionBookFilePath();

    /**
     * Replaces position book data with the data in {@code positionBook}.
     */
    void setPositionBook(ReadOnlyPositionBook positionBook);

    /**
     * Returns the PositionBook
     */
    ReadOnlyPositionBook getPositionBook();

    /**
     * Updates the filter of the filtered position list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPositionList(Predicate<Position> predicate);

    //=========== Applicant related methods =============================================================

    /**
     * Replaces the given applicant {@code target} with {@code editedApplicant}.
     * {@code target} must exist in MrTechRecruiter.
     * The applicant identity of {@code editedApplicant} must not be the same as another existing person in
     * MrTechRecruiter.
     */
    void setApplicant(Applicant target, Applicant editedApplicant);

    /**
     * Adds the given applicant.
     * {@code applicant} must not already exist in the applicant book.
     */
    void addApplicant(Applicant applicant);

    /**
     * Adds a new applicant to MrTechRecruiter with the given particulars.
     * The intended applicant must not already exist in the applicant book.
     *
     * @return the newly added applicant.
     */
    Applicant addApplicantWithParticulars(ApplicantParticulars applicantParticulars);

    /**
     * Returns true if {@code applicant} exists in MrTechRecruiter.
     */
    boolean hasApplicant(Applicant applicant);

    /**
     * Returns true if an applicant named {@code applicantName} exists in MrTechRecruiter.
     */
    boolean hasApplicantWithName(Name applicantName);

    /**
     * Returns true if MrTechRecruiter has applicants applying to
     * the specified position.
     */
    boolean hasApplicantsApplyingTo(Position position);

    /**
     * Returns the applicant with the specified name, if any.
     */
    Applicant getApplicantWithName(Name name);

    /**
     * Deletes the given applicant.
     * The applicant must exist in the address book.
     */
    void deleteApplicant(Applicant target);

    Path getApplicantBookFilePath();

    /**
     * Calculates the rejection rate of a given position.
     * @param title The title of the position to be calculated.
     * @return The rejection rate of the specified position.
     */
    float calculateRejectionRate(Title title);

    /**
     * Replaces position book data with the data in {@code positionBook}.
     */
    void setApplicantBook(ReadOnlyApplicantBook applicantBook);

    /** Returns the ApplicantBook */
    ReadOnlyApplicantBook getApplicantBook();

    /**
     * Updates the filter of the filtered position list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredApplicantList(Predicate<Applicant> predicateShowAllApplicants);

    void updateApplicantsWithPosition(Position positionToEdit, Position editedPosition);


    /**
     * Returns a deep-copied model.
     */
    Model getCopiedModel();

    /**
     * Records the modification history.
     */
    void addToHistory(Command command);

    /**
     * Returns true if there exists history to recover.
     */
    boolean hasHistory();

    /**
     * Undoes the previous modification.
     */
    String recoverHistory();

}
