package memento_mediator;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

public class IterableByUserTest {

    @Test
    public void testUserAndHistoryIterator() {
        ChatServer server = new ChatServer();
        User tian = new User("Tian", server);
        User phan = new User("Phan", server);
        User therese = new User("Therese", server);

        tian.sendMessage(phan, "Hey Phan, have you heard from Therese today?");
        tian.sendMessage(therese, "Hey Therese, how is your part of the project going?");
        phan.sendMessage(tian, "No, let me check in on her in the group chat.");

        Iterator<Message> iterator = tian.iterator(phan);
        assertTrue(iterator.hasNext());
        Message msg1 = iterator.next();
        assertEquals("Hey Phan, have you heard from Therese today?", msg1.getContent());

        assertTrue(iterator.hasNext());
        Message msg2 = iterator.next();
        assertEquals("No, let me check in on her in the group chat.", msg2.getContent());

        assertFalse(iterator.hasNext());
    }
}
