package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_DATAENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_DATASCIENTIST;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_DATAENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_DATASCIENTIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DATASCIENTIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DATAENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DATASCIENTIST;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_POSITION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_POSITION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditPositionCommand;
import seedu.address.logic.descriptors.EditPositionDescriptor;
import seedu.address.model.position.Description;
import seedu.address.model.position.Title;
import seedu.address.testutil.EditPositionDescriptorBuilder;

public class EditPositionParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPositionCommand.MESSAGE_USAGE);

    private EditPositionCommandParser parser = new EditPositionCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, TITLE_DESC_DATASCIENTIST, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditPositionCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TITLE_DESC_DATASCIENTIST, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TITLE_DESC_DATASCIENTIST, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC, Title.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, "1" + INVALID_DESCRIPTION, Description.MESSAGE_CONSTRAINTS); // invalid phone

        // invalid title followed by valid description
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC + DESCRIPTION_DESC_DATAENGINEER,
                Title.MESSAGE_CONSTRAINTS);

        // valid title followed by invalid title. The
        assertParseFailure(parser, "1" + TITLE_DESC_DATASCIENTIST + INVALID_TITLE_DESC,
                Title.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parser_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_POSITION;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_DATASCIENTIST
                + DESCRIPTION_DESC_DATASCIENTIST;

        EditPositionDescriptor descriptor = new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_DATASCIENTIST)
                .withDescription(VALID_DESCRIPTION_DATASCIENTIST).build();

        EditPositionCommand expectedCommand = new EditPositionCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_POSITION;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_DATASCIENTIST + TITLE_DESC_DATAENGINEER;

        EditPositionDescriptor descriptor = new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_DATAENGINEER)
                .build();
        EditPositionCommand expectedCommand = new EditPositionCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        Index targetIndex = INDEX_FIRST_POSITION;
        String userInput = targetIndex.getOneBased() + INVALID_TITLE_DESC + TITLE_DESC_DATAENGINEER;

        EditPositionDescriptor descriptor = new EditPositionDescriptorBuilder().withTitle(VALID_TITLE_DATAENGINEER)
                .build();
        EditPositionCommand expectedCommand = new EditPositionCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }




}
