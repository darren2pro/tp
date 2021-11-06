package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_POSITIONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.descriptors.EditPositionDescriptor;
import seedu.address.model.Model;
import seedu.address.model.position.Position;



public class EditPositionCommand extends Command {

    public static final String COMMAND_WORD = "edit-position";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the position identified "
            + "by the index number used in the displayed position list. "
            + "Existing values will be overwritten by the input values." + "\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] " + "\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "Create data pipeline";

    public static final String MESSAGE_EDIT_POSITION_SUCCESS = "Edited Position: %1$s";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    public static final String MESSAGE_DUPLICATE_POSITION = "This position already exists in the position book.";

    private final Index index;

    private final EditPositionDescriptor editPositionDescriptor;

    /**
     * @param index of the position in the filtered position list to edit
     * @param editPositionDescriptor details to edit the position with
     */
    public EditPositionCommand(Index index, EditPositionDescriptor editPositionDescriptor) {
        requireNonNull(index);
        requireNonNull(editPositionDescriptor);

        this.index = index;
        this.editPositionDescriptor = new EditPositionDescriptor(editPositionDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Position> lastShownList = model.getFilteredPositionList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_POSITION_DISPLAYED_INDEX);
        }

        Position positionToEdit = lastShownList.get(index.getZeroBased());
        Position editedPosition = editPositionDescriptor.createEditedPosition(positionToEdit);

        if (!positionToEdit.isSamePosition(editedPosition) && model.hasPosition(editedPosition)) {
            throw new CommandException(MESSAGE_DUPLICATE_POSITION);
        }

        memento.record(model.getCopiedModel());

        model.setPosition(positionToEdit, editedPosition);
        // when a position is edited, the position information in applicants should also be modified
        model.updateApplicantsWithPosition(positionToEdit, editedPosition);
        model.updateFilteredPositionList(PREDICATE_SHOW_ALL_POSITIONS);

        String successMessage = String.format(MESSAGE_EDIT_POSITION_SUCCESS, editedPosition);
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
        if (!(other instanceof EditPositionCommand)) {
            return false;
        }

        // state check
        EditPositionCommand e = (EditPositionCommand) other;
        return index.equals(e.index)
                && editPositionDescriptor.equals(e.editPositionDescriptor);
    }

}
