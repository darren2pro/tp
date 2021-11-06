package seedu.address.model.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.position.exceptions.DuplicatePositionException;
import seedu.address.model.position.exceptions.PositionNotFoundException;


/**
 * A list of positions that enforces uniqueness between its elements and does not allow nulls.
 * A position is considered unique by comparing using {@code Position#isSamePosition(Position)}.
 * As such, adding and updating of positions uses Position#isSamePosition(Position) for equality
 * so as to ensure that the position being added or updated is
 * unique in terms of identity in the UniquePositionList.
 * However, the removal of a position uses Position#equals(Object) so
 * as to ensure that the position with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Position#isSamePosition(Position)
 */
public class UniquePositionList implements Iterable<Position> {
    private final ObservableList<Position> internalList = FXCollections.observableArrayList();
    private final ObservableList<Position> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent position as the given argument.
     */
    public boolean contains(Position toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePosition);
    }

    /**
     * Returns true if the list contains a position titled {@code toCheck}.
     */
    public boolean containsPositionWithTitle(Title toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(position -> position.getTitle().equals(toCheck));
    }

    /**
     * Adds a position to the list.
     * The position must not already exist in the list.
     */
    public void add(Position toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePositionException();
        }
        internalList.add(toAdd);
    }

    /**
     * Searches for a position with the given {@code title}.
     */
    public Position getPositionWithTitle(Title title) {
        requireNonNull(title);
        return internalList.stream()
                .filter(position -> position.getTitle().equals(title))
                .findFirst()
                .orElseThrow(PositionNotFoundException::new);
    }

    /**
     * Replaces the position {@code target} in the list with {@code editedPosition}.
     * {@code target} must exist in the list.
     * The position identity of {@code editedPosition} must not be the same as another existing position in the list.
     */
    public void setPosition(Position target, Position editedPosition) {
        requireAllNonNull(target, editedPosition);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PositionNotFoundException();
        }

        if (!target.isSamePosition(editedPosition) && contains(editedPosition)) {
            throw new DuplicatePositionException();
        }

        internalList.set(index, editedPosition);
    }

    /**
     * Removes the equivalent position from the list.
     * The position must exist in the list.
     */
    public void remove(Position toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PositionNotFoundException();
        }
    }

    public void setPositions(UniquePositionList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code positions}.
     * {@code positions} must not contain duplicate positions.
     */
    public void setPositions(List<Position> positions) {
        requireAllNonNull(positions);
        if (!positionsAreUnique(positions)) {
            throw new DuplicatePositionException();
        }

        internalList.setAll(positions);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Position> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }




    @Override
    public Iterator<Position> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePositionList // instanceof handles nulls
                && internalList.equals(((UniquePositionList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code positions} contains only unique positions.
     */
    private boolean positionsAreUnique(List<Position> positions) {
        for (int i = 0; i < positions.size() - 1; i++) {
            for (int j = i + 1; j < positions.size(); j++) {
                if (positions.get(i).isSamePosition(positions.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public UniquePositionList getCopiedPositions() {
        UniquePositionList copiedPositions = new UniquePositionList();
        for (Position position : internalList) {
            copiedPositions.internalList.add(position.getCopiedPosition());
        }

        return copiedPositions;
    }


}
