package memento_mediator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChatHistory implements IterableByUser {
    private List<Message> history = new ArrayList<>();

    public void addMessage(Message message) {
        history.add(message);
    }

    public Message getLastMessage() {
        if (history.isEmpty()) {
            return null;
        }
        return history.get(history.size() - 1);
    }

    public Message getLastMessageSent() {
        return getLastMessage();
    }

    public Message getLastMessageSent(User user) {
        if (user == null) {
            return getLastMessage();
        }
        for (int i = history.size() - 1; i >= 0; i--) {
            if (user.equals(history.get(i).getSender())) {
                return history.get(i);
            }
        }
        return null;
    }

    public List<Message> getHistory() {
        return history;
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return new searchMessagesByUser(history, userToSearchWith);
    }
}
