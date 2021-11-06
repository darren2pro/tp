package seedu.address.model.applicant;

import static java.util.Objects.requireNonNull;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Represents an Applicant's name in MrTechRecruiter.
 * Guarantees: immutable; is valid as declared in {@link #isValidUrl(String)}
 */
public class ProfileUrl {

    /*
     * There must not be any white spaces at all. This validation regex already assumes that it is a valid url.
     * So there is no need to check for https:// or http://
     * This checks whether the url link given is a gitHub profile url, e.g. github.com/empty or
     * https://github.com/empty
     */
    public static final String VALIDATION_REGEX = "((http|https)://)?(www.)?"
                    + "github\\.com/[a-zA-Z0-9@:%_\\+~#\\?&=]+";

    public static final String PLACEHOLDER_URL = "https://www.google.com/";
    public static final String MESSAGE_CONSTRAINTS = "Profile urls must be valid github urls.";

    private static final ProfileUrl EMPTY_PROFILE_URL = new ProfileUrl(PLACEHOLDER_URL);
    public final String url;

    /**
     * Constructs a {@code ProfileUrl}.
     * Assumes non-null and assumes non-empty string, i.e. assumes valid gitHub url.
     * @param url A valid github url.
     */
    public ProfileUrl(String url) {
        requireNonNull(url);
        this.url = url;
    }

    /**
     * Returns a {@code ProfileUrl} containing the url given. If empty string is given,
     * emptyProfileUrl object instance is returned.
     * @param url  string url to be stored in this ProfileUrl.
     * @return ProfileUrl instance containing this url, if valid.
     */
    public static ProfileUrl ofNullable(String url) {
        if (url.equals("") || url.equals(PLACEHOLDER_URL)) {
            return emptyProfileUrl();
        }
        return new ProfileUrl(url);
    }

    /**
     * Returns the one single instance of empty profile url.
     *
     * @return the one single instance of empty profile url.
     */
    public static ProfileUrl emptyProfileUrl() {
        return EMPTY_PROFILE_URL;
    }

    /**
     * Returns true if a given string is a valid url.
     *
     * @param gitHubUrl given url to test.
     * @return boolean whether this url is a valid url.
     */
    public static boolean isValidUrl(String gitHubUrl) {
        try {
            new URL(gitHubUrl);
            // Comes to this line means that it is a valid url
            // now to check whether it is valid gitHub link
            return gitHubUrl.matches(VALIDATION_REGEX);
        } catch (MalformedURLException malformedUrlException) {
            return false;
        }
    }

    /**
     * Checks whether this ProfileUrl is empty or not, i.e. testing whether there is an existing profile.
     *
     * @return true is there is a profile stored, false if there is no profile.
     */
    public boolean hasProfile() {
        return this != EMPTY_PROFILE_URL;
    }

    public String getUrlString() {
        return this.url;
    }

    /**
     * Returns a copied Profile Url.
     *
     * @return a copy of this Profile Url.
     */
    public ProfileUrl getCopiedProfileUrl() {
        return new ProfileUrl(url);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProfileUrl)) {
            return false;
        }

        ProfileUrl otherProfileUrl = (ProfileUrl) other;
        return this.url.equals(otherProfileUrl.url);
    }

    @Override
    public String toString() {
        return this.url;
    }
}
