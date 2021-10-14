package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.application.Application;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final PositionBook positionBook;
    private final ApplicantBook applicantBook;
    private final ApplicationBook applicationBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Applicant> filteredApplicants;
    private final FilteredList<Position> filteredPositions;

    /**
     * Initializes a ModelManager with the given positionBook, applicantBook, applicationBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyApplicantBook applicantBook,
                        ReadOnlyPositionBook positionBook, ApplicationBook applicationBook,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, applicantBook, positionBook, applicationBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook
                + ", applicant book: " + applicantBook
                + ", position book: " + positionBook
                + ", application book: " + applicationBook
                + ", userPrefs: " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.positionBook = new PositionBook(positionBook);
        this.applicantBook = new ApplicantBook(applicantBook);
        this.applicationBook = new ApplicationBook(applicationBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredApplicants = new FilteredList<>(this.applicantBook.getApplicantList());
        filteredPositions = new FilteredList<>(this.positionBook.getPositionList());
    }

    /**
     * Old constructor - left temporarily to pass unit tests
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.positionBook = new PositionBook();
        this.applicantBook = new ApplicantBook();
        this.applicationBook = new ApplicationBook();
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredApplicants = new FilteredList<>(this.applicantBook.getApplicantList());
        filteredPositions = new FilteredList<>(this.positionBook.getPositionList());
    }

    public ModelManager() {
        this(new AddressBook(), new ApplicantBook(), new PositionBook(), new ApplicationBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public Path getPositionBookFilePath() {
        return userPrefs.getPositionBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void deleteApplicant(Applicant target) {
        applicantBook.removeApplicant(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addApplicantToPosition(Applicant applicant, Position dummyPosition) {
        Position position = positionBook.getPosition(dummyPosition);
        Application application = new Application(applicant, position);

        // Sets the application of the applicant to the application with original position object
        applicant.setApplication(application);

        applicantBook.addApplicant(applicant);
        applicationBook.addApplication(application);
        updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
    }

    @Override
    public boolean hasApplicant(Applicant applicant) {
        requireNonNull(applicant);
        return applicantBook.hasApplicant(applicant);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public void setApplicant(Applicant target, Applicant editedApplicant) {
        requireAllNonNull(target, editedApplicant);
        applicantBook.setApplicant(target, editedApplicant);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public ObservableList<Applicant> getFilteredApplicantList() {
        return filteredApplicants;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredApplicantList(Predicate<Applicant> predicateShowAllApplicants) {
        requireNonNull(predicateShowAllApplicants);
        filteredApplicants.setPredicate(predicateShowAllApplicants);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

    // Position related methods
    @Override
    public boolean hasPosition(Position position) {
        requireNonNull(position);
        return positionBook.hasPosition(position);
    }

    @Override
    public void addPosition(Position position) {
        positionBook.addPosition(position);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void deletePosition(Position positionToDelete) {
        positionBook.removePosition(positionToDelete);
    }


    //=========== Filtered Position List Accessors =============================================================
    @Override
    public ObservableList<Position> getFilteredPositionList() {
        return filteredPositions;
    }

    @Override
    public void updateFilteredPositionList(Predicate<Position> predicate) {
        requireNonNull(predicate);
        filteredPositions.setPredicate(predicate);
    }

    //=========== Filtered Applicant List Accessors =============================================================
    @Override
    public Path getApplicantBookFilePath() {
        return userPrefs.getApplicantBookFilePath();
    }

}
