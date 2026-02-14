package chappi.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import chappi.command.AddDeadlineTaskCommand;
import chappi.command.AddEventTaskCommand;
import chappi.command.AddTodoTaskCommand;
import chappi.command.Command;
import chappi.command.DeleteTaskCommand;
import chappi.command.ExitCommand;
import chappi.command.FindTaskCommand;
import chappi.command.MarkTaskCommand;
import chappi.command.ShowTaskListCommand;
import chappi.command.UnmarkTaskCommand;
import chappi.command.UpdateTaskCommand;
import chappi.exceptions.ChappiException;
import chappi.exceptions.ChappiInvalidDeadlineException;
import chappi.exceptions.ChappiInvalidEventException;
import chappi.exceptions.ChappiInvalidTodoException;
import chappi.exceptions.ChappiUnrecognisedCommandException;
import chappi.task.Deadline;
import chappi.task.Event;
import chappi.task.Task;
import chappi.task.ToDo;
import chappi.tasklist.TaskList;
import chappi.util.Util;



/**
 * Represents the logic in extracting the required
 * information from the given String input commands.
 * Performs various tasks depending on what is parsed.
 */
public class Parser {
    /**
     * Basic constructor.
     * Parser has no variables needed.
     */
    public Parser() {}

    /**
     * Checks the given command to see if it starts with
     * any of the given keywords and returns the appropriate command type.
     * @param input String representation of the command to be deciphered.
     * @return Enum that represents the command to be performed.
     */
    public static Command parseCommand(String input) throws ChappiUnrecognisedCommandException {
        if (input.equals("list")) {
            return new ShowTaskListCommand(input);
        } else if (input.equals("bye")) {
            return new ExitCommand(input);
        } else if (input.startsWith("mark")) {
            return new MarkTaskCommand(input);
        } else if (input.startsWith("unmark")) {
            return new UnmarkTaskCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteTaskCommand(input);
        } else if (input.startsWith("todo")) {
            return new AddTodoTaskCommand(input);
        } else if (input.startsWith("deadline")) {
            return new AddDeadlineTaskCommand(input);
        } else if (input.startsWith("event")) {
            return new AddEventTaskCommand(input);
        } else if (input.startsWith("find")) {
            return new FindTaskCommand(input);
        } else if (input.startsWith("update")) {
            return new UpdateTaskCommand(input);
        } else {
            throw new ChappiUnrecognisedCommandException();
        }
    }

    /**
     * Takes a String representation of a command
     * to create and return a ToDo task
     * with the given description.
     * @param input String representation of todo task command.
     * @return ToDo task with the given description.
     * @throws ChappiException if input is in the incorrect format.
     */
    public static ToDo parseTodo(String input) throws ChappiException {
        if (input.equals("todo")) {
            throw new ChappiInvalidTodoException("Please enter a task description.");
        }
        if (!input.startsWith("todo ")) {
            throw new ChappiUnrecognisedCommandException();
        }

        String description = Util.trimPrefix(input, "todo ").strip();
        if (description.isBlank()) {
            throw new ChappiInvalidTodoException("Please enter a task description.");
        }
        return new ToDo(description);
    }

    /**
     * Takes a String representation of a command
     * to create and return a Deadline task
     * with the given description and end date.
     * @param input String representation of deadline task command.
     * @return Deadline task with the given description and end date.
     * @throws ChappiException if input is in the incorrect format.
     */
    public static Deadline parseDeadline(String input) throws ChappiException {
        if (input.equals("deadline")) {
            throw new ChappiInvalidDeadlineException("Please enter the event's description and start date.");
        }
        if (!input.startsWith("deadline ")) {
            throw new ChappiUnrecognisedCommandException();
        }

        String description = Util.trimPrefix(input, "deadline ").strip();
        if (!description.contains("/by ")) {
            throw new ChappiInvalidDeadlineException("Please enter a due date with the '/by' keyword.");
        }
        String[] strings = description.split("/by ");
        String desc = strings[0].strip();
        String endDateStr = strings[1].strip();

        if (desc.isBlank()) {
            throw new ChappiInvalidDeadlineException("Please enter a valid description.");
        }

        LocalDate endDate = parseDate(endDateStr);

        if (endDate == null) {
            throw new ChappiInvalidEventException("Please enter a date! (Not a blank)");
        }
        return new Deadline(desc, endDate);
    }

    /**
     * Takes a String representation of a command
     * to create and return an Event task
     * with the given description and start and end date.
     * @param input String representation of Event task command.
     * @return Deadline task with the given description and start and end date.
     * @throws ChappiException if input is in the incorrect format.
     */
    public static Event parseEvent(String input) throws ChappiException {
        if (input.equals("event")) {
            throw new ChappiInvalidEventException(
                    "Please enter the event's description, start date and end date.");
        }
        if (!input.startsWith("event ")) {
            throw new ChappiUnrecognisedCommandException();
        }
        String body = Util.trimPrefix(input, "event ").strip();

        if (!body.contains("/from ")) {
            throw new ChappiInvalidEventException(
                    "Please enter a valid string for a start date using the '/from' keyword.");
        }
        if (!body.contains("/to ")) {
            throw new ChappiInvalidEventException(
                    "Please enter a valid string for an end date using the '/to' keyword.");
        }
        String[] parts = body.split("/from ");
        String desc = parts[0].strip();
        String[] dateParts = parts[1].split("/to ");
        String startDateStr = dateParts[0].strip();
        String endDateStr = dateParts[1].strip();

        if (desc.isBlank()) {
            throw new ChappiInvalidEventException("Please enter a valid description.");
        }
        LocalDate startDate = parseDate(startDateStr);
        LocalDate endDate = parseDate(endDateStr);

        if (startDate == null || endDate == null) {
            throw new ChappiInvalidEventException("Please enter a date! (Not a blank)");
        }

        return new Event(desc, startDate, endDate);
    }

    /**
     * Takes given String representation of task.
     * This input String must be formatted in the
     * format of the saved text file.
     * @param line String representing a single line in the save file.
     * @return Task that the line represents.
     */
    public static Task parseSavedTask(String line) {
        String[] splitLine = line.split(" \\| ");
        String taskType = splitLine[0];
        boolean isDone = splitLine[1].equals("1");
        String description = splitLine[2];

        switch (taskType) {
        case "T":
            return new ToDo(description, isDone);
        case "D":
            LocalDate deadlineEndDate = LocalDate.parse(Util.trimPrefix(splitLine[3], "End: "));
            return new Deadline(description, isDone, deadlineEndDate);
        case "E":
            LocalDate eventStartDate = LocalDate.parse(Util.trimPrefix(splitLine[3], "Start: "));
            LocalDate eventEndDate = LocalDate.parse(Util.trimPrefix(splitLine[4], "End: "));
            return new Event(description, isDone, eventStartDate, eventEndDate);
        default:
            throw new IllegalArgumentException("Unknown task type");
        }
    }

    /**
     * Extracts the task located in the task list
     * that has the specified integer index.
     * @param input String command that has an integer indicating index of task.
     * @param taskList Task list that contains the tasks of Chappi.
     * @return Task that is specified by the index.
     * @throws ChappiException if index in the input does not match what is available in the task list.
     */
    public static Task parseTaskIndex(String input, TaskList taskList) throws ChappiException {
        try {
            int index = Integer.parseInt(input);
            int i = index - 1;
            if (taskList.isEmpty()) {
                throw new ChappiException("The list is empty!");
            }
            if (i < 0) {
                throw new ChappiException("The given index is too small!");
            }
            if (i >= taskList.size()) {
                throw new ChappiException("The given index is too large!");
            }
            return taskList.getTask(i);
        } catch (NumberFormatException e) {
            throw new ChappiException("That is not a valid number.");
        }
    }

    /**
     * Takes a String command telling Chappi to mark a task.
     * Then extracts the index of the task Chappi should mark.
     * This index is represented as a string.
     * @param input String command that commands Chappi to mark a task.
     * @return String representing the index of the task to mark.
     * @throws ChappiException if command is not formatted correctly.
     */
    public static String parseMarkTask(String input) throws ChappiException {
        if (input.equals("mark")) {
            throw new ChappiException("Please enter a number.");
        }
        if (!input.startsWith("mark ")) {
            throw new ChappiUnrecognisedCommandException();
        }

        return Util.trimPrefix(input, "mark").strip();
    }

    /**
     * Takes a String command telling Chappi to unmark a task.
     * Then extracts the index of the task Chappi should unmark.
     * This index is represented as a string.
     * @param input String command that commands Chappi to unmark a task.
     * @return String representing the index of the task to unmark.
     * @throws ChappiException if command is not formatted correctly.
     */
    public static String parseUnmarkTask(String input) throws ChappiException {
        if (input.equals("unmark")) {
            throw new ChappiException("Please enter a number.");
        }
        if (!input.startsWith("unmark ")) {
            throw new ChappiUnrecognisedCommandException();
        }

        return Util.trimPrefix(input, "unmark").strip();
    }

    /**
     * Takes a String command telling Chappi to delete a task.
     * Then extracts the index of the task Chappi should delete.
     * This index is represented as a string.
     * @param input String command that commands Chappi to delete a task.
     * @return String representing the index of the task to delete.
     * @throws ChappiException if command is not formatted correctly.
     */
    public static String parseDeleteTask(String input) throws ChappiException {
        if (input.equals("delete")) {
            throw new ChappiException("Please enter a number.");
        }
        if (!input.startsWith("delete ")) {
            throw new ChappiUnrecognisedCommandException();
        }

        return Util.trimPrefix(input, "delete").strip();
    }

    /**
     * Parses find command represented as a String input
     * to obtain a keyword that is returned as a String.
     * @param input String representation of a find command.
     * @return Keyword to be searched for as a String.
     */
    public static String parseFindTask(String input) throws ChappiException {
        if (input.equals("find")) {
            throw new ChappiException("Please enter a keyword.");
        }
        if (!input.startsWith("find ")) {
            throw new ChappiUnrecognisedCommandException();
        }

        String keyword = Util.trimPrefix(input, "find").strip();
        if (keyword.isBlank()) {
            throw new ChappiException("Please enter a keyword.");
        }
        return keyword;
    }

    /**
     * Takes in a command beginning with "update" and interprets what information
     * is needed to be returned.
     * Also recognises if the information provided in the input is insufficient
     * and if the input has the wrong formatting.
     * Used ChatGPT to find ways to shorten the method.
     * ChatGPT gave the follow method below, as well as the private parseDate method.
     * @param input String input representing the user input command to update a task
     * @return An object array containing the needed information, in the order of
     *      index, description, end date, start date.
     * @throws ChappiException when the command is given in an incorrect format, or if the dates provided are in the
     *      wrong format.
     */
    public static Object[] parseUpdateTask(String input) throws ChappiException {
        if (input.equals("update")) {
            throw new ChappiException("Please enter a number.");
        }
        if (!input.startsWith("update ")) {
            throw new ChappiUnrecognisedCommandException();
        }

        String[] parts = Util.trimPrefix(input, "update ").strip().split("/");
        String desc = "";
        String index = "-1";
        LocalDate startDate = null;
        LocalDate endDate = null;
        for (String p : parts) {
            p = p.strip();
            if (p.startsWith("desc ")) {
                desc = Util.trimPrefix(p, "desc ").strip();
            } else if (p.startsWith("from ")) {
                startDate = parseDate(Util.trimPrefix(p, "from ").strip());
            } else if (p.startsWith("to ")) {
                endDate = parseDate(Util.trimPrefix(p, "to ").strip());
            } else {
                index = p;
            }
        }

        if (desc.isBlank() && startDate == null && endDate == null) {
            throw new ChappiException("Please enter some information to update with!");
        }

        return new Object[] { index, desc, endDate, startDate };
    }

    private static LocalDate parseDate(String dateStr) throws ChappiException {
        if (dateStr.isBlank()) {
            return null;
        }
        if (!dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new ChappiException("Please enter a valid date in the YYYY-MM-DD format.");
        }
        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new ChappiException(e.getMessage());
        }
    }
}
