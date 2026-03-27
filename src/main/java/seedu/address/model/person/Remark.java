package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Remark {

    public static final String MESSAGE_CONSTRAINTS =
        "Remark cannot be empty or only spaces, and cannot contain tabs or newlines.";
    
    /*
     * Requires at least one non-whitespace character, and allows only printable ASCII characters.
     * This rejects empty strings, strings containing only spaces, and control characters
     * such as tabs and newlines.
     */
    public static final String VALIDATION_REGEX = "^(?=.*\\S)[\\x20-\\x7E]+$";

    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param name A valid remark.
     */
    public Remark(String value) {
        requireNonNull(value);
        checkArgument(isValidRemark(value), MESSAGE_CONSTRAINTS);

        this.value = value;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidRemark(String test) {
        return test.matches(VALIDATION_REGEX);
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

        // instanceof handles nulls
        if (!(other instanceof Remark)) {
            return false;
        }

        Remark otherRemark = (Remark) other;
        return value.equals(otherRemark.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
