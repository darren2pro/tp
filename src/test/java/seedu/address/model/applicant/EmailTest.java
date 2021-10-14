package seedu.address.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.address.model.applicant.Email(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.address.model.applicant.Email(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> seedu.address.model.applicant.Email.isValidEmail(null));

        // blank email
        assertFalse(seedu.address.model.applicant.Email.isValidEmail("")); // empty string
        assertFalse(seedu.address.model.applicant.Email.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(seedu.address.model.applicant.Email.isValidEmail("@example.com")); // missing local part
        assertFalse(seedu.address.model.applicant.Email.isValidEmail(
                "peterjackexample.com")); // missing '@' symbol
        assertFalse(seedu.address.model.applicant.Email.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(seedu.address.model.applicant.Email.isValidEmail(
                "peterjack@-")); // invalid domain name
        assertFalse(seedu.address.model.applicant.Email.isValidEmail(
                "peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(seedu.address.model.applicant.Email.isValidEmail(
                "peter jack@example.com")); // spaces in local part
        assertFalse(seedu.address.model.applicant.Email.isValidEmail(
                "peterjack@exam ple.com")); // spaces in domain name
        assertFalse(seedu.address.model.applicant.Email.isValidEmail(
                " peterjack@example.com")); // leading space
        assertFalse(seedu.address.model.applicant.Email.isValidEmail(
                "peterjack@example.com ")); // trailing space
        assertFalse(seedu.address.model.applicant.Email.isValidEmail(
                "peterjack@@example.com")); // double '@' symbol
        assertFalse(seedu.address.model.applicant.Email.isValidEmail(
                "peter@jack@example.com")); // '@' symbol in local part
        assertFalse(seedu.address.model.applicant.Email.isValidEmail(
                "-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(seedu.address.model.applicant.Email.isValidEmail(
                "peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(seedu.address.model.applicant.Email.isValidEmail(
                "peter..jack@example.com")); // local part has two consecutive periods
        assertFalse(seedu.address.model.applicant.Email.isValidEmail(
                "peterjack@example@com")); // '@' symbol in domain name
        assertFalse(seedu.address.model.applicant.Email.isValidEmail(
                "peterjack@.example.com")); // domain name starts with a period
        assertFalse(seedu.address.model.applicant.Email.isValidEmail(
                "peterjack@example.com.")); // domain name ends with a period
        assertFalse(seedu.address.model.applicant.Email.isValidEmail(
                "peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(seedu.address.model.applicant.Email.isValidEmail(
                "peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(seedu.address.model.applicant.Email.isValidEmail(
                "peterjack@example.c")); // top level domain has less than two chars

        // valid email
        assertTrue(seedu.address.model.applicant.Email.isValidEmail(
                "PeterJack_1190@example.com")); // underscore in local part
        assertTrue(seedu.address.model.applicant.Email.isValidEmail(
                "PeterJack.1190@example.com")); // period in local part
        assertTrue(seedu.address.model.applicant.Email.isValidEmail(
                "PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(seedu.address.model.applicant.Email.isValidEmail(
                "PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(seedu.address.model.applicant.Email.isValidEmail(
                "a@bc")); // minimal
        assertTrue(seedu.address.model.applicant.Email.isValidEmail(
                "test@localhost")); // alphabets only
        assertTrue(seedu.address.model.applicant.Email.isValidEmail(
                "123@145")); // numeric local part and domain name
        assertTrue(seedu.address.model.applicant.Email.isValidEmail(
                "a1+be.d@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(seedu.address.model.applicant.Email.isValidEmail(
                "peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(seedu.address.model.applicant.Email.isValidEmail(
                "if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(Email.isValidEmail("e1234567@u.nus.edu")); // more than one period in domain
    }

}
