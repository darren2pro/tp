package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.logic.commands.FilterApplicantCommand;
import seedu.address.logic.descriptors.FilterApplicantDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FilterApplicantCommand object
 */
public class FilterApplicantCommandParser implements Parser<FilterApplicantCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterApplicantCommand
     * and returns an FilterApplicantCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterApplicantCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_POSITION, PREFIX_STATUS);

        FilterApplicantDescriptor filterApplicantDescriptor = new FilterApplicantDescriptor();
        if (argMultimap.getValue(PREFIX_POSITION).isPresent()) {
            filterApplicantDescriptor.setPositionTitle(
                    ParserUtil.parseTitle(argMultimap.getValue(PREFIX_POSITION).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            filterApplicantDescriptor.setApplicationStatus(
                    ParserUtil.parseApplicationStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }

        if (!filterApplicantDescriptor.hasAnyFilter()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FilterApplicantCommand.MESSAGE_USAGE));
        }

        return new FilterApplicantCommand(filterApplicantDescriptor);
    }

}
