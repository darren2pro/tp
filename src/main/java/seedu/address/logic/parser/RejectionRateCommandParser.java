package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimap.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;

import seedu.address.logic.commands.RejectionRateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.position.Title;


/**
 * Parses input arguments and creates a new RejectionRateCommand object
 */
public class RejectionRateCommandParser implements Parser<RejectionRateCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RejectionRateCommand
     * and returns a RejectionRateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RejectionRateCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_POSITION);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_POSITION)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RejectionRateCommand.MESSAGE_USAGE));
        }

        Title positionTitle = ParserUtil.parseTitle(argumentMultimap.getValue(PREFIX_POSITION).get());

        return new RejectionRateCommand(positionTitle);
    }

}
