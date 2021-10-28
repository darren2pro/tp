package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimap.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.logic.commands.MarkApplicantStatusCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.Application.ApplicationStatus;
import seedu.address.model.applicant.Name;

/**
 * Parses input arguments and creates a new MarkApplicantStatusCommand object
 */
public class MarkApplicantStatusCommandParser implements Parser<MarkApplicantStatusCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkApplicantStatusCommand
     * and returns a MarkApplicantStatusCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkApplicantStatusCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STATUS);

        if (!arePrefixesPresent(argMultimap, PREFIX_STATUS) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkApplicantStatusCommand.MESSAGE_USAGE));
        }

        Name name = ApplicantParserUtil.parseName(argMultimap.getPreamble());
        ApplicationStatus applicationStatus =
                ApplicantParserUtil.parseApplicationStatus(argMultimap.getValue(PREFIX_STATUS).get());

        return new MarkApplicantStatusCommand(name, applicationStatus);
    }

}
