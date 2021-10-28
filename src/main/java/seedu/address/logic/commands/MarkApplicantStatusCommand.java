package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.model.applicant.Application.ApplicationStatus;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.exceptions.ApplicantNotFoundException;

/**
 * Updates an applicant's application status.
 * The specifying of the status is case-insensitive, but must otherwise match exactly.
 */
public class MarkApplicantStatusCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Updates an applicant's application status as specified. "
            + "Parameters: NAME " + PREFIX_STATUS + "STATUS\n"
            + "Example: " + COMMAND_WORD + " John Doe " + PREFIX_STATUS + "accepted";

    public static final String MESSAGE_MARK_APPLICANT_STATUS_SUCCESS =
            "Updated applicant '%1$s''s status to: %2$s.";
    public static final String MESSAGE_NO_SUCH_APPLICANT_WITH_NAME =
            "There is no applicant with name: '%1$s' in MrTechRecruiter.";

    private final Name name;
    private final ApplicationStatus applicationStatus;

    /**
     * Creates a MarkApplicantStatusCommand to update the status of the specified applicant.
     */
    public MarkApplicantStatusCommand(Name name, ApplicationStatus applicationStatus) {
        requireAllNonNull(name, applicationStatus);
        this.name = name;
        this.applicationStatus = applicationStatus;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Applicant applicantToUpdate;
        try {
            applicantToUpdate = model.getApplicantByNameIgnoreCase(name);
        } catch (ApplicantNotFoundException e) {
            throw new CommandException(String.format(MESSAGE_NO_SUCH_APPLICANT_WITH_NAME, name));
        }

        memento.record(model.getCopiedModel());

        model.setApplicant(applicantToUpdate, applicantToUpdate.markAs(applicationStatus));

        String successMessage =
                String.format(MESSAGE_MARK_APPLICANT_STATUS_SUCCESS, applicantToUpdate.getName(), applicationStatus);
        memento.recordMessage(successMessage);

        model.addToHistory(this);

        return new CommandResult(successMessage);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkApplicantStatusCommand)) {
            return false;
        }

        // state check
        MarkApplicantStatusCommand o = (MarkApplicantStatusCommand) other;
        return name.equals(o.name) && applicationStatus.equals(o.applicationStatus);
    }

}
