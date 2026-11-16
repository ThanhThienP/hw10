package memento_mediator;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class SearchMessagesByUserTest {

    @Test
    public void testSearchMessagesByUser() {
        User tian = new User("Tian", null);
        User phan = new User("Phan", null);
        User therese = new User("Therese", null);

        Message m1 = new Message(tian, List.of(phan), "Hey Phan, have you heard from Therese today?");
        Message m2 = new Message(tian, List.of(therese), "Hey Therese, how is your part of the project going?");
        Message m3 = new Message(phan, List.of(tian), "No, let me check in on her in the group chat.");

        List<Message> messages = List.of(m1, m2, m3);

        searchMessagesByUser iterator = new searchMessagesByUser(messages, phan);

        assertTrue(iterator.hasNext());
        assertEquals(m1, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(m3, iterator.next());

        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
