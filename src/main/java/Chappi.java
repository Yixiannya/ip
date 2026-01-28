// Imports
import java.util.Scanner;
import java.util.ArrayList;

// IO Imports
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

// Exception imports
import dukeExceptions.DukeException;
import dukeExceptions.DukeInvalidEventException;
import dukeExceptions.DukeInvalidDeadlineException;
import dukeExceptions.DukeInvalidTodoException;
import dukeExceptions.DukeUnrecognisedCommandException;

// Date imports
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Chappi {
    // Common use strings
    private static final String seperator = "     ____________________________________________________________\n";
    private static final String filePath = "./data/chappiSave.txt";

    private static TaskList taskList;
    private static Ui ui;

    public static void main(String[] args) throws IOException {
        // Initialisation
        ui = new Ui();
        Storage storage = new Storage(filePath);
        taskList = storage.load();

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
                    throw new DukeUnrecognisedCommandException();
                }
            } catch (DukeException e) {
                ui.showDukeException(e);
            } catch (IOException e) {
                ui.showIOException(e);
            }
        }
    }
}
