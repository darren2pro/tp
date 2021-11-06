package seedu.address.model;

/**
 * This singleton class deals with calculating the rejection rate of a specified position only.
 */
public class Calculator {
    public static final int INVALID_REJECTION_RATE = -1;

    public static final int TO_PERCENTAGE = 100;

    private Calculator() {}

    /**
     * Calculates the rejection rate.
     * @param totalApplied Total number of applicants for a position.
     * @param totalRejected Total number of rejected applicants for a position.
     * @return A floating point number representing the rejection rate.
     */
    public static float calculateRejectionRate(int totalApplied, int totalRejected) {
        if (totalApplied == 0) {
            return INVALID_REJECTION_RATE;
        }
        return (float) totalRejected / totalApplied * TO_PERCENTAGE;
    }
}
