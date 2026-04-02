package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;

/**
 * Represents a Person's time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class Time {

    public static final String MESSAGE_CONSTRAINTS =
            "Times should be in the format HH:mm or HHmm, and be a valid 24-hour time";
    private static final String EMPTY_STRING = "";
    private static final DateTimeFormatter DISPLAY_TIME_FORMATTER = formatter("HH:mm");
    private static final DateTimeFormatter STORAGE_TIME_FORMATTER = formatter("HH:mm");
    private static final List<DateTimeFormatter> USER_TIME_FORMATTERS = List.of(
            formatter("HH:mm"),
            formatter("HHmm"));

    public final String value;
    private final LocalTime localTime;

    /**
     * Constructs a {@code Time}.
     *
     * @param time A valid time.
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        localTime = parseTime(time);
        value = localTime.format(STORAGE_TIME_FORMATTER);
    }

    private static DateTimeFormatter formatter(String pattern) {
        return DateTimeFormatter.ofPattern(pattern).withResolverStyle(ResolverStyle.STRICT);
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        requireNonNull(test);
        return parseTime(test) != null;
    }

    private static LocalTime parseTime(String time) {
        String trimmedTime = time.trim();

        for (DateTimeFormatter formatter : USER_TIME_FORMATTERS) {
            try {
                return LocalTime.parse(trimmedTime, formatter);
            } catch (DateTimeParseException ignored) {
                // Try next format.
            }
        }

        return null;
    }

    /**
     * Returns the display value of the time.
     */
    public String getDisplayValue() {
        return localTime.format(DISPLAY_TIME_FORMATTER);
    }

    /**
     * Returns true if given string is a valid time or is empty.
     * Used for loading from the JSON storage file.
     *
     * @param time time given to check for validity.
     * @return true if valid time or empty, else false.
     */
    public static boolean isValidTimeOrEmptyString(String time) {
        requireNonNull(time);
        return time.equals(EMPTY_STRING) || isValidTime(time);
    }

    @Override
    public String toString() {
        return getDisplayValue();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Time)) {
            return false;
        }

        Time otherTime = (Time) other;
        return value.equals(otherTime.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
