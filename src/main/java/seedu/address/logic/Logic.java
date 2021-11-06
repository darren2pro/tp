package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.position.Position;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;


    /** Returns an unmodifiable view of the filtered list of positions */
    ObservableList<Position> getFilteredPositionList();


    /** Returns an unmodifiable view of the filtered list of applicants */
    ObservableList<Applicant> getFilteredApplicantList();

    /**
     * Returns the user prefs' applicant book file path.
     */
    Path getApplicantBookFilePath();

    /**
     * Returns the user prefs' position book file path.
     */
    Path getPositionBookFilePath(); // this file paths might need to be combined.


    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
