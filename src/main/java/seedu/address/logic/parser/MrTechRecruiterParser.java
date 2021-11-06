package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddApplicantCommand;
import seedu.address.logic.commands.AddPositionCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteApplicantCommand;
import seedu.address.logic.commands.DeletePositionCommand;
import seedu.address.logic.commands.EditApplicantCommand;
import seedu.address.logic.commands.EditPositionCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterApplicantCommand;
import seedu.address.logic.commands.FindApplicantCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListApplicantCommand;
import seedu.address.logic.commands.ListPositionCommand;
import seedu.address.logic.commands.MarkApplicantStatusCommand;
import seedu.address.logic.commands.RejectionRateCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.VisualizePositionCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class MrTechRecruiterParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddApplicantCommand.COMMAND_WORD:
            return new AddApplicantCommandParser().parse(arguments);

        case DeleteApplicantCommand.COMMAND_WORD:
            return new DeleteApplicantCommandParser().parse(arguments);

        case ListApplicantCommand.COMMAND_WORD:
            return new ListApplicantCommand();

        case FilterApplicantCommand.COMMAND_WORD:
            return new FilterApplicantCommandParser().parse(arguments);

        case AddPositionCommand.COMMAND_WORD:
            return new AddPositionCommandParser().parse(arguments);

        case ListPositionCommand.COMMAND_WORD:
            return new ListPositionCommand();

        case DeletePositionCommand.COMMAND_WORD:
            return new DeletePositionCommandParser().parse(arguments);

        case RejectionRateCommand.COMMAND_WORD:
            return new RejectionRateCommandParser().parse(arguments);

        case EditPositionCommand.COMMAND_WORD:
            return new EditPositionCommandParser().parse(arguments);

        case EditApplicantCommand.COMMAND_WORD:
            return new EditApplicantCommandParser().parse(arguments);

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case FindApplicantCommand.COMMAND_WORD:
            return new FindApplicantCommandParser().parse(arguments);

        case VisualizePositionCommand.COMMAND_WORD:
            return new VisualizePositionCommandParser().parse(arguments);

        case MarkApplicantStatusCommand.COMMAND_WORD:
            return new MarkApplicantStatusCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
