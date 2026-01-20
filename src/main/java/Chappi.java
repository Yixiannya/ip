import java.util.Scanner;
import java.util.ArrayList;

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
            String input = scanner.nextLine();

            if (input.equals("list")) {
                readList();
            } else if (input.equals("blah")) {
                sayBlah();
            } else if (input.equals("bye")) {
                sayBye();
                System.exit(0);
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(trimPrefix(input, "mark "));
                markList(index);
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(trimPrefix(input, "unmark "));
                unmarkList(index);
            } else if (input.startsWith("todo ")) {
                addToDo(input);
            } else if (input.startsWith("deadline ")) {
                addDeadline(input);
            } else if (input.startsWith("event ")) {
                addEvent(input);
            } else {
                addTask(input);
            }
        }
    }

    private static String trimPrefix(String input, String prefix) {
        return input.substring(prefix.length());
    }

    private static void addToDo(String input) {
        String description = trimPrefix(input, "todo ");
        ToDo todo = new ToDo(description);
        addToTaskList(todo);
    }

    private static void addDeadline(String input) {
        String description = trimPrefix(input, "deadline ");
        if (description.contains("/by ")) {
            String[] strings = description.split("/by ");
            Deadline deadline = new Deadline(strings[0], strings[1]);
            addToTaskList(deadline);
        } else {
            System.out.println(seperator
                    + "     Invalid format.\n"
                    + "     Please enter a due date with the '/by' keyword.\n"
                    + seperator);
        }
    }

    private static void addEvent(String input) {
        String description = trimPrefix(input, "todo ");
        if (description.contains("/from ") && description.contains("/to ")) {
            String[] strings = description.split("/from ");
            String[] dateArray = strings[1].split("/to ");
            Event event = new Event(strings[0], dateArray[0], dateArray[1]);
            addToTaskList(event);
        } else {
            System.out.println(seperator
                    + "     Invalid format.\n"
                    + "     Please enter a start date with the '/from' keyword and an end date with the '/to' keyword.\n"
                    + seperator);
        }
    }

    private static void addTask(String str) {
        Task task = new Task(str);
        addToTaskList(task);
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

    private static void markList(int index) {
        int i = index - 1;
        Task task = taskList.get(i);
        task.markDone();
        String msg = "      Alright, marked this task as done:\n        %s\n";
        System.out.println(seperator + String.format(msg, task) + seperator);
    }

    private static void unmarkList(int index) {
        int i = index - 1;
        Task task = taskList.get(i);
        task.markNotDone();
        String msg = "      Alright, marked this task as not done yet:\n        %s\n";
        System.out.println(seperator + String.format(msg, task) + seperator);
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

    private static void sayBlah() {
        String msg = "      blah\n";
        System.out.println(seperator + msg + seperator);
    }

    private static void sayBye() {
        String msg = "      Bye. Hope to see you again soon! ☆*:.｡. o(≧▽≦)o .｡.:*☆\n";
        System.out.println(seperator + msg + seperator);
    }
}
