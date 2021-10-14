package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Position> PREDICATE_SHOW_ALL_POSITIONS = unused -> true;
    Predicate<Applicant> PREDICATE_SHOW_ALL_APPLICANTS = unused -> true;

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

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' position book file path.
     */
    Path getPositionBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Deletes the given applicant.
     * The applicant must exist in the address book.
     */
    void deleteApplicant(Applicant target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Adds the given applicant to the given position.
     * {@code applicant} must not already exist in the applicant book.
     */
    void addApplicantToPosition(Applicant applicant, Position position);

    /**
     * Returns true if an applicant with the same identity as {@code applicant} exists MrTechRecruiter.
     */
    boolean hasApplicant(Applicant applicant);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Replaces the given applicant {@code target} with {@code editedApplicant}.
     * {@code target} must exist in MrTechRecruiter.
     * The applicant identity of {@code editedApplicant} must not be the same as another existing person in
     * MrTechRecruiter.
     */
    public void setApplicant(Applicant target, Applicant editedApplicant);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered applicant list */
    ObservableList<Applicant> getFilteredApplicantList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    void updateFilteredApplicantList(Predicate<Applicant> predicateShowAllApplicants);

    // Position related methods
    boolean hasPosition(Position toAdd);

    void addPosition(Position toAdd);

    void deletePosition(Position positionToDelete);

    //=========== Filtered Position List Accessors =============================================================
    ObservableList<Position> getFilteredPositionList();

    void updateFilteredPositionList(Predicate<Position> predicate);

    // Applicant related methods ==============================================================================
    Path getApplicantBookFilePath();
}
