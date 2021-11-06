package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.model.applicant.Application.ApplicationStatus;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.descriptors.EditPositionDescriptor;
import seedu.address.logic.descriptors.FilterApplicantDescriptor;
import seedu.address.model.ApplicantBook;
import seedu.address.model.Model;
import seedu.address.model.PositionBook;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.NameContainsKeywordsPredicate;
import seedu.address.model.position.Position;
import seedu.address.model.position.TitleContainsAllKeywordsPredicate;
import seedu.address.testutil.EditPositionDescriptorBuilder;
import seedu.address.testutil.FilterApplicantDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";


    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses

    public static final String VALID_TITLE_DATAENGINEER = "Data Engineer";
    public static final String VALID_TITLE_DATASCIENTIST = "Data Scientist";
    public static final String VALID_TITLE_SOFTWAREARCHITECT = "Software Architect";
    public static final String VALID_DESCRIPTION_DATAENGINEER = "Create data pipeline for service";
    public static final String VALID_DESCRIPTION_DATASCIENTIST = "Apply state-of-the-art machine learning models";
    public static final String VALID_DESCRIPTION_SOFTWAREARCHITECT = "Makes high-level design choices and "
            + "try to enforce technical standards";

    public static final String TITLE_DESC_DATAENGINEER = " " + PREFIX_TITLE
            + VALID_TITLE_DATAENGINEER;
    public static final String TITLE_DESC_DATASCIENTIST = " " + PREFIX_TITLE
            + VALID_TITLE_DATASCIENTIST;
    public static final String TITLE_DESC_SOFTWAREARCHITECT = " " + PREFIX_TITLE
            + VALID_TITLE_SOFTWAREARCHITECT;
    public static final String DESCRIPTION_DESC_DATAENGINEER = " " + PREFIX_DESCRIPTION
            + VALID_DESCRIPTION_DATAENGINEER;
    public static final String DESCRIPTION_DESC_DATASCIENTIST = " " + PREFIX_DESCRIPTION
            + VALID_DESCRIPTION_DATASCIENTIST;
    public static final String DESCRIPTION_DESC_SOFRWAREARCHITECT = " " + PREFIX_DESCRIPTION
            + VALID_DESCRIPTION_SOFTWAREARCHITECT;

    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE
            + "sleeper:"; // ':' not allowed in titles
    public static final String INVALID_DESCRIPTION = " " + PREFIX_DESCRIPTION
            + ""; // empty string not allowed for descrption.


    public static final String INVALID_FILTER_TITLE_DATAMINER = "Data Miner";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";


    public static final EditPositionDescriptor DESC_DATA_ENGINEER;
    public static final EditPositionDescriptor DESC_DATA_SCIENTIST;

    public static final FilterApplicantDescriptor FILTER_DESC_FULL;
    public static final FilterApplicantDescriptor FILTER_DESC_PARTIAL;

    static {

        DESC_DATA_ENGINEER = new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_DATAENGINEER)
                .withDescription(VALID_DESCRIPTION_DATAENGINEER).build();
        DESC_DATA_SCIENTIST = new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_DATASCIENTIST)
                .withDescription(VALID_DESCRIPTION_DATASCIENTIST).build();
        FILTER_DESC_FULL = new FilterApplicantDescriptorBuilder()
                .withPositionTitle(VALID_TITLE_DATASCIENTIST)
                .withApplicationStatus(ApplicationStatus.PENDING)
                .build();
        FILTER_DESC_PARTIAL = new FilterApplicantDescriptorBuilder()
                .withPositionTitle(VALID_TITLE_DATASCIENTIST)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the position book, filtered position list, applicant book, filtered applicant list
     *      in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        PositionBook expectedPositionBook = new PositionBook(actualModel.getPositionBook());
        ApplicantBook expectedApplicantBook = new ApplicantBook(actualModel.getApplicantBook());

        List<Position> expectedFilteredPositionList = new ArrayList<>(actualModel.getFilteredPositionList());
        List<Applicant> expectedFilteredApplicantList = new ArrayList<>(actualModel.getFilteredApplicantList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedPositionBook, actualModel.getPositionBook());
        assertEquals(expectedApplicantBook, actualModel.getApplicantBook());
        assertEquals(expectedFilteredPositionList, actualModel.getFilteredPositionList());
        assertEquals(expectedFilteredApplicantList, actualModel.getFilteredApplicantList());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertPositionCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        PositionBook expectedPositionBook = new PositionBook(actualModel.getPositionBook());
        List<Position> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPositionList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedPositionBook, actualModel.getPositionBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPositionList());
    }


    /**
     * Updates {@code model}'s filtered list to show only the position at the given {@code targetIndex} in the
     * {@code model}'s position book.
     */
    public static void showPositionAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPositionList().size());

        Position position = model.getFilteredPositionList().get(targetIndex.getZeroBased());
        final String[] splitName = position.getTitle().fullTitle.split("\\s+");

        model.updateFilteredPositionList(new TitleContainsAllKeywordsPredicate(Arrays.asList(splitName)));

        assertEquals(1, model.getFilteredPositionList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the applicant at the given {@code targetIndex} in the
     * {@code model}'s applicant book.
     */
    public static void showApplicantAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPositionList().size());

        Applicant applicant = model.getFilteredApplicantList().get(targetIndex.getZeroBased());
        final String[] splitName = applicant.getName().fullName.split("\\s+");

        model.updateFilteredApplicantList(new NameContainsKeywordsPredicate(Arrays.asList(splitName)));

        assertEquals(1, model.getFilteredApplicantList().size());
    }

}
