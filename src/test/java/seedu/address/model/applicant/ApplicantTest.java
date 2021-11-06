package seedu.address.model.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.TypicalApplicants.ALICE;
import static seedu.address.testutil.TypicalApplicants.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ApplicantBuilder;

public class ApplicantTest {

    @Test
    public void isSameApplicant() {
        // same object -> returns true
        assertTrue(ALICE.isSameApplicant(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameApplicant(null));

        // same name, all other attributes different -> returns true
        Applicant editedAlice = new ApplicantBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(ALICE.isSameApplicant(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Applicant editedBob = new ApplicantBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSameApplicant(editedBob));

        // different name, all other attributes same -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameApplicant(editedAlice));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new ApplicantBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameApplicant(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Applicant aliceCopy = new ApplicantBuilder(ALICE).build();
        assertEquals(ALICE, aliceCopy);

        // same object -> returns true
        assertEquals(ALICE, ALICE);

        // null -> returns false
        assertNotEquals(null, ALICE);

        // different type -> returns false
        assertNotEquals(5, ALICE);

        // different applicant -> returns false
        assertNotEquals(ALICE, BOB);

        // different name -> returns false
        Applicant editedAlice = new ApplicantBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different phone -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different email -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different address -> returns false
        editedAlice = new ApplicantBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertNotEquals(ALICE, editedAlice);

    }

}
