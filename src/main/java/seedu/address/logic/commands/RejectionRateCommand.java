package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.AddApplicantCommand.MESSAGE_NO_SUCH_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Calculator;
import seedu.address.model.Model;
import seedu.address.model.position.Title;

/**
 * Returns the rejection rate of a given position to the user.
 */
public class RejectionRateCommand extends Command {

    public static final String COMMAND_WORD = "rate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Returns the rejection rate of position specified." + "\n"
            + "Parameters: "
            + PREFIX_POSITION + "POSITION" + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_POSITION + "software engineer";

    public static final String MESSAGE_SUCCESS = "Rejection rate for %1$s = %2$.2f%%";

    public static final String MESSAGE_NO_CURRENT_APPLICANTS = "There are currently no applicants for this position.";

    private final Title positionTitle;

    /**
     * Creates an RejectionRateCommand to get the specified rejection rate.
     */
    public RejectionRateCommand(Title positionTitle) {
        requireNonNull(positionTitle);
        this.positionTitle = positionTitle;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPositionWithTitle(positionTitle)) {
            throw new CommandException(MESSAGE_NO_SUCH_POSITION);
        }

        double rejectionRate = model.calculateRejectionRate(positionTitle);

        if (rejectionRate == Calculator.INVALID_REJECTION_RATE) {
            return new CommandResult(MESSAGE_NO_CURRENT_APPLICANTS);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, positionTitle, rejectionRate));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RejectionRateCommand // instanceof handles nulls
                && positionTitle.equals(((RejectionRateCommand) other).positionTitle));
    }
}
