package seedu.address.testutil;

import static seedu.address.testutil.TypicalPositions.DATAENGINEER;
import static seedu.address.testutil.TypicalPositions.DATASCIENTIST;

import seedu.address.model.applicant.Application;


public class TypicalApplications {

    public static final Application DATAENGINEER_APPLICATION =
            new ApplicationBuilder().withPosition(DATAENGINEER).build();

    public static final Application DATASCIENTIST_APPLICATION =
            new ApplicationBuilder().withPosition(DATASCIENTIST).build();

    private TypicalApplications() {} // prevents instantiation

}
