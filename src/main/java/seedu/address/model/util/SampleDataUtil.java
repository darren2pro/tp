package seedu.address.model.util;

import seedu.address.model.ApplicantBook;
import seedu.address.model.PositionBook;
import seedu.address.model.ReadOnlyApplicantBook;
import seedu.address.model.ReadOnlyPositionBook;
import seedu.address.model.applicant.Address;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Email;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.Phone;
import seedu.address.model.applicant.ProfileUrl;
import seedu.address.model.position.Description;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {


    public static Position[] getSamplePositions() {
        return new Position[] {
            new Position(new Title("software engineer"), new Description("work in a team that builds a "
                        + "facial recognition application")),
            new Position(new Title("database administrator"), new Description("handles database administration "
                        + "matters"))
        };
    }

    public static Applicant[] getSampleApplicants() {
        return new Applicant[] {
            new Applicant(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), new Position(new Title("software engineer"),
                    new Description("work in a team that builds a facial recognition application")),
                    ProfileUrl.emptyProfileUrl()),
            new Applicant(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new Position(new Title("database administrator"),
                    new Description("handles database administration matters")),
                    new ProfileUrl("https://github.com/yumorishita"))
        };
    }

    public static ReadOnlyApplicantBook getSampleApplicantBook() {
        ApplicantBook sampleApplicantBook = new ApplicantBook();
        for (Applicant sampleApplicant : getSampleApplicants()) {
            sampleApplicantBook.addApplicant(sampleApplicant);
        }
        return sampleApplicantBook;
    }


    public static ReadOnlyPositionBook getSamplePositionBook() {
        PositionBook samplePb = new PositionBook();
        for (Position samplePosition : getSamplePositions()) {
            samplePb.addPosition(samplePosition);
        }
        return samplePb;
    }
}
