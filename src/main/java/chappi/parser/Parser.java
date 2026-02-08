package chappi.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
import chappi.ui.Chappi;
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
     * Main parsing logic for commands.
     * Checks the given command to see if it starts with
     * any of the given keywords and returns the appropriate command type.
     * @param input String representation of the command to be deciphered.
     * @return Enum that represents the command to be performed.
     */
    public static Chappi.CommandType parse(String input) {
        if (input.equals("list")) {
            return Chappi.CommandType.LIST;
        } else if (input.equals("bye")) {
            return Chappi.CommandType.BYE;
        } else if (input.startsWith("mark")) {
            return Chappi.CommandType.MARK;
        } else if (input.startsWith("unmark")) {
            return Chappi.CommandType.UNMARK;
        } else if (input.startsWith("delete")) {
            return Chappi.CommandType.DELETE;
        } else if (input.startsWith("todo")) {
            return Chappi.CommandType.TODO;
        } else if (input.startsWith("deadline")) {
            return Chappi.CommandType.DEADLINE;
        } else if (input.startsWith("event")) {
            return Chappi.CommandType.EVENT;
        } else if (input.startsWith("find")) {
            return Chappi.CommandType.FIND;
        } else if (input.startsWith("update")) {
            return Chappi.CommandType.UPDATE;
        } else {
            return Chappi.CommandType.UNRECOGNISED;
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
        if (endDateStr.isBlank()) {
            throw new ChappiInvalidDeadlineException("Please enter a valid end date.");
        }
        if (!endDateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new ChappiInvalidDeadlineException("Please enter a valid end date in the YYYY-MM-DD format.");
        }
        try {
            LocalDate endDate = LocalDate.parse(endDateStr);
            return new Deadline(desc, endDate);
        } catch (DateTimeParseException e) {
            throw new ChappiInvalidDeadlineException(e.toString());
        }
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
            throw new ChappiInvalidEventException("Please enter the event's description, start date and end date.");
        }
        if (!input.startsWith("event ")) {
            throw new ChappiUnrecognisedCommandException();
        }

        String description = Util.trimPrefix(input, "todo ").strip();
        if (!description.contains("/from ")) {
            throw new ChappiInvalidEventException(
                    "Please enter a valid string for a start date using the '/from' keyword.");
        }
        if (!description.contains("/to ")) {
            throw new ChappiInvalidEventException(
                    "Please enter a valid string for an end date using the '/to' keyword.");
        }
        String[] strings = description.split("/from ");
        String[] dateArray = strings[1].split("/to ");
        String desc = strings[0].strip();
        String startDateStr = dateArray[0].strip();
        String endDateStr = dateArray[1].strip();
        if (desc.isBlank()) {
            throw new ChappiInvalidEventException("Please enter a valid description.");
        }
        if (startDateStr.isBlank()) {
            throw new ChappiInvalidEventException("Please enter a valid start date.");
        }
        if (endDateStr.isBlank()) {
            throw new ChappiInvalidEventException("Please enter a valid end date.");
        }
        if (!startDateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new ChappiInvalidDeadlineException("Please enter a valid end date in the YYYY-MM-DD format.");
        }
        if (!endDateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new ChappiInvalidDeadlineException("Please enter a valid end date in the YYYY-MM-DD format.");
        }
        try {
            LocalDate startDate = LocalDate.parse(startDateStr);
            LocalDate endDate = LocalDate.parse(endDateStr);
            return new Event(desc, startDate, endDate);
        } catch (DateTimeParseException e) {
            throw new ChappiInvalidEventException(e.toString());
        }
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
     *
     * @param input
     * @return
     * @throws ChappiException
     */
    public static String[] parseUpdateTask(String input) throws ChappiException {
        if (input.equals("update")) {
            throw new ChappiException("Please enter a number.");
        }
        if (!input.startsWith("update ")) {
            throw new ChappiUnrecognisedCommandException();
        }

        String description = Util.trimPrefix(input, "update ").strip();
        String[] strings = description.split("/");
        String desc = "";
        String startDateStr = "";
        String endDateStr = "";
        String index = "-1";
        for (String s : strings) {
            if (s.startsWith("desc ")) {
                desc = Util.trimPrefix(s, "desc ").strip();
            } else {
                index = s.strip();
            }
        }
        boolean isAllBlank = desc.isBlank() && startDateStr.isBlank() && endDateStr.isBlank();
        if (isAllBlank) {
            throw new ChappiException("Please enter some information to update with!");
        }
        String[] result = {index, desc};
        return result;
    }
}
