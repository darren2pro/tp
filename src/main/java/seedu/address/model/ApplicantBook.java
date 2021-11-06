package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.UniqueApplicantList;
import seedu.address.model.position.Position;

/**
 * Wraps all applicant data at ApplicantBook level
 * Duplicates are not allowed (by .isSameApplicant comparison)
 */
public class ApplicantBook implements ReadOnlyApplicantBook {

    private final UniqueApplicantList applicants;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        applicants = new UniqueApplicantList();
    }

    public ApplicantBook() {}

    /**
     * Creates an ApplicantBook using the Applicants in the {@code toBeCopied}
     */
    public ApplicantBook(ReadOnlyApplicantBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the applicant list with {@code applicants}.
     * {@code applicants} must not contain duplicate applicants.
     */
    public void setApplicants(List<Applicant> applicants) {
        this.applicants.setApplicants(applicants);
    }

    /**
     * Resets the existing data of this {@code ApplicantBook} with {@code newData}.
     */
    public void resetData(ReadOnlyApplicantBook newData) {
        requireNonNull(newData);
        setApplicants(newData.getApplicantList());
    }

    //// position-level operations

    /**
     * Returns true if the {@code applicant} exists in the applicant book.
     */
    public boolean hasApplicant(Applicant applicant) {
        requireNonNull(applicant);
        return applicants.contains(applicant);
    }

    /**
     * Returns true if an applicant with the name {@code applicantName} exists in the applicant book.
     */
    public boolean hasApplicantWithName(Name applicantName) {
        requireNonNull(applicantName);
        return applicants.containsApplicantWithName(applicantName);
    }

    /**
     * Returns true if there are applicants applying to {@code position} in the applicant book.
     */
    public boolean hasApplicantsApplyingTo(Position position) {
        requireNonNull(position);
        return applicants.hasApplicantsApplyingTo(position);
    }

    /**
     * Returns the applicant with the specified name, if any.
     */
    public Applicant getApplicantWithName(Name applicantName) {
        requireNonNull(applicantName);
        return applicants.getApplicantWithName(applicantName);
    }

    /**
     * Adds an applicant to the applicant book.
     * The applicant must not already exist in the applicant book.
     */
    public void addApplicant(Applicant a) {
        applicants.add(a);
    }

    /**
     * Replaces the given applicant {@code target} in the list with {@code editedApplicant}.
     * {@code target} must exist in the applicant book.
     * The applicant identity of {@code editedApplicant} must not be the same as
     * another existing applicant in the applicant book.
     */
    public void setApplicant(Applicant target, Applicant editedApplicant) {
        requireNonNull(editedApplicant);
        applicants.setApplicant(target, editedApplicant);
    }

    /**
     * Removes {@code key} from this {@code ApplicantBook}.
     * {@code key} must exist in the applicant book.
     */
    public void removeApplicant(Applicant key) {
        applicants.remove(key);
    }

    public void removeApplicantsUnderPosition(Position position) {
        applicants.removeIf(applicant -> applicant.isApplyingTo(position));
    }

    public void updateApplicantsWithPosition(Position positionToEdit,
                                             Position editedPosition) {
        applicants.updateApplicantsWithPosition(positionToEdit, editedPosition);
    }

    @Override
    public String toString() {
        return applicants.asUnmodifiableObservableList().size() + " applicants";
        // TODO: refine later
    }

    @Override
    public ObservableList<Applicant> getApplicantList() {
        return applicants.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ApplicantBook // instanceof handles nulls
                && applicants.equals(((ApplicantBook) other).applicants));
    }

    public ApplicantBook getCopiedApplicantBook() {
        ApplicantBook copiedApplicantBook = new ApplicantBook();
        copiedApplicantBook.applicants.setApplicants(this.applicants.getCopiedApplicants());

        return copiedApplicantBook;
    }

}
