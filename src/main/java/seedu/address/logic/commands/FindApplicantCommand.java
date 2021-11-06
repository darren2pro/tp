package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.applicant.NameContainsKeywordsPredicate;

/**
 * Finds and lists all applicants in MTR whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindApplicantCommand extends Command {

    public static final String COMMAND_WORD = "find-applicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all applicants whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers." + "\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]..." + "\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    public FindApplicantCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredApplicantList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW, model.getFilteredApplicantList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindApplicantCommand // instanceof handles nulls
                && predicate.equals(((FindApplicantCommand) other).predicate)); // state check
    }

}
