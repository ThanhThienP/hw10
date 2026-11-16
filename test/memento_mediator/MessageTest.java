package memento_mediator;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageAndMementoCreation() {
        User tian = new User("Tian", null);
        User phan = new User("Phan", null);
        Message msg = new Message(tian, List.of(phan), "Hey Phan, have you heard from Therese today?");

        assertEquals(tian, msg.getSender());
        assertEquals(List.of(phan), msg.getRecipients());
        assertEquals("Hey Phan, have you heard from Therese today?", msg.getContent());

        MessageMemento memento = msg.createMemento();
        assertEquals("Hey Phan, have you heard from Therese today?", memento.getContent());
    }
}
