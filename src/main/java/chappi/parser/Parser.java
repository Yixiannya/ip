package chappi.parser;

import chappi.task.Deadline;
import chappi.task.Event;
import chappi.task.Task;
import chappi.task.ToDo;
import chappi.chappiExceptions.*;
import chappi.tasklist.TaskList;
import chappi.util.Util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public Parser() {}

    public static int parse(String input) throws ChappiException {
        if (input.equals("list")) {
            return 0;
        } else if (input.equals("bye")) {
            return 1;
        } else if (input.startsWith("mark")) {
            return 2;
        } else if (input.startsWith("unmark")) {
            return 3;
        } else if (input.startsWith("delete")) {
            return 4;
        } else if (input.startsWith("todo")) {
            return 5;
        } else if (input.startsWith("deadline")) {
            return 6;
        } else if (input.startsWith("event")) {
            return 7;
        } else {
            return -1;
        }
    }

    public static ToDo parseTodo(String input) throws ChappiException {
        if (input.equals("todo")) {
            throw new ChappiInvalidTodoException("Please enter a task description.");
        } else if (input.startsWith("todo ")) {
            String description = Util.trimPrefix(input, "todo ").strip();
            if (description.isBlank()) {
                throw new ChappiInvalidTodoException("Please enter a task description.");
            }
            return new ToDo(description);
        } else {
            throw new ChappiUnrecognisedCommandException();
        }
    }

    public static Deadline parseDeadline(String input) throws ChappiException {
        if (input.equals("deadline")) {
            throw new ChappiInvalidDeadlineException("Please enter the event's description and start date.");
        } else if (input.startsWith("deadline ")) {
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
        } else {
            throw new ChappiUnrecognisedCommandException();
        }
    }

    public static Event parseEvent(String input) throws ChappiException {
        if (input.equals("event")) {
            throw new ChappiInvalidEventException("Please enter the event's description, start date and end date.");
        } else if (input.startsWith("event ")) {
            String description = Util.trimPrefix(input, "todo ").strip();
            if (!description.contains("/from ")) {
                throw new ChappiInvalidEventException("Please enter a valid string for a start date using the '/from' keyword.");
            }
            if (!description.contains("/to ")) {
                throw new ChappiInvalidEventException("Please enter a valid string for an end date using the '/to' keyword.");
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
            } catch (DateTimeParseException e){
                throw new ChappiInvalidEventException(e.toString());
            }
        } else {
            throw new ChappiUnrecognisedCommandException();
        }
    }

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

    public static String parseMarkTask(String input) throws ChappiException {
        if (input.equals("mark")) {
            throw new ChappiException("Please enter a number.");
        } else if (input.startsWith("mark ")) {
            return Util.trimPrefix(input, "mark").strip();
        } else {
            throw new ChappiUnrecognisedCommandException();
        }
    }

    public static String parseUnmarkTask(String input) throws ChappiException {
        if (input.equals("unmark")) {
            throw new ChappiException("Please enter a number.");
        } else if (input.startsWith("unmark ")) {
            return Util.trimPrefix(input, "unmark").strip();
        } else {
            throw new ChappiUnrecognisedCommandException();
        }
    }

    public static String parseDeleteTask(String input) throws ChappiException {
        if (input.equals("delete")) {
            throw new ChappiException("Please enter a number.");
        } else if (input.startsWith("delete ")) {
            return Util.trimPrefix(input, "delete").strip();
        } else {
            throw new ChappiUnrecognisedCommandException();
        }
    }
}
