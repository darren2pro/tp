package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;

/**
 * Deletes an applicant identified using the displayed index in MrTechRecruiter.
 */
public class DeleteApplicantCommand extends Command {

    public static final String COMMAND_WORD = "delete-applicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the applicant identified by the index number used in the displayed applicant list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_APPLICANT_SUCCESS = "Deleted applicant: %1$s";

    private final Index targetIndex;

    public DeleteApplicantCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }

        Applicant applicantToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteApplicant(applicantToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_APPLICANT_SUCCESS, applicantToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteApplicantCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteApplicantCommand) other).targetIndex)); // state check
    }

}
