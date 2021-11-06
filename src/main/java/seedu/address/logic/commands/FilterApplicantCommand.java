package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.descriptors.FilterApplicantDescriptor;
import seedu.address.logic.descriptors.FilterApplicantDescriptorVerifier;
import seedu.address.model.Model;
import seedu.address.model.applicant.ApplicantMatchesFiltersPredicate;

/*
 * Filters the current list of applicants by the specified criteria.
 */
public class FilterApplicantCommand extends Command {

    public static final String COMMAND_WORD = "filter-applicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters the current list of applicants by the specified criteria. " + "\n"
            + "Parameters: "
            + "[" + PREFIX_POSITION + "POSITION] "
            + "[" + PREFIX_STATUS + "STATUS] " + "\n"
            + "Example: " + COMMAND_WORD
            + PREFIX_POSITION + "software engineer "
            + PREFIX_STATUS + "pending";

    public static final String MESSAGE_SUCCESS = "Applicants filtered by: %1$s";
    public static final String MESSAGE_INVALID_FILTER = "The following filters are invalid: %1$s";

    private final FilterApplicantDescriptor filterApplicantDescriptor;

    /**
     * Creates a FilterApplicantCommand according to the specified {@code filterApplicantDescriptor}.
     */
    public FilterApplicantCommand(FilterApplicantDescriptor filterApplicantDescriptor) {
        requireNonNull(filterApplicantDescriptor);
        this.filterApplicantDescriptor = new FilterApplicantDescriptor(filterApplicantDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        FilterApplicantDescriptor invalidFilterApplicantDescriptor =
                new FilterApplicantDescriptorVerifier(model, filterApplicantDescriptor);
        if (invalidFilterApplicantDescriptor.hasAnyFilter()) {
            throw new CommandException(String.format(MESSAGE_INVALID_FILTER, invalidFilterApplicantDescriptor));
        }

        model.updateFilteredApplicantList(new ApplicantMatchesFiltersPredicate(filterApplicantDescriptor));

        return new CommandResult(String.format(MESSAGE_SUCCESS, filterApplicantDescriptor));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterApplicantCommand // instanceof handles nulls
                && filterApplicantDescriptor.equals(((FilterApplicantCommand) other).filterApplicantDescriptor));
    }

}
