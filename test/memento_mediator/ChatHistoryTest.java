package memento_mediator;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ChatHistoryTest {

    @Test
    public void testAddAndGetLastMessage() {
        ChatHistory history = new ChatHistory();
        assertNull(history.getLastMessage());

        User tian = new User("Tian", null);
        User phan = new User("Phan", null);
        Message msg1 = new Message(tian, List.of(phan), "Hey Phan, have you heard from Therese today?");
        Message msg2 = new Message(phan, List.of(tian), "No, let me check in on her in the group chat.");

        history.addMessage(msg1);
        assertEquals(msg1, history.getLastMessage());

        history.addMessage(msg2);
        assertEquals(msg2, history.getLastMessage());
        assertEquals(2, history.getHistory().size());
    }
}
