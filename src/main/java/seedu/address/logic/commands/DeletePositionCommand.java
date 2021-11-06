package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.position.Position;

/**
 * Deletes a position identified using it's displayed index from the position book.
 */
public class DeletePositionCommand extends Command {

    public static final String COMMAND_WORD = "delete-position";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the position identified by the index number used in the displayed position list." + "\n"
            + "Parameters: INDEX (must be a positive integer)" + "\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_POSITION_SUCCESS = "Deleted Position: %1$s";

    private final Index targetIndex;

    public DeletePositionCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Position> lastShownList = model.getFilteredPositionList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_POSITION_DISPLAYED_INDEX);
        }

        memento.record(model.getCopiedModel());

        Position positionToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePosition(positionToDelete);

        String successMessage = String.format(MESSAGE_DELETE_POSITION_SUCCESS, positionToDelete);
        memento.recordMessage(successMessage);

        model.addToHistory(this);

        return new CommandResult(successMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePositionCommand // instanceof handles nulls
                && targetIndex.equals(((DeletePositionCommand) other).targetIndex)); // state check
    }
}
