package seedu.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.applicant.Applicant;

public class ApplicantBook implements ReadOnlyApplicantBook {

    public ApplicantBook() {}

    public ApplicantBook(ReadOnlyApplicantBook toBeCopied) {}

    public void addApplicant(Applicant a) {}

    @Override
    public ObservableList<Applicant> getApplicantList() {
        return FXCollections.emptyObservableList();
    }

}