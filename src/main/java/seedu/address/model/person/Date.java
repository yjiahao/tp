package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Person's date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS =
            "Dates should be in the format yyyy-MM-dd and be a valid calendar date";
    private static final String EMPTY_STRING = "";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd")
            .withResolverStyle(ResolverStyle.STRICT);

    public final String value;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = date;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }

    /**
     * Returns true if given string is a valid date or is empty.
     * Used for loading from the JSON storage file.
     *
     * @param date date given to check for validity.
     * @return true if valid date or empty, else false.
     */
    public static boolean isValidDateOrEmptyString(String date) {
        return date.equals(EMPTY_STRING) || isValidDate(date);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Date)) {
            return false;
        }

        Date otherDate = (Date) other;
        return value.equals(otherDate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
