package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.FILTER_DESC_FULL;
import static seedu.address.logic.commands.CommandTestUtil.FILTER_DESC_PARTIAL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FILTER_TITLE_DATAMINER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DATAENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DATASCIENTIST;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;
import static seedu.address.model.applicant.Application.ApplicationStatus;
import static seedu.address.testutil.TypicalApplicants.getTypicalApplicantBook;
import static seedu.address.testutil.TypicalPositions.DATAENGINEER;
import static seedu.address.testutil.TypicalPositions.DATASCIENTIST;
import static seedu.address.testutil.TypicalPositions.getTypicalPositionBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.descriptors.FilterApplicantDescriptor;
import seedu.address.model.ApplicantBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PositionBook;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.FilterApplicantDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for FilterApplicantCommand.
 */
public class FilterApplicantCommandTest {

    private Model model = new ModelManager(
            getTypicalApplicantBook(), getTypicalPositionBook(), new UserPrefs());

    @Test
    public void execute_allFiltersSpecified_success() {
        FilterApplicantDescriptor descriptor = new FilterApplicantDescriptorBuilder()
                .withPositionTitle(VALID_TITLE_DATASCIENTIST)
                .withApplicationStatus(ApplicationStatus.PENDING)
                .build();
        FilterApplicantCommand filterApplicantCommand = new FilterApplicantCommand(descriptor);

        String expectedMessage = String.format(FilterApplicantCommand.MESSAGE_SUCCESS, descriptor);

        Model expectedModel = new ModelManager(
                new PositionBook(model.getPositionBook()),
                new ApplicantBook(model.getApplicantBook()),
                new UserPrefs());
        expectedModel.updateFilteredApplicantList(applicant ->
                applicant.getApplication().getStatus().equals(ApplicationStatus.PENDING)
                && applicant.getApplication().getPosition().equals(DATASCIENTIST));

        assertCommandSuccess(filterApplicantCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFiltersSpecified_success() {
        FilterApplicantDescriptor descriptor = new FilterApplicantDescriptorBuilder()
                .withPositionTitle(VALID_TITLE_DATAENGINEER)
                .build();
        FilterApplicantCommand filterApplicantCommand = new FilterApplicantCommand(descriptor);

        String expectedMessage = String.format(FilterApplicantCommand.MESSAGE_SUCCESS, descriptor);

        Model expectedModel = new ModelManager(
                new PositionBook(model.getPositionBook()),
                new ApplicantBook(model.getApplicantBook()),
                new UserPrefs());
        expectedModel.updateFilteredApplicantList(applicant ->
                applicant.getApplication().getPosition().equals(DATAENGINEER));

        assertCommandSuccess(filterApplicantCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFiltersSpecified_failure() {
        FilterApplicantDescriptor descriptor = new FilterApplicantDescriptorBuilder().build();
        FilterApplicantCommand filterApplicantCommand = new FilterApplicantCommand(descriptor);

        String expectedMessage = String.format(FilterApplicantCommand.MESSAGE_SUCCESS, descriptor);

        Model expectedModel = new ModelManager(
                new PositionBook(model.getPositionBook()),
                new ApplicantBook(model.getApplicantBook()),
                new UserPrefs());
        expectedModel.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);

        assertCommandSuccess(filterApplicantCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Invalid filters are those which do not exist in MrTechRecruiter.
     */
    @Test
    public void execute_invalidFiltersSpecified_failure() {
        FilterApplicantDescriptor descriptor = new FilterApplicantDescriptorBuilder()
                .withPositionTitle(INVALID_FILTER_TITLE_DATAMINER)
                .withApplicationStatus(ApplicationStatus.PENDING)
                .build();
        FilterApplicantDescriptor invalidFilterDescriptor = new FilterApplicantDescriptorBuilder()
                .withPositionTitle(INVALID_FILTER_TITLE_DATAMINER)
                .build();
        FilterApplicantCommand filterApplicantCommand = new FilterApplicantCommand(descriptor);

        String expectedMessage = String.format(FilterApplicantCommand.MESSAGE_INVALID_FILTER, invalidFilterDescriptor);

        assertCommandFailure(filterApplicantCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        final FilterApplicantCommand standardCommand = new FilterApplicantCommand(FILTER_DESC_FULL);

        // same values -> returns true
        FilterApplicantDescriptor descriptor = new FilterApplicantDescriptor(FILTER_DESC_FULL);
        FilterApplicantCommand commandWithSameValues = new FilterApplicantCommand(descriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ListPositionCommand());

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new FilterApplicantCommand(FILTER_DESC_PARTIAL));
    }

}
