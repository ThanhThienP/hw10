package memento_mediator;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    MessageMementoTest.class,
    MessageTest.class,
    ChatHistoryTest.class,
    ChatServerTest.class,
    UserTest.class,
    SearchMessagesByUserTest.class,
    IterableByUserTest.class
})
public class CompleteUnitTest {

}
