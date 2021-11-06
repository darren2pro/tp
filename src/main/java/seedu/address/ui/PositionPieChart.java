package seedu.address.ui;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Application.ApplicationStatus;
import seedu.address.model.applicant.ApplyingToPositionPredicate;
import seedu.address.model.position.Position;

/**
 * A pie chart representing a job position and the breakdown of its applicants.
 */
public class PositionPieChart extends PieChart {

    private final Position position;

    /**
     * Constructor for a {@code PositionPieChart}, which takes an {@code ObservableList} of applicants
     * under a specified position.
     */
    public PositionPieChart(ObservableList<Applicant> applicants, Position position) {
        super(createPieChart(requireNonNull(applicants)
                .filtered(new ApplyingToPositionPredicate(requireNonNull(position)))));
        this.position = position;
        setStyling();
        installTooltips();
    }

    /**
     * Internal method to create a pie chart, given a list of applicants under a single position.
     */
    private static ObservableList<PieChart.Data> createPieChart(ObservableList<Applicant> applicantsUnderPosition) {
        requireNonNull(applicantsUnderPosition);

        int applicantCount = applicantsUnderPosition.size();
        return Arrays.stream(ApplicationStatus.values())
                .map(status -> createPieChartData(applicantsUnderPosition, applicantCount, status))
                .filter(pieChartData -> pieChartData.getPieValue() != 0)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    /**
     * Internal method to create a {@code PieChart.Data} object from a given application status.
     */
    private static PieChart.Data createPieChartData(
            ObservableList<Applicant> applicants, int applicantCount, ApplicationStatus status) {
        requireAllNonNull(applicants, status);

        int applicantsWithStatusCount = applicants.filtered(applicant ->
                applicant.hasApplicationStatus(status)).size();
        double percentage = (double) applicantsWithStatusCount * 100 / applicantCount;

        return new PieChart.Data(status.toString(), percentage);
    }

    private void setStyling() {
        String properCaseTitle = StringUtil.toProperCase(position.getTitle().fullTitle);
        setTitle(properCaseTitle);

        setLabelsVisible(true);
    }

    private void installTooltips() {
        getData().forEach(data -> {
            Tooltip tooltip = new Tooltip();
            tooltip.setText(String.format("%.1f%%", data.getPieValue()));
            Tooltip.install(data.getNode(), tooltip);
        });
    }

}
