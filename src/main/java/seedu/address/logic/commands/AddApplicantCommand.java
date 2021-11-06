package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB_PROFILE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.ApplicantParticulars;

/**
 * Adds an applicant to the address book.
 */
public class AddApplicantCommand extends Command {

    public static final String COMMAND_WORD = "add-applicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an applicant to MrTechRecruiter. " + "\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_POSITION + "POSITION "
            + PREFIX_GITHUB_PROFILE + "GITHUB_PROFILE " + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_POSITION + "software engineer"
            + PREFIX_GITHUB_PROFILE + "https://github.com/empty";

    public static final String MESSAGE_SUCCESS = "New applicant added: %1$s.";
    public static final String MESSAGE_DUPLICATE_APPLICANT = "This applicant already exists in MrTechRecruiter.";
    public static final String MESSAGE_NO_SUCH_POSITION = "There is no such position in MrTechRecruiter.";

    private final ApplicantParticulars applicantParticulars;

    /**
     * Creates an AddApplicantCommand to add the specified {@code Applicant}
     */
    public AddApplicantCommand(ApplicantParticulars applicantParticulars) {
        requireNonNull(applicantParticulars);
        this.applicantParticulars = applicantParticulars;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasApplicantWithName(applicantParticulars.getName())) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICANT);
        }

        if (!model.hasPositionWithTitle(applicantParticulars.getPositionTitle())) {
            throw new CommandException(MESSAGE_NO_SUCH_POSITION);
        }

        memento.record(model.getCopiedModel());
        Applicant applicant = model.addApplicantWithParticulars(applicantParticulars);
        String successMessage = String.format(MESSAGE_SUCCESS, applicant);
        memento.recordMessage(successMessage);

        model.addToHistory(this);

        return new CommandResult(successMessage);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddApplicantCommand // instanceof handles nulls
                && applicantParticulars.equals(((AddApplicantCommand) other).applicantParticulars));
    }
}
