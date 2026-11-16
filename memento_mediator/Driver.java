package memento_mediator;

import java.util.Iterator;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        ChatServer server = new ChatServer();

        User tian = new User("Tian", server);
        User phan = new User("Phan", server);
        User therese = new User("Therese", server);

        System.out.println("===TEST: SENDING 1-ON-1 & GROUP MESSAGES");

        // 1-on-1 direct message
        tian.sendMessage(List.of(phan), "Hey Phan, have you heard from Therese today?");
        phan.sendMessage(List.of(tian), "No, let me check in on her in the group chat.");

        // group message
        phan.sendMessage(List.of(tian, therese), "Hey Therese, how is your part of the project going?");
        therese.sendMessage(List.of(tian, phan), "Hey guys, I haven't even started yet lol");

        tian.printHistory();
        phan.printHistory();
        therese.printHistory();

        System.out.println("\n===TEST: UNDO FUNCTION");
        therese.sendMessage(List.of(tian, phan), "Mom, can you grab me some milk?");
        tian.printHistory();
        System.out.println("\nTherese realizes she sent wrong message in the group chat then she hits undo");
        therese.undoLastMessageSent();

        System.out.println("\nTian's history after Therese's undo:");
        tian.printHistory();

        System.out.println("\n===TEST: BLOCKING ");
        therese.sendMessage(List.of(tian, phan), "Btw Phan, I saw your code and I think you need to git gud");
        phan.printHistory();

        System.out.println("\nPhan is offended and decided to block Therese");
        phan.blockUser(therese);

        therese.sendMessage(List.of(tian, phan), "Sorry my sister used my phone, what happened?");
        therese.sendMessage(List.of(tian, phan), "Can you guys update me on the project?");


        System.out.println("\nTian's history, still able to see the message");
        tian.printHistory();

        System.out.println("\nPhan's history (he blocked Therese, so Mediator filtered out Therese's message):");
        phan.printHistory();

        System.out.println("\n===TEST: ITERATING MESSAGES BY SPECIFIC USER (ITERATOR PATTERN)");
        System.out.println("-> Tian searching his chat history for messages involving Phan:");
        Iterator<Message> phanIterator = tian.iterator(phan);
        while (phanIterator.hasNext()) {
            System.out.println("  " + phanIterator.next());
        }

        System.out.println("\n-> Tian searching his chat history for messages involving Therese:");
        Iterator<Message> thereseIterator = tian.iterator(therese);
        while (thereseIterator.hasNext()) {
            System.out.println("  " + thereseIterator.next());
        }

        System.out.println("\n-> Phan searching his chat history for messages involving Therese (pre-block only):");
        Iterator<Message> phanThereseIterator = phan.iterator(therese);
        while (phanThereseIterator.hasNext()) {
            System.out.println("  " + phanThereseIterator.next());
        }
    }
}
