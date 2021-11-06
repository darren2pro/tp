package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.RejectionRateCommand.MESSAGE_NO_CURRENT_APPLICANTS;
import static seedu.address.logic.commands.RejectionRateCommand.MESSAGE_SUCCESS;
import static seedu.address.model.applicant.Application.ApplicationStatus.REJECTED;
import static seedu.address.testutil.TypicalApplicants.getTypicalApplicantBook;
import static seedu.address.testutil.TypicalPositions.DATASCIENTIST;
import static seedu.address.testutil.TypicalPositions.getTypicalPositionBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;
import seedu.address.testutil.ApplicantBuilder;
import seedu.address.testutil.PositionBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code RejectionRateCommand}.
 */
public class RejectionRateCommandTest {
    private final ModelManager model = new ModelManager(getTypicalApplicantBook(),
            getTypicalPositionBook(), new UserPrefs());
    private final Position position = new PositionBuilder().build();

    @Test
    public void constructor_nullPosition_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RejectionRateCommand(null));
    }

    @Test
    public void execute_nonExistingPosition_throwsCommandException() {
        assertThrows(CommandException.class, () -> new RejectionRateCommand(new Title("Impossible")).execute(model));
    }

    @Test
    public void execute_existingPositionWithoutApplicant_successful() throws Exception {
        model.addPosition(position);
        assertEquals(new CommandResult(MESSAGE_NO_CURRENT_APPLICANTS),
                new RejectionRateCommand(position.getTitle()).execute(model));
    }

    @Test
    public void execute_existingPositionWithExistingApplicant_successful() throws Exception {
        Applicant applicant = new ApplicantBuilder().build();

        model.addPosition(position);
        model.addApplicant(applicant);

        CommandResult commandResult = new RejectionRateCommand(position.getTitle()).execute(model);
        float rejectionRate = model.calculateRejectionRate(position.getTitle()); // 0%

        assertEquals(String.format(MESSAGE_SUCCESS, position.getTitle(), rejectionRate),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_changeRejectionRateAfterUpdatingApplicant_successful() {
        Applicant applicant = new ApplicantBuilder().build();

        model.addApplicant(applicant);
        model.addPosition(position);

        float initialRejectionRate = model.calculateRejectionRate(position.getTitle()); // currently 0.0%

        Applicant newApplicant = applicant.markAs(REJECTED);

        model.setApplicant(applicant, newApplicant);

        float newRejectionRate = model.calculateRejectionRate(position.getTitle()); // now 12.5%

        assertNotEquals(initialRejectionRate, newRejectionRate);
    }

    @Test
    public void execute_changeRejectionRateAfterAddingMoreApplicants_successful() {
        // Create Amy and set status to REJECTED
        Applicant applicant = new ApplicantBuilder().build();

        model.addApplicant(applicant);

        model.addPosition(position);

        Applicant newApplicant = applicant.markAs(REJECTED);

        model.setApplicant(applicant, newApplicant);

        float initialRejectionRate = model.calculateRejectionRate(position.getTitle()); // 12.5

        // Create Alice
        Applicant otherApplicant = new ApplicantBuilder().withName("Alice").build();

        model.addApplicant(otherApplicant);

        float newRejectionRate = model.calculateRejectionRate(position.getTitle()); // 11.1%

        assertNotEquals(initialRejectionRate, newRejectionRate);
    }

    @Test
    public void execute_changeRejectionRateAfterDeletingApplicants_successful() {
        // Create Amy and set status to REJECTED
        model.addPosition(position);

        Applicant applicant = new ApplicantBuilder().build();

        model.addApplicant(applicant);

        Applicant newApplicant = applicant.markAs(REJECTED);

        // Create Alice
        Applicant otherApplicant = new ApplicantBuilder().withName("Alice").build();

        model.setApplicant(applicant, newApplicant);

        model.addApplicant(otherApplicant);

        float initialRejectionRate = model.calculateRejectionRate(position.getTitle()); // 11.1%

        model.deleteApplicant(newApplicant);

        float newRejectionRate = model.calculateRejectionRate(position.getTitle()); // 0%

        assertNotEquals(initialRejectionRate, newRejectionRate);
    }

    @Test
    public void equals() {
        final RejectionRateCommand standardCommand = new RejectionRateCommand(position.getTitle());
        Position otherPosition = DATASCIENTIST;

        RejectionRateCommand sameCommand = new RejectionRateCommand(position.getTitle());
        RejectionRateCommand otherCommand = new RejectionRateCommand(DATASCIENTIST.getTitle());

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // duplicate command -> returns True
        assertEquals(standardCommand, sameCommand);

        // different types -> returns false
        assertFalse(standardCommand.equals(1));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different position -> returns false
        assertFalse(standardCommand.equals(otherCommand));
    }
}

