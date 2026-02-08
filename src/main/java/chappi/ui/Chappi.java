package chappi.ui;

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
                if (taskList.isEmpty()) {
                    return ui.showEmptyTaskList();
                } else {
                    return ui.showTaskList(taskList);
                }
            case BYE:
                return "bye";
            case MARK:
                Task markedTask = Parser.parseTaskIndex(Parser.parseMarkTask(input), taskList);
                markedTask.markDone();
                storage.save(taskList);
                return ui.showMarkedTask(markedTask);
            case UNMARK:
                Task unmarkedTask = Parser.parseTaskIndex(Parser.parseUnmarkTask(input), taskList);
                unmarkedTask.markNotDone();
                storage.save(taskList);
                return ui.showUnmarkedTask(unmarkedTask);
            case DELETE:
                Task deletedTask = Parser.parseTaskIndex(Parser.parseDeleteTask(input), taskList);
                taskList.removeTask(deletedTask);
                storage.save(taskList);
                return ui.showDeletedTask(deletedTask);
            case TODO:
                Task todoTask = Parser.parseTodo(input);
                taskList.addTask(todoTask);
                storage.save(taskList);
                return ui.showNewTask(todoTask, taskList);
            case DEADLINE:
                Task deadlineTask = Parser.parseDeadline(input);
                taskList.addTask(deadlineTask);
                storage.save(taskList);
                return ui.showNewTask(deadlineTask, taskList);
            case EVENT:
                Task eventTask = Parser.parseEvent(input);
                taskList.addTask(eventTask);
                storage.save(taskList);
                return ui.showNewTask(eventTask, taskList);
            case FIND:
                String keyword = Parser.parseFindTask(input);
                TaskList foundTasks = taskList.findMatchingTasks(keyword);
                return ui.showFoundTasks(foundTasks);
            case UPDATE:
                String[] info = Parser.parseUpdateTask(input);
                Task toBeUpdatedTask = Parser.parseTaskIndex(info[0], taskList);
                taskList.updateTask(toBeUpdatedTask, info);
                return ui.showUpdatedTask(toBeUpdatedTask);
            default:
                throw new ChappiUnrecognisedCommandException();
            }
        } catch (ChappiException e) {
            return ui.showChappiException(e);
        } catch (IOException e) {
            return ui.showIoException(e);
        }
    }

    /**
     * Gives a String representation of the greeting Chappi says.
     * @return String representation of greeting.
     */
    public String showGreeting() {
        return ui.showGreeting();
    }
}
