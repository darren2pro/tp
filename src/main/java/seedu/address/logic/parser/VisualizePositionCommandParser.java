package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.VisualizePositionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.position.Title;

/**
 * Parses input arguments and creates a new VisualizePositionCommand object
 */
public class VisualizePositionCommandParser implements Parser<VisualizePositionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the VisualizePositionCommand
     * and returns a VisualizePositionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public VisualizePositionCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Title titleToShow;
        try {
            titleToShow = ParserUtil.parseTitle(args);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, VisualizePositionCommand.MESSAGE_USAGE));
        }

        return new VisualizePositionCommand(titleToShow);
    }

}
