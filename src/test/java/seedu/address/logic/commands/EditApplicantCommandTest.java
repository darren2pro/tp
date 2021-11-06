package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static seedu.address.testutil.TypicalApplicants.getTypicalApplicantBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_POSITION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICANT;
import static seedu.address.testutil.TypicalPositions.getTypicalPositionBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.descriptors.EditApplicantDescriptor;
import seedu.address.model.ApplicantBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PositionBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.position.Position;
import seedu.address.testutil.ApplicantBuilder;
import seedu.address.testutil.EditApplicantDescriptorBuilder;


public class EditApplicantCommandTest {
    private Model model = new ModelManager(getTypicalApplicantBook(), getTypicalPositionBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Position validPosition = model.getFilteredPositionList().get(0);
        Applicant editApplicant = new ApplicantBuilder().withPosition(validPosition).build();
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(editApplicant).build();
        EditApplicantCommand editApplicantCommand = new EditApplicantCommand(INDEX_FIRST_APPLICANT, descriptor);

        String expectedMessage = String.format(EditApplicantCommand.MESSAGE_EDIT_APPLICANT_SUCCESS, editApplicant);

        Model expectedModel = new ModelManager(new ApplicantBook(model.getApplicantBook()),
                new PositionBook(model.getPositionBook()), new UserPrefs());

        expectedModel.setApplicant(model.getFilteredApplicantList().get(0), editApplicant);

        assertCommandSuccess(editApplicantCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastApplicant = Index.fromOneBased(model.getFilteredApplicantList().size());
        Applicant lastApplicant = model.getFilteredApplicantList().get(indexLastApplicant.getZeroBased());

        ApplicantBuilder applicantInList = new ApplicantBuilder(lastApplicant);
        Applicant editedApplicant = applicantInList.withName(VALID_NAME_AMY).withAddress(VALID_ADDRESS_AMY).build();

        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder().withName(VALID_NAME_AMY)
                .withAddress(VALID_ADDRESS_AMY).build();
        EditApplicantCommand editApplicantCommand = new EditApplicantCommand(indexLastApplicant, descriptor);

        String expectedMessage = String.format(EditApplicantCommand.MESSAGE_EDIT_APPLICANT_SUCCESS, editedApplicant);

        Model expectedModel = new ModelManager(new ApplicantBook(model.getApplicantBook()),
                new PositionBook(model.getPositionBook()), new UserPrefs());
        expectedModel.setApplicant(lastApplicant, editedApplicant);

        assertCommandSuccess(editApplicantCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateApplicantUnfilteredList_failure() {
        Applicant firstApplicant = model.getFilteredApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(firstApplicant).build();
        EditApplicantCommand editApplicantCommand = new EditApplicantCommand(INDEX_SECOND_APPLICANT, descriptor);

        assertCommandFailure(editApplicantCommand, model, EditApplicantCommand.MESSAGE_DUPLICATE_APPLICANT);
    }

    @Test
    public void execute_invalidApplicantIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicantList().size() + 1);
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder()
                .withName(VALID_NAME_BOB).build();
        EditApplicantCommand editApplicantCommand = new EditApplicantCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editApplicantCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidApplicantIndexFilteredList_failure() {
        showApplicantAtIndex(model, INDEX_FIRST_POSITION);
        Index outOfBoundIndex = INDEX_SECOND_APPLICANT;
        // ensures that outOfBoundIndex is still in bounds of applicant book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getApplicantBook().getApplicantList().size());

        EditApplicantCommand editApplicantCommand = new EditApplicantCommand(outOfBoundIndex,
                new EditApplicantDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build());

        assertCommandFailure(editApplicantCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

}
