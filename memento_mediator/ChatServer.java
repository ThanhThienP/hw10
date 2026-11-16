package memento_mediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatServer {
    private List<User> users = new ArrayList<>();
    private Map<User, List<User>> blockList = new HashMap<>();

    public void registerUser(User user) {
        users.add(user);
    }

    public void unregisterUser(User user) {
        users.remove(user);
    }

    public void blockUser(User user, User userToBlock) {
        blockList.computeIfAbsent(user, k -> new ArrayList<>()).add(userToBlock);
    }

    public boolean isBlocked(User recipient, User sender) {
        List<User> blocked = blockList.get(recipient);
        return blocked != null && blocked.contains(sender);
    }

    public void sendMessage(User sender, List<User> recipients, String content) {
        Message message = new Message(sender, recipients, content);

        sender.getChatHistory().addMessage(message);

        for (User recipient : recipients) {
            if (isBlocked(recipient, sender)) {
                System.out.println("[Blocked] " + recipient.getName() + " blocked messages from " + sender.getName());
            } else {
                recipient.receiveMessage(message);
            }
        }
    }

    public void undoLastMessage(User sender) {
        if (sender == null) {
            return;
        }
        Message lastMessage = sender.getChatHistory().getLastMessageSent(sender);
        if (lastMessage == null) {
            return;
        }

        sender.getChatHistory().getHistory().remove(lastMessage);

        for (User recipient : lastMessage.getRecipients()) {
            recipient.getChatHistory().getHistory().remove(lastMessage);
        }
    }
}
