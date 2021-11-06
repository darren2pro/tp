package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalApplicants.getTypicalApplicantBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;
import static seedu.address.testutil.TypicalPositions.getTypicalPositionBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;


public class DeleteApplicantCommandTest {

    private Model model = new ModelManager(getTypicalApplicantBook(), getTypicalPositionBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Applicant applicantToDelete = model.getFilteredApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());
        DeleteApplicantCommand deleteApplicantCommand = new DeleteApplicantCommand(INDEX_FIRST_APPLICANT);

        String expectedMessage = String.format(DeleteApplicantCommand.MESSAGE_DELETE_APPLICANT_SUCCESS,
                applicantToDelete);

        ModelManager expectedModel = new ModelManager(model.getApplicantBook(),
                model.getPositionBook(), new UserPrefs());
        expectedModel.deleteApplicant(applicantToDelete);

        assertCommandSuccess(deleteApplicantCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicantList().size() + 1);
        DeleteApplicantCommand deleteApplicantCommand = new DeleteApplicantCommand(outOfBoundIndex);

        assertCommandFailure(deleteApplicantCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

}
