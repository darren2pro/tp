package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.AddApplicantCommand.MESSAGE_NO_SUCH_POSITION;
import static seedu.address.logic.commands.RejectionRateCommand.MESSAGE_NO_CURRENT_APPLICANTS;

import javafx.scene.chart.PieChart;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;
import seedu.address.model.position.exceptions.PositionNotFoundException;
import seedu.address.ui.PieChartDisplayer;
import seedu.address.ui.PositionPieChart;

/*
 * Displays a pie chart for a position in MrTechRecruiter.
 */
public class VisualizePositionCommand extends Command {

    public static final String COMMAND_WORD = "visualize";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays a visual representation of a position. " + "\n"
            + "Parameters: " + "TITLE " + "\n"
            + "Example: " + COMMAND_WORD + " " + "software engineer";

    public static final String MESSAGE_SUCCESS = "Position chart displayed: %1$s";

    private final Title toShowTitle;

    /**
     * Creates a VisualizePositionCommand to visualize the specified {@code Position}
     */
    public VisualizePositionCommand(Title position) {
        requireNonNull(position);
        toShowTitle = position;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Position position;
        try {
            position = model.getPositionWithTitle(toShowTitle);
        } catch (PositionNotFoundException e) {
            throw new CommandException(MESSAGE_NO_SUCH_POSITION);
        }

        assert position != null; // Above try-catch block handles the 'Position not found' case

        if (!model.hasApplicantsApplyingTo(position)) {
            throw new CommandException(MESSAGE_NO_CURRENT_APPLICANTS);
        }

        PieChart positionChart = new PositionPieChart(model.getFilteredApplicantList(), position);
        PieChartDisplayer positionChartDisplayer = new PieChartDisplayer(positionChart);
        positionChartDisplayer.displayPieChart();

        return new CommandResult(String.format(MESSAGE_SUCCESS, position.getTitle()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VisualizePositionCommand // instanceof handles nulls
                && toShowTitle.equals(((VisualizePositionCommand) other).toShowTitle));
    }

}
