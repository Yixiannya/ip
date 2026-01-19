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
                int index = Integer.parseInt(input.substring(5));
                markList(index);
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7));
                unmarkList(index);
            } else {
                addToList(input);
            }
        }
    }


    private static void addToList(String str) {
        Task task = new Task(str);
        taskList.add(task);
        String msg = "      added: %s\n";
        System.out.println(seperator + String.format(msg, task.getDescription()) + seperator);
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
