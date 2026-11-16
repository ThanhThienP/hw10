package memento_mediator;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class User implements IterableByUser {
    private String name;
    private ChatServer server;
    private ChatHistory history = new ChatHistory();
    private MessageMemento lastMemento;

    public User(String name, ChatServer server) {
        this.name = name;
        this.server = server;
        if (server != null) {
            server.registerUser(this);
        }
    }

    public String getName() {
        return name;
    }

    public ChatHistory getChatHistory() {
        return history;
    }

    public MessageMemento getLastMemento() {
        return lastMemento;
    }

    public void sendMessage(List<User> recipients, String content) {
        server.sendMessage(this, recipients, content);

        Message lastMessage = history.getLastMessage();
        if (lastMessage != null) {
            lastMemento = lastMessage.createMemento();
        }
    }

    public void sendMessage(User recipient, String content) {
        if (recipient != null) {
            sendMessage(List.of(recipient), content);
        }
    }

    public void receiveMessage(Message message) {
        history.addMessage(message);
    }

    public void undoLastMessageSent() {
        if (lastMemento == null) {
            System.out.println(name + " has no message to undo.");
            return;
        }
        System.out.println(name + " is undoing: \"" + lastMemento.getContent() + "\"");
        server.undoLastMessage(this);
        lastMemento = null;
    }

    public void blockUser(User userToBlock) {
        server.blockUser(this, userToBlock);
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return history.iterator(userToSearchWith);
    }

    public void printHistory() {
        System.out.println("History for " + name + ":");
        for (Message m : history.getHistory()) {
            System.out.println("  " + m);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
