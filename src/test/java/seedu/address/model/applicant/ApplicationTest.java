package seedu.address.model.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.model.applicant.Application.ApplicationStatus;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.DATAENGINEER_APPLICATION;
import static seedu.address.testutil.TypicalApplications.DATASCIENTIST_APPLICATION;
import static seedu.address.testutil.TypicalPositions.DATAENGINEER;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ApplicationBuilder;
import seedu.address.testutil.PositionBuilder;

public class ApplicationTest {
    @Test
    public void constructor_onlyPositionNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Application(null));
    }

    @Test
    public void constructor_positionNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new Application(null, ApplicationStatus.PENDING));
    }

    @Test
    public void constructor_statusNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new Application(new PositionBuilder().build(), null));
    }

    @Test
    public void constructor_positionAndStatusNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Application(null));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Application dataScientistApplicationCopy = new ApplicationBuilder(DATASCIENTIST_APPLICATION).build();
        assertEquals(DATASCIENTIST_APPLICATION, dataScientistApplicationCopy);

        // same object -> returns true
        assertEquals(DATASCIENTIST_APPLICATION, DATASCIENTIST_APPLICATION);

        // null -> returns false
        assertNotEquals(null, DATASCIENTIST_APPLICATION);

        // different type -> returns false
        assertNotEquals(7, DATASCIENTIST_APPLICATION);

        // different application -> returns false
        assertNotEquals(DATASCIENTIST_APPLICATION, DATAENGINEER_APPLICATION);

        // different position -> returns false
        Application editedDataScientistApplication =
                new ApplicationBuilder(DATASCIENTIST_APPLICATION).withPosition(DATAENGINEER).build();
        assertNotEquals(DATASCIENTIST_APPLICATION, editedDataScientistApplication);

        // different application status -> returns false
        editedDataScientistApplication = new ApplicationBuilder(DATASCIENTIST_APPLICATION)
                .withApplicationStatus(ApplicationStatus.ACCEPTED)
                .build();
        assertNotEquals(DATASCIENTIST_APPLICATION, editedDataScientistApplication);
    }

}
