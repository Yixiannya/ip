import java.util.Scanner;

public class Chappi {
    // Common use strings
    private static String seperator = "     ____________________________________________________________\n";
    private static Scanner scanner;

    public static void main(String[] args) {
        // Messages
        String greeting = "      Hello! I'm Chappi!\n" +
                "      What can I do for you?\n";

        System.out.println(seperator + greeting + seperator);

        scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            switch (input) {
                case "list":
                    list();
                    break;
                case "blah":
                    blah();
                    break;
                case "bye":
                    bye();
                    System.exit(0);
                    break;
                default:
                    unrecognised();
                    break;
            }
        }
    }

    private static void list() {
        String msg = "      list\n";
        System.out.println(seperator + msg + seperator);
    }

    private static void blah() {
        String msg = "      blah\n";
        System.out.println(seperator + msg + seperator);
    }

    private static void bye() {
        String msg = "      Bye. Hope to see you again soon! ☆*:.｡. o(≧▽≦)o .｡.:*☆\n";
        System.out.println(seperator + msg + seperator);
    }

    private static void unrecognised() {
        String msg = "      I don't understand what you said :(\n";
        System.out.println(seperator + msg + seperator);
    }
}
