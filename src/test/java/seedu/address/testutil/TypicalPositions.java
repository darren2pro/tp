package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DATAENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DATASCIENTIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SOFTWAREARCHITECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DATAENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DATASCIENTIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_SOFTWAREARCHITECT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.PositionBook;
import seedu.address.model.position.Position;


public class TypicalPositions {

    public static final Position DATAENGINEER = new PositionBuilder().withTitle(VALID_TITLE_DATAENGINEER)
            .withDescription(VALID_DESCRIPTION_DATAENGINEER).build();

    public static final Position DATASCIENTIST = new PositionBuilder().withTitle(VALID_TITLE_DATASCIENTIST)
            .withDescription(VALID_DESCRIPTION_DATASCIENTIST).build();

    public static final Position SOFTWAREARCHITECT = new PositionBuilder().withTitle(VALID_TITLE_SOFTWAREARCHITECT)
            .withDescription(VALID_DESCRIPTION_SOFTWAREARCHITECT).build();

    private TypicalPositions() {} // prevents instantiation

    /**
     * Returns an {@code PositionBook} with all the typical positions.
     */
    public static PositionBook getTypicalPositionBook() {
        PositionBook pb = new PositionBook();
        for (Position position : getTypicalPositions()) {
            pb.addPosition(position);
        }
        return pb;
    }


    public static List<Position> getTypicalPositions() {
        return new ArrayList<>(Arrays.asList(DATAENGINEER, DATASCIENTIST));
    }

}
