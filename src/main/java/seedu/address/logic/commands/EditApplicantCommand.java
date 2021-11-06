package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB_PROFILE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.descriptors.EditApplicantDescriptor;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;



public class EditApplicantCommand extends Command {

    public static final String COMMAND_WORD = "edit-applicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the applicant identified "
            + "by the index number used in the displayed applicant list. "
            + "Existing values will be overwritten by the input values." + "\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_POSITION + "POSITION] " + "\n"
            + "[" + PREFIX_GITHUB_PROFILE + "GITHUB_PROFILE]"
            + "Example: " + COMMAND_WORD + " 2 "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com ";

    public static final String MESSAGE_EDIT_APPLICANT_SUCCESS = "Edited Applicant: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPLICANT = "This applicant already exists in the applicant book.";
    public static final String MESSAGE_NO_SUCH_POSITION = "There is no position '%1$s' in MrTechRecruiter.";

    private final Index index;
    private final EditApplicantDescriptor editApplicantDescriptor;

    /**
     * @param index of the applicant in the filtered applicant list to edit
     * @param editApplicantDescriptor details to edit the applicant with
     */
    public EditApplicantCommand(Index index, EditApplicantDescriptor editApplicantDescriptor) {
        requireNonNull(index);
        requireNonNull(editApplicantDescriptor);

        this.index = index;
        this.editApplicantDescriptor = new EditApplicantDescriptor(editApplicantDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }

        if (editApplicantDescriptor.getTitle().isPresent()
                && !model.hasPositionWithTitle(editApplicantDescriptor.getTitle().get())) {
            throw new CommandException(String.format(MESSAGE_NO_SUCH_POSITION,
                    editApplicantDescriptor.getTitle().get()));
        }

        Applicant applicantToEdit = lastShownList.get(index.getZeroBased());
        Applicant editedApplicant = editApplicantDescriptor.createEditedApplicant(applicantToEdit, model);

        if (!applicantToEdit.isSameApplicant(editedApplicant) && model.hasApplicant(editedApplicant)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICANT);
        }

        if (applicantToEdit.equals(editedApplicant)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICANT);
        }

        memento.record(model.getCopiedModel());

        model.setApplicant(applicantToEdit, editedApplicant);
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);

        String successMessage = String.format(MESSAGE_EDIT_APPLICANT_SUCCESS, editedApplicant);
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
        if (!(other instanceof EditApplicantCommand)) {
            return false;
        }

        // state check
        EditApplicantCommand e = (EditApplicantCommand) other;
        return index.equals(e.index)
                && editApplicantDescriptor.equals(e.editApplicantDescriptor);
    }

}
