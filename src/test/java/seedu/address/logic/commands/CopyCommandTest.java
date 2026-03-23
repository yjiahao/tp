package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIds.ID_FIRST;
import static seedu.address.testutil.TypicalIds.ID_SECOND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Id;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code CopyCommand}.
 */
public class CopyCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_idNotInAddressBook_throwsCommandException() {
        Id notInAddressBookId = Id.fromCurrentMaxId(model.findMaxId());
        CopyCommand copyCommand = new CopyCommand(notInAddressBookId, CopyCommand.FIELD_PHONE);

        String expectedMessage = String.format(CopyCommand.MESSAGE_PERSON_NOT_FOUND,
                notInAddressBookId.getValue());

        assertCommandFailure(copyCommand, model, expectedMessage);
    }

    @Test
    public void execute_idNotInEmptyAddressBook_throwsCommandException() {
        Model emptyModel = new ModelManager();
        Id anyId = ID_FIRST;
        CopyCommand copyCommand = new CopyCommand(anyId, CopyCommand.FIELD_PHONE);

        String expectedMessage = String.format(CopyCommand.MESSAGE_PERSON_NOT_FOUND, anyId.getValue());

        assertCommandFailure(copyCommand, emptyModel, expectedMessage);
    }

    @Test
    public void equals() {
        CopyCommand copyFirstPhone = new CopyCommand(ID_FIRST, CopyCommand.FIELD_PHONE);
        CopyCommand copySecondPhone = new CopyCommand(ID_SECOND, CopyCommand.FIELD_PHONE);
        CopyCommand copyFirstName = new CopyCommand(ID_FIRST, CopyCommand.FIELD_NAME);

        assertTrue(copyFirstPhone.equals(copyFirstPhone));

        CopyCommand copyFirstPhoneCopy = new CopyCommand(ID_FIRST, CopyCommand.FIELD_PHONE);
        assertTrue(copyFirstPhone.equals(copyFirstPhoneCopy));
        assertFalse(copyFirstPhone.equals(copySecondPhone));
        assertFalse(copyFirstPhone.equals(copyFirstName));
        assertFalse(copyFirstPhone.equals(1));
        assertFalse(copyFirstPhone.equals(null));
    }

    @Test
    public void toStringMethod() {
        CopyCommand copyCommand = new CopyCommand(ID_FIRST, CopyCommand.FIELD_PHONE);
        String expected = CopyCommand.class.getCanonicalName()
                + "{targetId=" + ID_FIRST + ", field=" + CopyCommand.FIELD_PHONE + "}";
        assertEquals(expected, copyCommand.toString());
    }
}
