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
     * Performs the main logic and behaviour of Chappi chatbot.
     * Executes various tasks based on the commands received.
     */
    public void run() {
        ui.showGreeting();
        while (true) {
            try {
                String input = ui.readInput();
                int commandType = Parser.parse(input);
                switch (commandType) {
                case 0:
                    if (taskList.isEmpty()) {
                        ui.showEmptyTaskList();
                    } else {
                        ui.showTaskList(taskList);
                    }
                    break;
                case 1:
                    ui.showGoodbye();
                    System.exit(0);
                    break;
                case 2:
                    Task markedTask = Parser.parseTaskIndex(Parser.parseMarkTask(input), taskList);
                    markedTask.markDone();
                    ui.showMarkedTask(markedTask);
                    storage.save(taskList);
                    break;
                case 3:
                    Task unmarkedTask = Parser.parseTaskIndex(Parser.parseUnmarkTask(input), taskList);
                    unmarkedTask.markNotDone();
                    ui.showUnmarkedTask(unmarkedTask);
                    storage.save(taskList);
                    break;
                case 4:
                    Task deletedTask = Parser.parseTaskIndex(Parser.parseDeleteTask(input), taskList);
                    taskList.removeTask(deletedTask);
                    ui.showDeletedTask(deletedTask);
                    storage.save(taskList);
                    break;
                case 5:
                    Task todoTask = Parser.parseTodo(input);
                    taskList.addTask(todoTask);
                    ui.showNewTask(todoTask, taskList);
                    storage.save(taskList);
                    break;
                case 6:
                    Task deadlineTask = Parser.parseDeadline(input);
                    taskList.addTask(deadlineTask);
                    ui.showNewTask(deadlineTask, taskList);
                    storage.save(taskList);
                    break;
                case 7:
                    Task eventTask = Parser.parseEvent(input);
                    taskList.addTask(eventTask);
                    ui.showNewTask(eventTask, taskList);
                    storage.save(taskList);
                    break;
                case 8:
                    String keyword = Parser.parseFindTask(input);
                    TaskList foundTasks = taskList.findMatchingTasks(keyword);
                    ui.showFoundTasks(foundTasks);
                    break;
                default:
                    throw new ChappiUnrecognisedCommandException();
                }
            } catch (ChappiException e) {
                ui.showDukeException(e);
            } catch (IOException e) {
                ui.showIOException(e);
            }
        }
    }

    /**
     * The main logic for running Chappi chatbot.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Chappi("./data/chappiSave.txt").run();
    }
}
