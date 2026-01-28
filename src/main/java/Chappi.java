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

    private static ArrayList<Task> taskList;
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
                    readList();
                    break;
                case 1:
                    ui.showGoodbye();
                    System.exit(0);
                    break;
                case 2:
                    markList(input);
                    storage.save(taskList);
                    break;
                case 3:
                    unmarkList(input);
                    storage.save(taskList);
                    break;
                case 4:
                    deleteTask(input);
                    storage.save(taskList);
                    break;
                case 5:
                    addToDo(input);
                    storage.save(taskList);
                    break;
                case 6:
                    addDeadline(input);
                    storage.save(taskList);
                    break;
                case 7:
                    addEvent(input);
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

    private static void addToDo(String input) throws DukeException {
        addToTaskList(Parser.parseTodo(input));
    }

    private static void addDeadline(String input) throws DukeException{
        addToTaskList(Parser.parseDeadline(input));
    }

    private static void addEvent(String input) throws DukeException {
        addToTaskList(Parser.parseEvent(input));
    }

    private static void addToTaskList(Task task) {
        taskList.add(task);
        ui.showNewTask(task, taskList);
    }

    private static void markList(String input) throws DukeException {
        Task task = Parser.parseTaskIndex(Parser.parseMarkTask(input), taskList);
        task.markDone();
        ui.showMarkedTask(task);
    }

    private static void unmarkList(String input) throws DukeException {
        Task task = Parser.parseTaskIndex(Parser.parseUnmarkTask(input), taskList);
        task.markNotDone();
        ui.showUnmarkedTask(task);
    }

    private static void deleteTask(String input) throws DukeException {
        Task task = Parser.parseTaskIndex(Parser.parseDeleteTask(input), taskList);
        taskList.remove(task);
        ui.showDeletedTask(task);
    }

    private static void readList() {
        if (taskList.size() <= 0) {
            ui.showEmptyTaskList();
        } else {
            ui.showTaskList(taskList);
        }
    }
}
