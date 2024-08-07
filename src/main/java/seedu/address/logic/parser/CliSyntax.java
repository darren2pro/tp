package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands.
 */
public class CliSyntax {
    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_POSITION = new Prefix("pos/");
    public static final Prefix PREFIX_TITLE = new Prefix("tit/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("des/");
    public static final Prefix PREFIX_STATUS = new Prefix("status/");
    public static final Prefix PREFIX_GITHUB_PROFILE = new Prefix("github/");
}
