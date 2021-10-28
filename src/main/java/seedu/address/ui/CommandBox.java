package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";
    private static final int MAX_HIST_LEN = 10;

    private final CommandExecutor commandExecutor;
    private final List<String> commandHistory;
    private String savedInput;
    private int currHistIndex;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.commandHistory = new ArrayList<>();
        this.currHistIndex = -1;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        commandTextField.setOnKeyPressed(keyEvent -> {
            KeyCode keyPressed = keyEvent.getCode();
            if (keyPressed == KeyCode.UP || keyPressed == KeyCode.DOWN) {
                handleHistoryNavigation(keyPressed);
            }
        });
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        addToHistory(commandText);

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    private void handleHistoryNavigation(KeyCode keyPressed) {
        if (currHistIndex == -1) {
            savedInput = commandTextField.getText();
        }

        if (keyPressed == KeyCode.UP && currHistIndex < commandHistory.size() - 1) {
            currHistIndex++;
        } else if (keyPressed == KeyCode.DOWN && currHistIndex >= 0) {
            currHistIndex--;
        }

        if (currHistIndex == -1) {
            commandTextField.setText(savedInput);
        } else {
            commandTextField.setText(commandHistory.get(currHistIndex));
        }
    }

    private void addToHistory(String commandText) {
        if (commandHistory.size() == MAX_HIST_LEN) {
            commandHistory.remove(MAX_HIST_LEN - 1);
        }
        commandHistory.add(0, commandText);
        currHistIndex = -1;
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
