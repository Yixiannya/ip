// Imports
import java.util.Scanner;
import java.util.ArrayList;

import dukeExceptions.DukeException;
import dukeExceptions.DukeInvalidEventException;
import dukeExceptions.DukeInvalidDeadlineException;
import dukeExceptions.DukeInvalidTodoException;
import dukeExceptions.DukeUnrecognisedCommandException;

public class Chappi {
    // Common use strings
    private static String seperator = "     ____________________________________________________________\n";
    private static Scanner scanner;
    private static ArrayList<Task> taskList;

    public static void main(String[] args) {
        // Messages
        String greeting = "      Hello! I'm Chappi!\n" +
                "      What can I do for you?\n";

        // Initialisation
        taskList = new ArrayList<>();
        System.out.println(seperator + greeting + seperator);

        scanner = new Scanner(System.in);

        while (true) {
            try {
                String input = scanner.nextLine();

                if (input.equals("list")) {
                    readList();
                } else if (input.equals("bye")) {
                    sayBye();
                    System.exit(0);
                } else if (input.startsWith("mark")) {
                    markList(input);
                } else if (input.startsWith("unmark")) {
                    unmarkList(input);
                } else if (input.startsWith("todo")) {
                    addToDo(input);
                } else if (input.startsWith("deadline")) {
                    addDeadline(input);
                } else if (input.startsWith("event")) {
                    addEvent(input);
                } else {
                    throw new DukeUnrecognisedCommandException();
                }
            } catch (DukeException e) {
                System.out.println(seperator + e + seperator);
            }
        }
    }

    private static String trimPrefix(String input, String prefix) {
        return input.substring(prefix.length());
    }

    private static void addToDo(String input) throws DukeException {
        if (input.equals("todo")) {
            throw new DukeInvalidTodoException("Please enter a task description.");
        } else if (input.startsWith("todo ")) {
            String description = trimPrefix(input, "todo ").strip();
            if (description.isBlank()) {
                throw new DukeInvalidTodoException("Please enter a task description.");
            }
            ToDo todo = new ToDo(description);
            addToTaskList(todo);
        } else {
            throw new DukeUnrecognisedCommandException();
        }
    }

    private static void addDeadline(String input) throws DukeException{
        if (input.equals("deadline")) {
            throw new DukeInvalidDeadlineException("Please enter the event's description and start date.");
        } else if (input.startsWith("deadline ")) {
            String description = trimPrefix(input, "deadline ").strip();
            if (!description.contains("/by ")) {
                throw new DukeInvalidDeadlineException("Please enter a due date with the '/by' keyword.");
            }
            String[] strings = description.split("/by ");
            String desc = strings[0].strip();
            String endDate = strings[1].strip();
            if (desc.isBlank()) {
                throw new DukeInvalidDeadlineException("Please enter a valid description.");
            }
            if (endDate.isBlank()) {
                throw new DukeInvalidDeadlineException("Please enter a valid end date.");
            }
            Deadline deadline = new Deadline(desc, endDate);
            addToTaskList(deadline);

        } else {
            throw new DukeUnrecognisedCommandException();
        }
    }

    private static void addEvent(String input) throws DukeException {
        if (input.equals("event")) {
            throw new DukeInvalidEventException("Please enter the event's description, start date and end date.");
        } else if (input.startsWith("event ")) {
            String description = trimPrefix(input, "todo ").strip();
            if (!description.contains("/from ")) {
                throw new DukeInvalidEventException("Please enter a valid string for a start date using the '/from' keyword.");
            }
            if (!description.contains("/to ")) {
                throw new DukeInvalidEventException("Please enter a valid string for an end date using the '/to' keyword.");
            }
            String[] strings = description.split("/from ");
            String[] dateArray = strings[1].split("/to ");
            String desc = strings[0].strip();
            String startDate = dateArray[0].strip();
            String endDate = dateArray[1].strip();
            if (desc.isBlank()) {
                throw new DukeInvalidEventException("Please enter a valid description.");
            }
            if (startDate.isBlank()) {
                throw new DukeInvalidEventException("Please enter a valid start date.");
            }
            if (endDate.isBlank()) {
                throw new DukeInvalidEventException("Please enter a valid end date.");
            }
            Event event = new Event(desc, startDate, endDate);
            addToTaskList(event);
        } else {
            throw new DukeUnrecognisedCommandException();
        }
    }

    private static void addToTaskList(Task task) {
        taskList.add(task);
        String msg = "      Sure, added:\n      %s\n";
        String listLenMsg = String.format("      There's %d tasks in your list now.\n", taskList.size());
        System.out.println(seperator
                + String.format(msg, task)
                + listLenMsg
                + seperator);
    }

    private static Task getTask(String input) throws DukeException {
        int index = Integer.parseInt(input);
        int i = index - 1;
        if (taskList.isEmpty()) {
            throw new DukeException("      The list is empty!");
        }
        if (i < 0) {
            throw new DukeException("      Please input a number greater than 0.");
        }
        if (i >= taskList.size()) {
            String e = String.format("      The given number is larger than the size of the list.\n"
                    + "      Please give a number smaller than %d.", taskList.size());
            throw new DukeException(e);
        }
        return taskList.get(i);
    }

    private static void markList(String input) throws DukeException {
        if (input.equals("mark")) {
            throw new DukeException("      Please enter a number.");
        } else if (input.startsWith("mark ")) {
            try {
                Task task = getTask(trimPrefix(input, "mark").strip());
                task.markDone();
                String msg = "      Alright, marked this task as done:\n        %s\n";
                System.out.println(seperator + String.format(msg, task) + seperator);
            } catch (NumberFormatException e) {
                throw new DukeException("      That is not a valid number.");
            }
        } else {
            throw new DukeUnrecognisedCommandException();
        }
    }

    private static void unmarkList(String input) throws DukeException {
        if (input.equals("unmark")) {
            throw new DukeException("      Please enter a number.");
        } else if (input.startsWith("unmark ")) {
            try {
                Task task = getTask(trimPrefix(input, "unmark").strip());
                task.markNotDone();
                String msg = "      Alright, marked this task as not done yet:\n        %s\n";
                System.out.println(seperator + String.format(msg, task) + seperator);
            } catch (NumberFormatException e) {
                throw new DukeException("      That is not a valid number.");
            }
        } else {
            throw new DukeUnrecognisedCommandException();
        }
    }

    private static void readList() {
        String result = "";

        if (taskList.size() <= 0) {
            result = result.concat("      The list is empty!\n");
        } else {
            result = result.concat("      Here's your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                int index = i + 1;
                String msg = "      %d.%s\n";
                result = result.concat(String.format(msg, index, taskList.get(i)));
            }
        }
        System.out.println(seperator + result + seperator);
    }

    private static void sayBye() {
        String msg = "      Bye. Hope to see you again soon! ☆*:.｡. o(≧▽≦)o .｡.:*☆\n";
        System.out.println(seperator + msg + seperator);
    }
}
