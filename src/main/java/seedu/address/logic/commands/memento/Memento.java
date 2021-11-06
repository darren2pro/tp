package seedu.address.logic.commands.memento;

import seedu.address.model.Model;

/**
 * Keeps a {@code model} when a modification command executes.
 * The use of Memento pattern is adapted from
 * https://stackoverflow.com/questions/11530276/how-do-i-implement-a-simple-undo-redo-for-actions-in-java.
 */
public class Memento {
    private Model model;
    private String message;

    public Memento() {}

    public void record(Model model) {
        this.model = model;
    }

    public void recordMessage(String message) {
        this.message = message;
    }

    public Model getModel() {
        return model;
    }

    public String getMessage() {
        return message;
    }
}
