package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandUndoException;
import seedu.address.model.Model;

/**
 * Undoes the previous modification to MTR.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Undid the previous modification : \n";

    @Override
    public CommandResult execute(Model model) throws CommandUndoException {
        requireNonNull(model);

        if (!model.hasHistory()) {
            throw new CommandUndoException("Cannot undo because there is no record.");
        }

        String previousMessages = model.recoverHistory();
        return new CommandResult(MESSAGE_SUCCESS + previousMessages);
    }

    @Override
    public boolean equals(Object other) {
        return other == this;
    }
}
