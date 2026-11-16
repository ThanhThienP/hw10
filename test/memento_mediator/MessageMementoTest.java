package memento_mediator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageMementoTest {

    @Test
    public void testMementoSnapshot() {
        MessageMemento memento = new MessageMemento("Mom, can you grab me some milk?", 1000L);
        assertEquals("Mom, can you grab me some milk?", memento.getContent());
        assertEquals(1000L, memento.getTimestamp());
    }
}
