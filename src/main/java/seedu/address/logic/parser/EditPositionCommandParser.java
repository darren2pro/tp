package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditPositionCommand;
import seedu.address.logic.descriptors.EditPositionDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;



public class EditPositionCommandParser implements Parser<EditPositionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditPositionCommand
     * and returns an EditPositionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditPositionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DESCRIPTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditPositionCommand.MESSAGE_USAGE), pe);
        }

        EditPositionDescriptor editPositionDescriptor = new EditPositionDescriptor();
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editPositionDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editPositionDescriptor.setDescription(ParserUtil.parseDescription(
                    argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }

        if (!editPositionDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditPositionCommand.MESSAGE_NOT_EDITED);
        }

        return new EditPositionCommand(index, editPositionDescriptor);
    }


}
