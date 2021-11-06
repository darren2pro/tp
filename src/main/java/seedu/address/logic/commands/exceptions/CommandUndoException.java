package seedu.address.logic.commands.exceptions;

/**
 * Represents an error which occurs during unexecution of a {@link CommandException}.
 */
public class CommandUndoException extends CommandException {
    public CommandUndoException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code CommandUndoException} with the specified detail {@code message} and {@code cause}.
     */
    public CommandUndoException(String message, Throwable cause) {
        super(message, cause);
    }
}

