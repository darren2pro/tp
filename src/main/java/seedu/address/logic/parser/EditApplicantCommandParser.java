package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB_PROFILE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditApplicantCommand;
import seedu.address.logic.descriptors.EditApplicantDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.ProfileUrl;
import seedu.address.model.position.Title;


public class EditApplicantCommandParser implements Parser<EditApplicantCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditApplicantCommand
     * and returns an EditApplicantCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public EditApplicantCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_POSITION, PREFIX_GITHUB_PROFILE);

        Index index;
        Title positionTitle;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditApplicantCommand.MESSAGE_USAGE), pe);
        }

        EditApplicantDescriptor editApplicantDescriptor = new EditApplicantDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editApplicantDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editApplicantDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editApplicantDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editApplicantDescriptor.setAddress(ParserUtil
                    .parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_POSITION).isPresent()) {
            positionTitle = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_POSITION).get());
            editApplicantDescriptor.setTitle(positionTitle);
        }
        if (argMultimap.getValue(PREFIX_GITHUB_PROFILE).isPresent()) {
            ProfileUrl githubProfile = new ProfileUrl(argMultimap.getValue(PREFIX_GITHUB_PROFILE).get());
            editApplicantDescriptor.setGitHubProfile(githubProfile);
        }

        if (!editApplicantDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditApplicantCommand.MESSAGE_NOT_EDITED);
        }

        return new EditApplicantCommand(index, editApplicantDescriptor);
    }

}
