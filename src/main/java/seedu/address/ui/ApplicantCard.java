package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.ProfileUrl;

/**
 * A UI component that displays information of a {@code Applicant}.
 */
public class ApplicantCard extends UiPart<Region> {
    private static final String FXML = "ApplicantListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Applicant applicant;
    private ProfileUrl gitHubUrl;
    private ProfileUrl linkedInUrl;

    @FXML
    private ImageView gitHubLogo;
    @FXML
    private ImageView linkedInLogo;
    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label application;
    @FXML
    private AnchorPane hyperlinkAnchorPane;
    @FXML
    private Hyperlink hyperlinkGitHub;

    /**
     * Creates a {@code ApplicantCard} with the given {@code Applicant} and index to display.
     */
    public ApplicantCard(Applicant applicant, int displayedIndex) {
        super(FXML);
        this.applicant = applicant;
        id.setText(displayedIndex + ". ");
        name.setText(applicant.getName().fullName);
        phone.setText(applicant.getPhone().value);
        address.setText(applicant.getAddress().value);
        email.setText(applicant.getEmail().value);
        application.setText(applicant.getApplicationSummary());
        initializeHyperlinksForApplicant(applicant);
    }

    private void initializeHyperlinksForApplicant(Applicant applicant) {
        // Insert the code for initializing the applicant github link and linkedin link here
        this.gitHubUrl = applicant.getGitHubUrl();
        if (!applicant.hasGitHubProfile()) {
            removeGitHubHyperLinkFromApplicantCard();
        }
    }

    private void removeGitHubHyperLinkFromApplicantCard() {
        hyperlinkAnchorPane.getChildren().removeAll(hyperlinkGitHub, gitHubLogo);
    }

    @FXML
    private void handleGitHubHyperlink() {
        try {
            Desktop.getDesktop().browse(new URI(gitHubUrl.getUrlString()));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplicantCard)) {
            return false;
        }

        // state check
        ApplicantCard card = (ApplicantCard) other;
        return id.getText().equals(card.id.getText())
                && applicant.equals(card.applicant);
    }

}
