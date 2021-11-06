package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.position.Position;

// Adds a position to MrTechRecruiter.
public class AddPositionCommand extends Command {

    public static final String COMMAND_WORD = "add-position";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a position to the MTR system. " + "\n"
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_DESCRIPTION + "DESCRIPTION " + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "software engineer "
            + PREFIX_DESCRIPTION + "writes code everyday :/";


    public static final String MESSAGE_SUCCESS = "New position added: %1$s";
    public static final String MESSAGE_DUPLICATE_POSITION = "This position already exists in the MTR system";

    private final Position toAdd;

    /**
     * Creates an AddPositionCommand to add the specified {@code Position}
     */
    public AddPositionCommand(Position position) {
        requireNonNull(position);
        toAdd = position;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPosition(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_POSITION);
        }

        memento.record(model.getCopiedModel());

        model.addPosition(toAdd);

        String successMessage = String.format(MESSAGE_SUCCESS, toAdd);
        memento.recordMessage(successMessage);

        model.addToHistory(this);

        return new CommandResult(successMessage);
    }



    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPositionCommand // instanceof handles nulls
                && toAdd.equals(((AddPositionCommand) other).toAdd));
    }
}
