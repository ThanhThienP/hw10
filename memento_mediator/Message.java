package memento_mediator;

import java.util.List;
import java.util.Objects;

public class Message {
    private User sender;
    private List<User> recipients;
    private long timestamp;
    private String content;

    public Message(User sender, List<User> recipients, String content) {
        this.sender = sender;
        this.recipients = recipients;
        this.content = content;
        this.timestamp = System.currentTimeMillis();
    }

    public User getSender() {
        return sender;
    }

    public List<User> getRecipients() {
        return recipients;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }

    public String getMessageContent() {
        return content;
    }

    public MessageMemento createMemento() {
        return new MessageMemento(content, timestamp);
    }

    @Override
    public String toString() {
        return sender.getName() + ": " + content;
    }
}
