package chappi.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import chappi.command.AddDeadlineTaskCommand;
import chappi.command.AddEventTaskCommand;
import chappi.command.AddTodoTaskCommand;
import chappi.command.DeleteTaskCommand;
import chappi.command.ExitCommand;
import chappi.command.FindTaskCommand;
import chappi.command.MarkTaskCommand;
import chappi.command.ShowTaskListCommand;
import chappi.command.UnmarkTaskCommand;
import chappi.command.UpdateTaskCommand;
import chappi.exceptions.ChappiException;
import chappi.exceptions.ChappiInvalidTodoException;
import chappi.exceptions.ChappiUnrecognisedCommandException;
import chappi.task.ToDo;


public class ParserTest {
    @Test
    public void parseCommand_success() throws Exception {
        assertInstanceOf(ShowTaskListCommand.class, Parser.parseCommand("list"));

        assertInstanceOf(ExitCommand.class, Parser.parseCommand("bye"));

        assertInstanceOf(MarkTaskCommand.class, Parser.parseCommand("mark"));

        assertInstanceOf(UnmarkTaskCommand.class, Parser.parseCommand("unmark"));

        assertInstanceOf(DeleteTaskCommand.class, Parser.parseCommand("delete"));

        assertInstanceOf(AddTodoTaskCommand.class, Parser.parseCommand("todo"));

        assertInstanceOf(AddDeadlineTaskCommand.class, Parser.parseCommand("deadline"));

        assertInstanceOf(AddEventTaskCommand.class, Parser.parseCommand("event"));

        assertInstanceOf(FindTaskCommand.class, Parser.parseCommand("find"));

        assertInstanceOf(UpdateTaskCommand.class, Parser.parseCommand("update"));
    }

    @Test
    public void parseCommand_unrecognised() throws Exception {
        try {
            Parser.parseCommand("listy");
            fail();
        } catch (ChappiUnrecognisedCommandException e) {
            ChappiException testException = new ChappiUnrecognisedCommandException();
            assertEquals(testException.getMessage(), e.getMessage());
        }
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
