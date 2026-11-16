package memento_mediator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class searchMessagesByUser implements Iterator<Message> {
    private List<Message> messages;
    private User targetUser;
    private int index = 0;

    public searchMessagesByUser(List<Message> messages, User targetUser) {
        this.messages = messages;
        this.targetUser = targetUser;
    }

    @Override
    public boolean hasNext() {
        while (index < messages.size()) {
            Message m = messages.get(index);
            if (m.getSender().equals(targetUser) || m.getRecipients().contains(targetUser)) {
                return true;
            }
            index++;
        }
        return false;
    }

    @Override
    public Message next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return messages.get(index++);
    }
}
