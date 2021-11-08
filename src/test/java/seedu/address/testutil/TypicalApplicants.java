package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.TypicalPositions.DATAENGINEER;
import static seedu.address.testutil.TypicalPositions.DATASCIENTIST;
import static seedu.address.testutil.TypicalPositions.SOFTWAREARCHITECT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ApplicantBook;
import seedu.address.model.applicant.Applicant;

public class TypicalApplicants {

    public static final Applicant ALICE = new ApplicantBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withPosition(DATASCIENTIST).withGitHubProfile("https://github.com/empty").build();
    public static final Applicant BENSON = new ApplicantBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withPosition(SOFTWAREARCHITECT)
            .withGitHubProfile("https://github.com/empty")
            .build();
    public static final Applicant CARL = new ApplicantBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withPosition(DATASCIENTIST)
            .withGitHubProfile("https://github.com/empty").build();
    public static final Applicant DANIEL = new ApplicantBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withPosition(DATAENGINEER)
            .withGitHubProfile("https://github.com/empty").build();
    public static final Applicant ELLE = new ApplicantBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withPosition(SOFTWAREARCHITECT)
            .withGitHubProfile("https://github.com/empty").build();
    public static final Applicant FIONA = new ApplicantBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withPosition(DATASCIENTIST)
            .withGitHubProfile("https://github.com/empty").build();
    public static final Applicant GEORGE = new ApplicantBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withPosition(DATAENGINEER)
            .withGitHubProfile("https://github.com/empty").build();

    // Manually added
    public static final Applicant HOON = new ApplicantBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withPosition(DATAENGINEER)
            .withGitHubProfile("https://github.com/empty").build();
    public static final Applicant IDA = new ApplicantBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withPosition(DATAENGINEER)
            .withGitHubProfile("https://github.com/empty").build();

    // Manually added - Applicant's details found in {@code CommandTestUtil}
    public static final Applicant AMY = new ApplicantBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withPosition(DATASCIENTIST)
            .withGitHubProfile("https://github.com/empty").build();
    public static final Applicant BOB = new ApplicantBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withPosition(SOFTWAREARCHITECT)
            .withGitHubProfile("https://github.com/empty").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalApplicants() {} // prevents instantiation

    /**
     * Returns an {@code ApplicantBook} with all the typical applicants.
     */
    public static ApplicantBook getTypicalApplicantBook() {
        ApplicantBook ab = new ApplicantBook();
        for (Applicant applicant : getTypicalApplicants()) {
            ab.addApplicant(applicant);
        }
        return ab;
    }

    public static List<Applicant> getTypicalApplicants() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
