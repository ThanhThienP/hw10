package memento_mediator;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testSendAndUndo() {
        ChatServer server = new ChatServer();
        User therese = new User("Therese", server);
        User tian = new User("Tian", server);

        therese.sendMessage(List.of(tian), "Mom, can you grab me some milk?");
        assertNotNull(therese.getLastMemento());
        assertEquals(1, tian.getChatHistory().getHistory().size());

        therese.undoLastMessageSent();
        assertNull(therese.getLastMemento());
        assertEquals(0, tian.getChatHistory().getHistory().size());
        assertEquals(0, therese.getChatHistory().getHistory().size());
    }

    @Test
    public void testBlockUser() {
        ChatServer server = new ChatServer();
        User phan = new User("Phan", server);
        User therese = new User("Therese", server);

        phan.blockUser(therese);
        therese.sendMessage(List.of(phan), "Sorry my sister used my phone, what happened?");

        assertEquals(0, phan.getChatHistory().getHistory().size());
    }
}
