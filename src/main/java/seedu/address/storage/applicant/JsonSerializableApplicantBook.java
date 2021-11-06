package seedu.address.storage.applicant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ApplicantBook;
import seedu.address.model.ReadOnlyApplicantBook;
import seedu.address.model.ReadOnlyPositionBook;
import seedu.address.model.applicant.Applicant;

/**
 * An Immutable ApplicantBook that is serializable to JSON format.
 */
@JsonRootName(value = "applicantbook")
public class JsonSerializableApplicantBook {
    public static final String MESSAGE_DUPLICATE_POSITION = "Applicants list contains duplicate applicant(s).";

    private final List<JsonAdaptedApplicant> applicants = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableApplicantBook} with the given applicants.
     */
    @JsonCreator
    public JsonSerializableApplicantBook(@JsonProperty("applicants") List<JsonAdaptedApplicant> applicants) {
        this.applicants.addAll(applicants);
    }

    /**
     * Converts a given {@code ReadOnlyApplicantBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableApplicantBook}.
     */
    public JsonSerializableApplicantBook(ReadOnlyApplicantBook source) {
        applicants.addAll(source.getApplicantList().stream().map(JsonAdaptedApplicant::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this applicant book into the model's {@code ApplicantBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ApplicantBook toModelType(ReadOnlyPositionBook positionBook) throws IllegalValueException {
        ApplicantBook applicantBook = new ApplicantBook();
        for (JsonAdaptedApplicant jsonAdaptedApplicant : applicants) {
            Applicant applicant = jsonAdaptedApplicant.toModelType(positionBook);
            if (applicantBook.hasApplicantWithName(applicant.getName())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_POSITION);
            }
            applicantBook.addApplicant(applicant);
        }
        return applicantBook;
    }

}
