package chappi.ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import chappi.exceptions.ChappiException;
import chappi.exceptions.ChappiUnrecognisedCommandException;
import chappi.parser.Parser;
import chappi.storage.Storage;
import chappi.task.Task;
import chappi.tasklist.TaskList;

/**
 * Main class in which the Chappi chatbot is run on.
 */
public class Chappi {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Represents the different command types
     * a user can input.
     */
    public enum CommandType {
        LIST,
        BYE,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND,
        UPDATE,
        UNRECOGNISED
    }

    /**
     * Creates a Chappi object that uses the specified file path for storage.
     * @param filePath File path to be used for storage.
     */
    public Chappi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = storage.load();
        } catch (ChappiException e) {
            ui.showChappiException(e);
            taskList = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     * Message is dependent on what command the user includes in their input.
     * @param input String representation of user's command to Chappi.
     * @return String representation of Chappi's response to the user.
     */
    public String getResponse(String input) {
        try {
            CommandType commandType = Parser.parse(input);
            assert commandType != null;
            switch (commandType) {
            case LIST:
                return executeShowTaskListCommand();
            case BYE:
                return "bye";
            case MARK:
                return executeMarkTaskCommand(input);
            case UNMARK:
                return executeUnmarkTaskCommand(input);
            case DELETE:
                return executeDeleteTaskCommand(input);
            case TODO:
                return executeAddTodoTaskCommand(input);
            case DEADLINE:
                return executeAddDeadlineTaskCommand(input);
            case EVENT:
                return executeAddEventTaskCommand(input);
            case FIND:
                return executeFindTaskCommand(input);
            case UPDATE:
                return executeUpdateTaskCommand(input);
            default:
                throw new ChappiUnrecognisedCommandException();
            }
        } catch (ChappiException e) {
            return ui.showChappiException(e);
        }
    }

    /**
     * Gives a String representation of the greeting Chappi says.
     * @return String representation of greeting.
     */
    public String showGreeting() {
        return ui.showGreeting();
    }

    private String executeShowTaskListCommand() {
        if (taskList.isEmpty()) {
            return ui.showEmptyTaskList();
        } else {
            return ui.showTaskList(taskList);
        }
    }

    private String executeMarkTaskCommand(String input) throws ChappiException {
        Task markedTask = Parser.parseTaskIndex(Parser.parseMarkTask(input), taskList);
        markedTask.markDone();
        saveTaskList();
        return ui.showMarkedTask(markedTask);
    }

    private String executeUnmarkTaskCommand(String input) throws ChappiException {
        Task unmarkedTask = Parser.parseTaskIndex(Parser.parseUnmarkTask(input), taskList);
        unmarkedTask.markNotDone();
        saveTaskList();
        return ui.showUnmarkedTask(unmarkedTask);
    }

    private String executeDeleteTaskCommand(String input) throws ChappiException {
        Task deletedTask = Parser.parseTaskIndex(Parser.parseDeleteTask(input), taskList);
        taskList.removeTask(deletedTask);
        saveTaskList();
        return ui.showDeletedTask(deletedTask);
    }

    private String executeAddTodoTaskCommand(String input) throws ChappiException {
        Task todoTask = Parser.parseTodo(input);
        taskList.addTask(todoTask);
        saveTaskList();
        return ui.showNewTask(todoTask, taskList);
    }

    private String executeAddDeadlineTaskCommand(String input) throws ChappiException {
        Task deadlineTask = Parser.parseDeadline(input);
        taskList.addTask(deadlineTask);
        saveTaskList();
        return ui.showNewTask(deadlineTask, taskList);
    }

    private String executeAddEventTaskCommand(String input) throws ChappiException {
        Task eventTask = Parser.parseEvent(input);
        taskList.addTask(eventTask);
        saveTaskList();
        return ui.showNewTask(eventTask, taskList);
    }

    private String executeFindTaskCommand(String input) throws ChappiException {
        String keyword = Parser.parseFindTask(input);
        TaskList foundTasks = taskList.findMatchingTasks(keyword);
        return ui.showFoundTasks(foundTasks);
    }

    private String executeUpdateTaskCommand(String input) throws ChappiException {
        Object[] info = Parser.parseUpdateTask(input);
        String indexString = (String) info[0];
        Task toBeUpdatedTask = Parser.parseTaskIndex(indexString, taskList);
        taskList.updateTask(toBeUpdatedTask, info);
        saveTaskList();
        return ui.showUpdatedTask(toBeUpdatedTask);
    }

    private void saveTaskList() throws ChappiException {
        try {
            storage.save(taskList);
        } catch (FileNotFoundException e) {
            throw new ChappiException(e.getMessage());
        }
    }
}
