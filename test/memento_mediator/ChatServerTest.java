package memento_mediator;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ChatServerTest {

    @Test
    public void testSendMessageAndBlock() {
        ChatServer server = new ChatServer();
        User phan = new User("Phan", server);
        User therese = new User("Therese", server);

        server.blockUser(phan, therese);
        assertTrue(server.isBlocked(phan, therese));

        server.sendMessage(therese, List.of(phan), "Sorry my sister used my phone, what happened?");
        assertEquals(1, therese.getChatHistory().getHistory().size());
        assertEquals(0, phan.getChatHistory().getHistory().size());
    }

    @Test
    public void testUndoLastMessage() {
        ChatServer server = new ChatServer();
        User therese = new User("Therese", server);
        User phan = new User("Phan", server);

        server.sendMessage(therese, List.of(phan), "Mom, can you grab me some milk?");
        assertEquals(1, phan.getChatHistory().getHistory().size());

        server.undoLastMessage(therese);
        assertEquals(0, therese.getChatHistory().getHistory().size());
        assertEquals(0, phan.getChatHistory().getHistory().size());
    }
}
