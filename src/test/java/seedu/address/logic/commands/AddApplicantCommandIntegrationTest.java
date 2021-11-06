package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPositions.getTypicalPositionBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.ApplicantParticulars;
import seedu.address.model.position.Position;
import seedu.address.testutil.ApplicantBuilder;
import seedu.address.testutil.PositionBuilder;

public class AddApplicantCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPositionBook(), new UserPrefs());
    }

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPositionCommand(null));
    }

    @Test
    public void execute_newApplicantWithExistingPosition_success() {
        Position validPosition = model.getFilteredPositionList().get(0);

        ApplicantParticulars validParticulars = new ApplicantBuilder().withPosition(validPosition).getParticulars();
        Applicant validApplicant = new ApplicantBuilder().withPosition(validPosition).build();

        Model expectedModel = new ModelManager(model.getPositionBook(), new UserPrefs());
        expectedModel.addApplicant(validApplicant);

        assertCommandSuccess(new AddApplicantCommand(validParticulars), model,
                String.format(AddApplicantCommand.MESSAGE_SUCCESS, validApplicant), expectedModel);
    }

    @Test
    public void execute_newApplicantWithNonExistingPosition_throwsCommandException() {
        Position nonExistingPosition = new PositionBuilder().withTitle("NonExistingPosition").build();

        ApplicantParticulars invalidParticulars = new ApplicantBuilder()
                .withPosition(nonExistingPosition).getParticulars();
        Applicant invalidApplicant = new ApplicantBuilder().withPosition(nonExistingPosition).build();

        Model expectedModel = new ModelManager(model.getPositionBook(), new UserPrefs());
        expectedModel.addApplicant(invalidApplicant);

        assertCommandFailure(new AddApplicantCommand(invalidParticulars), model,
                AddApplicantCommand.MESSAGE_NO_SUCH_POSITION);
    }

    @Test
    public void execute_duplicateApplicant_throwsCommandException() {
        ApplicantParticulars duplicateParticulars = new ApplicantBuilder().getParticulars();
        Applicant duplicateApplicant = new ApplicantBuilder().build();
        model.addApplicant(duplicateApplicant);

        assertCommandFailure(new AddApplicantCommand(duplicateParticulars), model,
                AddApplicantCommand.MESSAGE_DUPLICATE_APPLICANT);
    }

}
