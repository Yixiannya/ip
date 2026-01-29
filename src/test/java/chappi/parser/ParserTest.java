package chappi.parser;

import chappi.chappiExceptions.ChappiException;
import chappi.chappiExceptions.ChappiInvalidTodoException;
import chappi.chappiExceptions.ChappiUnrecognisedCommandException;
import chappi.task.ToDo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parse() throws Exception {
        assertEquals(0, Parser.parse("list"));

        assertEquals(1, Parser.parse("bye"));

        assertEquals(2, Parser.parse("mark"));

        assertEquals(3, Parser.parse("unmark"));

        assertEquals(4, Parser.parse("delete"));

        assertEquals(5, Parser.parse("todo"));

        assertEquals(6, Parser.parse("deadline"));

        assertEquals(7, Parser.parse("event"));

        assertEquals(-1, Parser.parse("listy"));

        assertEquals(-1, Parser.parse("sleep"));
    }

    @Test
    public void parseToDo_success() throws Exception {
        ToDo testResult = Parser.parseTodo("todo Eat a banana.");
        assertEquals("[T][ ] Eat a banana.", testResult.toString());
    }

    @Test
    public void parseToDo_invalidFormat() throws Exception {
        try {
            ToDo testResult = Parser.parseTodo("todoEat a banana.");
            fail();
        } catch (ChappiException e) {
            ChappiException testException = new ChappiUnrecognisedCommandException();
            assertEquals(testException.toString(), e.toString());
        }
    }
}
