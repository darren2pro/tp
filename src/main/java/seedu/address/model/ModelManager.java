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
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final PositionBook positionBook;
    private final ApplicantBook applicantBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Applicant> filteredApplicants;
    private final FilteredList<Position> filteredPositions;
    private final History history;

    /**
     * Initializes a ModelManager with the given positionBook, applicantBook, applicationBook and userPrefs.
     */
    public ModelManager(ReadOnlyApplicantBook applicantBook,
                        ReadOnlyPositionBook positionBook,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(applicantBook, positionBook, userPrefs);

        logger.fine("Initializing with applicant book: " + applicantBook
                + ", position book: " + positionBook
                + ", userPrefs: " + userPrefs);

        this.positionBook = new PositionBook(positionBook);
        this.applicantBook = new ApplicantBook(applicantBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredApplicants = new FilteredList<>(this.applicantBook.getApplicantList());
        filteredPositions = new FilteredList<>(this.positionBook.getPositionList());
        history = new History();
    }

    /**
     * Initializes a ModelManager with the given positionBook, applicantBook and userPrefs.
     */
    public ModelManager(ReadOnlyApplicantBook applicantBook,
                        ReadOnlyPositionBook positionBook,
                        ReadOnlyUserPrefs userPrefs, History changeHistory) {
        super();
        requireAllNonNull(applicantBook, positionBook, userPrefs);

        logger.fine("Initializing with applicant book: " + applicantBook
                + ", position book: " + positionBook
                + ", userPrefs: " + userPrefs);

        this.positionBook = new PositionBook(positionBook);
        this.applicantBook = new ApplicantBook(applicantBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredApplicants = new FilteredList<>(this.applicantBook.getApplicantList());
        filteredPositions = new FilteredList<>(this.positionBook.getPositionList());
        this.history = changeHistory;
    }

    /**
     * Initializes a ModelManager with the given userPrefs.
     */
    public ModelManager(ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(userPrefs);

        logger.fine("Initializing with user prefs " + userPrefs);

        this.positionBook = new PositionBook();
        this.applicantBook = new ApplicantBook();
        this.userPrefs = new UserPrefs(userPrefs);

        filteredApplicants = new FilteredList<>(this.applicantBook.getApplicantList());
        filteredPositions = new FilteredList<>(this.positionBook.getPositionList());
        history = new History();
    }

    /**
     * Initializes a ModelManager with the given positionBook and userPrefs.
     */
    public ModelManager(ReadOnlyPositionBook positionBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(positionBook, userPrefs);

        logger.fine("Initializing with position book: " + positionBook + " and user prefs " + userPrefs);

        this.positionBook = new PositionBook(positionBook);
        this.applicantBook = new ApplicantBook();
        this.userPrefs = new UserPrefs(userPrefs);

        filteredApplicants = new FilteredList<>(this.applicantBook.getApplicantList());
        filteredPositions = new FilteredList<>(this.positionBook.getPositionList());
        history = new History();
    }

    /**
     * Initializes a ModelManager with the given positionBook, applicantBook and userPrefs.
     */
    public ModelManager(ReadOnlyPositionBook positionBook, ReadOnlyApplicantBook applicantBook,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(positionBook, userPrefs);

        logger.fine("Initializing with position book: " + positionBook + " and user prefs " + userPrefs);

        this.positionBook = new PositionBook(positionBook);
        this.applicantBook = new ApplicantBook(applicantBook);
        this.userPrefs = new UserPrefs(userPrefs);

        filteredApplicants = new FilteredList<>(this.applicantBook.getApplicantList());
        filteredPositions = new FilteredList<>(this.positionBook.getPositionList());
        history = new History();
    }

    /**
     * Constructor for completely new Applicant and Position books.
     */
    public ModelManager() {
        this(new ApplicantBook(), new PositionBook(), new UserPrefs());
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
    public Position getPositionWithTitle(Title title) {
        return positionBook.getPositionWithTitle(title);
    }

    @Override
    public void addPosition(Position position) {
        positionBook.addPosition(position);
        updateFilteredPositionList(PREDICATE_SHOW_ALL_POSITIONS);
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
        updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
    }

    @Override
    public void deleteApplicant(Applicant target) {
        applicantBook.removeApplicant(target);
    }

    @Override
    public Applicant addApplicantWithParticulars(ApplicantParticulars applicantParticulars) {
        Title positionTitle = applicantParticulars.getPositionTitle();
        Position position = positionBook.getPositionWithTitle(positionTitle);
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
    public boolean hasApplicantsApplyingTo(Position position) {
        requireNonNull(position);
        return applicantBook.hasApplicantsApplyingTo(position);
    }

    @Override
    public Applicant getApplicantWithName(Name applicantName) {
        requireNonNull(applicantName);
        return applicantBook.getApplicantWithName(applicantName);
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
        Position currPosition = positionBook.getPositionWithTitle(title);
        int total = (int) applicantBook.getApplicantList()
                .stream()
                .filter(applicant -> applicant.isApplyingTo(currPosition))
                .count();

        int count = (int) applicantBook.getApplicantList()
                .stream()
                .filter(applicant -> applicant.isApplyingTo(currPosition)
                        && (applicant.hasApplicationStatus(ApplicationStatus.REJECTED)))
                .count();
        return Calculator.calculateRejectionRate(total, count);
    }

    @Override
    public Model getCopiedModel() {
        return new ModelManager(applicantBook.getCopiedApplicantBook(),
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
        String message = this.resetData(memento);
        return message;
    }

    @Override
    public String resetData(Memento memento) {
        Model previousModel = memento.getModel();
        this.setPositionBook(previousModel.getPositionBook());
        this.setApplicantBook(previousModel.getApplicantBook());
        return memento.getMessage();
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
        return positionBook.equals(other.positionBook)
                && applicantBook.equals(other.applicantBook)
                && filteredPositions.equals(other.filteredPositions)
                && filteredApplicants.equals(other.filteredApplicants)
                && userPrefs.equals(other.userPrefs);
    }
}
