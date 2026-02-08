package chappi.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import chappi.exceptions.ChappiException;
import chappi.exceptions.ChappiInvalidTodoException;
import chappi.exceptions.ChappiUnrecognisedCommandException;
import chappi.task.ToDo;
import chappi.ui.Chappi;

public class ParserTest {
    @Test
    public void parse() throws Exception {
        assertEquals(Chappi.CommandType.LIST, Parser.parse("list"));

        assertEquals(Chappi.CommandType.BYE, Parser.parse("bye"));

        assertEquals(Chappi.CommandType.MARK, Parser.parse("mark"));

        assertEquals(Chappi.CommandType.UNMARK, Parser.parse("unmark"));

        assertEquals(Chappi.CommandType.DELETE, Parser.parse("delete"));

        assertEquals(Chappi.CommandType.TODO, Parser.parse("todo"));

        assertEquals(Chappi.CommandType.DEADLINE, Parser.parse("deadline"));

        assertEquals(Chappi.CommandType.EVENT, Parser.parse("event"));

        assertEquals(Chappi.CommandType.UNRECOGNISED, Parser.parse("listy"));

        assertEquals(Chappi.CommandType.UNRECOGNISED, Parser.parse("sleep"));
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
            assertEquals(testException.getMessage(), e.getMessage());
        }
    }

    @Test
    public void parseToDo_missingDescription() throws Exception {
        try {
            ToDo testResult = Parser.parseTodo("todo");
            fail();
        } catch (ChappiException e) {
            ChappiException testException = new ChappiInvalidTodoException("Please enter a task description.");
            assertEquals(testException.getMessage(), e.getMessage());
        }
    }

    @Test
    public void parseToDo_missingDescription_withWhiteSpace() throws Exception {
        try {
            ToDo testResult = Parser.parseTodo("todo    ");
            fail();
        } catch (ChappiException e) {
            ChappiException testException = new ChappiInvalidTodoException("Please enter a task description.");
            assertEquals(testException.getMessage(), e.getMessage());
        }
    }
}
