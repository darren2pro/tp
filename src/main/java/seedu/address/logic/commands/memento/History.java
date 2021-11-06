package seedu.address.logic.commands.memento;

import java.util.Stack;

import seedu.address.logic.commands.Command;


/**
 * Records the command history.
 * Refers to https://stackoverflow.com/questions/11530276/how-do-i-implement-a-simple-undo-redo-for-actions-in-java
 */
public class History {
    private Stack<Command> commandStack = new Stack<>();


    public void add(Command command) {
        commandStack.push(command);
    }

    public boolean hasHistory() {
        return commandStack.size() > 0;
    }

    /**
     * Returns the previous memento.
     */
    public Memento recoverHistory() {
        assert hasHistory() : "There is no history to recover.";
        Command previousCommand = commandStack.pop();
        return previousCommand.getMemento();
    }


}
