package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.FILTER_DESC_FULL;
import static seedu.address.logic.commands.CommandTestUtil.FILTER_DESC_PARTIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DATAENGINEER;
import static seedu.address.model.applicant.Application.ApplicationStatus;

import org.junit.jupiter.api.Test;

import seedu.address.logic.descriptors.FilterApplicantDescriptor;
import seedu.address.testutil.FilterApplicantDescriptorBuilder;

public class FilterApplicantDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        FilterApplicantDescriptor descriptorWithSameValues = new FilterApplicantDescriptor(FILTER_DESC_FULL);
        assertEquals(FILTER_DESC_FULL, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(FILTER_DESC_FULL, FILTER_DESC_FULL);

        // null -> returns false
        assertNotEquals(null, FILTER_DESC_FULL);

        // different types -> returns false
        assertNotEquals(9, FILTER_DESC_FULL);

        // different values -> returns false
        assertNotEquals(FILTER_DESC_FULL, FILTER_DESC_PARTIAL);

        // different position title -> returns false
        FilterApplicantDescriptor editedDescriptor = new FilterApplicantDescriptorBuilder(FILTER_DESC_FULL)
                .withPositionTitle(VALID_TITLE_DATAENGINEER)
                .build();
        assertNotEquals(FILTER_DESC_FULL, editedDescriptor);

        // different application status -> returns false
        editedDescriptor = new FilterApplicantDescriptorBuilder(FILTER_DESC_FULL)
                .withApplicationStatus(ApplicationStatus.ACCEPTED)
                .build();
        assertNotEquals(FILTER_DESC_FULL, editedDescriptor);

    }
}
