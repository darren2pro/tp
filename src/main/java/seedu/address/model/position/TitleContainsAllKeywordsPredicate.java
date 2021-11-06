package seedu.address.model.position;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Position}'s {@code Name} matches any of the keywords given.
 */
public class TitleContainsAllKeywordsPredicate implements Predicate<Position> {
    private final List<String> keywords;

    public TitleContainsAllKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Position position) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(position.getTitle().fullTitle, keyword));
    }

}
