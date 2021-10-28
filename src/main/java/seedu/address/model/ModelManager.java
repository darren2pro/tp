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
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.memento.History;
import seedu.address.logic.commands.memento.Memento;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.ApplicantParticulars;
import seedu.address.model.applicant.Application.ApplicationStatus;
import seedu.address.model.applicant.Name;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final PositionBook positionBook;
    private final ApplicantBook applicantBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Applicant> filteredApplicants;
    private final FilteredList<Position> filteredPositions;
    private final History history;

    /**
     * Initializes a ModelManager with the given positionBook, applicantBook, applicationBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyApplicantBook applicantBook,
                        ReadOnlyPositionBook positionBook,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, applicantBook, positionBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook
                + ", applicant book: " + applicantBook
                + ", position book: " + positionBook
                + ", userPrefs: " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.positionBook = new PositionBook(positionBook);
        this.applicantBook = new ApplicantBook(applicantBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredApplicants = new FilteredList<>(this.applicantBook.getApplicantList());
        filteredPositions = new FilteredList<>(this.positionBook.getPositionList());
        history = new History();
    }

    /**
     * Initializes a ModelManager with the given positionBook, applicantBook, applicationBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyApplicantBook applicantBook,
                        ReadOnlyPositionBook positionBook,
                        ReadOnlyUserPrefs userPrefs, History changeHistory) {
        super();
        requireAllNonNull(addressBook, applicantBook, positionBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook
                + ", applicant book: " + applicantBook
                + ", position book: " + positionBook
                + ", userPrefs: " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.positionBook = new PositionBook(positionBook);
        this.applicantBook = new ApplicantBook(applicantBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredApplicants = new FilteredList<>(this.applicantBook.getApplicantList());
        filteredPositions = new FilteredList<>(this.positionBook.getPositionList());
        this.history = changeHistory;
    }


    /**
     * Old constructor - left temporarily to pass unit tests
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook
                + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.positionBook = new PositionBook();
        this.applicantBook = new ApplicantBook();
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredApplicants = new FilteredList<>(this.applicantBook.getApplicantList());
        filteredPositions = new FilteredList<>(this.positionBook.getPositionList());
        history = new History();
    }

    /**
     * Left temporarily to pass unit tests
     * Initializes a ModelManager with the given positionBook and userPrefs.
     */
    public ModelManager(ReadOnlyPositionBook positionBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(positionBook, userPrefs);

        logger.fine("Initializing with position book: " + positionBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook();
        this.positionBook = new PositionBook(positionBook);
        this.applicantBook = new ApplicantBook();
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredApplicants = new FilteredList<>(this.applicantBook.getApplicantList());
        filteredPositions = new FilteredList<>(this.positionBook.getPositionList());
        history = new History();
    }

    /**
     * Old constructor - left temporarily to pass unit tests.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyPositionBook positionBook,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(positionBook, userPrefs);

        logger.fine("Initializing with position book: " + positionBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.positionBook = new PositionBook(positionBook);
        this.applicantBook = new ApplicantBook();
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredApplicants = new FilteredList<>(this.applicantBook.getApplicantList());
        filteredPositions = new FilteredList<>(this.positionBook.getPositionList());
        history = new History();
    }

    /**
     * Old constructor - left temporarily to pass unit tests.
     */
    public ModelManager(ReadOnlyPositionBook positionBook, ReadOnlyApplicantBook applicantBook,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(positionBook, userPrefs);

        logger.fine("Initializing with position book: " + positionBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook();
        this.positionBook = new PositionBook(positionBook);
        this.applicantBook = new ApplicantBook(applicantBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredApplicants = new FilteredList<>(this.applicantBook.getApplicantList());
        filteredPositions = new FilteredList<>(this.positionBook.getPositionList());
        history = new History();
    }


    public ModelManager() {
        this(new AddressBook(), new ApplicantBook(), new PositionBook(), new UserPrefs());
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

    //=========== AddressBook ================================================================================

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

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
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
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
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    // TODO: Update equals()
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

    //=========== Position and PositionBook =========================================================================

    @Override
    public void setPosition(Position target, Position editedPosition) {
        requireAllNonNull(target, editedPosition);
        positionBook.setPosition(target, editedPosition);
    }

    @Override
    public Path getPositionBookFilePath() {
        return userPrefs.getPositionBookFilePath();
    }

    @Override
    public boolean hasPosition(Position position) {
        requireNonNull(position);
        return positionBook.hasPosition(position);
    }

    @Override
    public boolean hasPositionWithTitle(Title title) {
        requireNonNull(title);
        return positionBook.hasPositionWithTitle(title);
    }

    @Override
    public Position getPositionByTitle(Title title) {
        return positionBook.getPositionByTitle(title);
    }

    @Override
    public void addPosition(Position position) {
        positionBook.addPosition(position);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }
    @Override
    public void deletePosition(Position positionToDelete) {
        positionBook.removePosition(positionToDelete);
        applicantBook.removeApplicantsUnderPosition(positionToDelete);
    }

    @Override
    public void setPositionBook(ReadOnlyPositionBook positionBook) {
        this.positionBook.resetData(positionBook);
    }

    @Override
    public ReadOnlyPositionBook getPositionBook() {
        return positionBook;
    }

    @Override
    public ObservableList<Position> getFilteredPositionList() {
        return filteredPositions;
    }

    @Override
    public void updateFilteredPositionList(Predicate<Position> predicate) {
        requireNonNull(predicate);
        filteredPositions.setPredicate(predicate);
    }

    //=========== Applicant and ApplicantBook =============================================================

    @Override
    public Path getApplicantBookFilePath() {
        return userPrefs.getApplicantBookFilePath();
    }

    public void setApplicantBook(ReadOnlyApplicantBook applicantBook) {
        this.applicantBook.resetData(applicantBook);
    }

    @Override
    public void setApplicant(Applicant target, Applicant editedApplicant) {
        requireAllNonNull(target, editedApplicant);
        applicantBook.setApplicant(target, editedApplicant);
    }

    @Override
    public ReadOnlyApplicantBook getApplicantBook() {
        return applicantBook;
    }

    @Override
    public void addApplicant(Applicant applicant) {
        applicantBook.addApplicant(applicant);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void deleteApplicant(Applicant target) {
        applicantBook.removeApplicant(target);
    }

    @Override
    public Applicant addApplicantWithParticulars(ApplicantParticulars applicantParticulars) {
        Title positionTitle = applicantParticulars.getPositionTitle();
        Position position = positionBook.getPositionByTitle(positionTitle);
        Applicant applicant = new Applicant(applicantParticulars, position);

        applicantBook.addApplicant(applicant);
        updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        return applicant;
    }

    @Override
    public boolean hasApplicant(Applicant applicant) {
        requireNonNull(applicant);
        return applicantBook.hasApplicant(applicant);
    }

    @Override
    public boolean hasApplicantWithName(Name applicantName) {
        requireNonNull(applicantName);
        return applicantBook.hasApplicantWithName(applicantName);
    }

    @Override
    public Applicant getApplicantByNameIgnoreCase(Name applicantName) {
        requireNonNull(applicantName);
        return applicantBook.getApplicantByNameIgnoreCase(applicantName);
    }

    @Override
    public void updateApplicantsWithPosition(Position positionToEdit, Position editedPosition) {
        requireAllNonNull(positionToEdit, editedPosition);
        applicantBook.updateApplicantsWithPosition(positionToEdit, editedPosition);
    }

    @Override
    public void updateFilteredApplicantList(Predicate<Applicant> predicate) {
        requireNonNull(predicate);
        filteredApplicants.setPredicate(predicate);
    }

    @Override
    public ObservableList<Applicant> getFilteredApplicantList() {
        return filteredApplicants;
    }

    //========== Rejection rates =======================================
    /**
     * Initialise rejection rate of a new position.
     *
     * @param title The title of the position to be calculated.
     * @return The rejection rate of a given position in MTR.
     */
    @Override
    public float calculateRejectionRate(Title title) {
        Position currPosition = positionBook.getPositionByTitle(title);
        int total = (int) applicantBook.getApplicantList()
                .stream()
                .filter(applicant -> applicant.isApplyingTo(currPosition))
                .count();

        int count = (int) applicantBook.getApplicantList()
                .stream()
                .filter(applicant -> applicant.isApplyingTo(currPosition)
                        && (applicant.getApplication().getStatus() == ApplicationStatus.REJECTED))
                .count();
        return Calculator.calculateRejectionRate(total, count);
    }


    @Override
    public Model getCopiedModel() {
        return new ModelManager(this.addressBook, applicantBook.getCopiedApplicantBook(),
                positionBook.getCopiedPositionBook(), this.userPrefs, this.history);
    }

    @Override
    public void addToHistory(Command command) {
        history.add(command);
    }


    @Override
    public boolean hasHistory() {
        return history.hasHistory();
    }

    @Override
    public String recoverHistory() {
        Memento memento = history.recoverHistory();
        Model previousModel = memento.getModel();
        this.setPositionBook(previousModel.getPositionBook());
        this.setApplicantBook(previousModel.getApplicantBook());

        return memento.getMessage();
    }
}
