package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
 * Displays its given pie chart in a new window.
 */
public class PieChartDisplayer {

    private final PieChart pieChart;

    /**
     * Constructor for an object which displays a given pie chart.
     */
    public PieChartDisplayer(PieChart pieChart) {
        requireNonNull(pieChart);
        this.pieChart = pieChart;
    }

    /**
     * Displays the pie chart in a new window.
     * When the window loses focus, it is closed.
     */
    public void displayPieChart() {
        StackPane root = new StackPane();
        root.getChildren().add(pieChart);

        Stage stage = new Stage();
        stage.setScene(new Scene(root, 450, 450));
        setCloseOnLoseFocus(stage);

        stage.show();
    }

    private void setCloseOnLoseFocus(Stage stage) {
        stage.focusedProperty().addListener((i, j, k) -> {
            if (!stage.isFocused()) {
                Platform.runLater(stage::close);
            }
        });
    }

}
