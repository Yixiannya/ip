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
     * Creates a Chappi object that uses the specified file path for storage.
     * @param filePath File path to be used for storage.
     */
    public Chappi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = storage.load();
        } catch (ChappiException e) {
            ui.showDukeException(e);
            taskList = new TaskList();
        }
    }
    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            int commandType = Parser.parse(input);
            switch (commandType) {
            case 0:
                if (taskList.isEmpty()) {
                    return ui.showEmptyTaskList();
                } else {
                    return ui.showTaskList(taskList);
                }
            case 1:
                return "bye";
            case 2:
                Task markedTask = Parser.parseTaskIndex(Parser.parseMarkTask(input), taskList);
                markedTask.markDone();
                storage.save(taskList);
                return ui.showMarkedTask(markedTask);
            case 3:
                Task unmarkedTask = Parser.parseTaskIndex(Parser.parseUnmarkTask(input), taskList);
                unmarkedTask.markNotDone();
                storage.save(taskList);
                return ui.showUnmarkedTask(unmarkedTask);
            case 4:
                Task deletedTask = Parser.parseTaskIndex(Parser.parseDeleteTask(input), taskList);
                taskList.removeTask(deletedTask);
                storage.save(taskList);
                return ui.showDeletedTask(deletedTask);
            case 5:
                Task todoTask = Parser.parseTodo(input);
                taskList.addTask(todoTask);
                storage.save(taskList);
                return ui.showNewTask(todoTask, taskList);
            case 6:
                Task deadlineTask = Parser.parseDeadline(input);
                taskList.addTask(deadlineTask);
                storage.save(taskList);
                return ui.showNewTask(deadlineTask, taskList);
            case 7:
                Task eventTask = Parser.parseEvent(input);
                taskList.addTask(eventTask);
                storage.save(taskList);
                return ui.showNewTask(eventTask, taskList);
            case 8:
                String keyword = Parser.parseFindTask(input);
                TaskList foundTasks = taskList.findMatchingTasks(keyword);
                return ui.showFoundTasks(foundTasks);
            default:
                throw new ChappiUnrecognisedCommandException();
            }
        } catch (ChappiException e) {
            return ui.showDukeException(e);
        } catch (IOException e) {
            return ui.showIoException(e);
        }
    }

    public String showGreeting() {
        return ui.showGreeting();
    }
}
