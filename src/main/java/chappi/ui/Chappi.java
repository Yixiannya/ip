package chappi.ui;

// IO imports
import java.io.IOException;



// Exception imports
import chappi.chappiExceptions.ChappiException;
import chappi.chappiExceptions.ChappiUnrecognisedCommandException;
import chappi.parser.Parser;
import chappi.storage.Storage;
import chappi.task.Task;
import chappi.tasklist.TaskList;

public class Chappi {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Chappi(String filePath) {
        // Initialisation
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = storage.load();
        } catch (ChappiException e) {
            ui.showDukeException(e);
            taskList = new TaskList();
        }
    }

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

    public static void main(String[] args) throws IOException {
        new Chappi("./data/chappiSave.txt").run();
    }
}
