package seedu.address.testutil;

import seedu.address.model.applicant.Application;
import seedu.address.model.applicant.Application.ApplicationStatus;
import seedu.address.model.position.Position;

public class ApplicationBuilder {
    private Position position;
    private ApplicationStatus applicationStatus;

    /**
     * Creates a {@code ApplicationBuilder} with the default details.
     */
    public ApplicationBuilder() {
        position = new PositionBuilder().build();
        applicationStatus = ApplicationStatus.PENDING;
    }

    /**
     * Initializes the ApplicationBuilder with the data of {@code applicationToCopy}.
     */
    public ApplicationBuilder(Application applicationToCopy) {
        position = applicationToCopy.getPosition();
        applicationStatus = applicationToCopy.getStatus();
    }

    /**
     * Sets the {@code position} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withPosition(Position position) {
        this.position = position;
        return this;
    }

    /**
     * Sets the {@code ApplicationStatus} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
        return this;
    }

    public Application build() {
        return new Application(position, applicationStatus);
    }

}
