import java.util.Scanner;

public class Chappi {
    // Common use strings
    private static String seperator = "     ____________________________________________________________\n";
    private static Scanner scanner;
    private static String[] stringList;
    private static int listCount;

    public static void main(String[] args) {
        // Messages
        String greeting = "      Hello! I'm Chappi!\n" +
                "      What can I do for you?\n";

        // Initialisation
        stringList = new String[100];
        listCount = 0;
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
                    addToList(input);
                    break;
            }
        }
    }

    private static void list() {
        String result = seperator;

        for (int i = 0; i < stringList.length; i++) {
            if (stringList[i] == null) {
                if (i <= 0) {
                    result = result.concat("       The list is empty!\n");
                }
                break;
            }
            int index = i + 1;
            String msg = "       %d. %s\n";
            result = result.concat(String.format(msg, index, stringList[i]));
        }

        System.out.println(result + seperator);
    }

    private static void blah() {
        String msg = "      blah\n";
        System.out.println(seperator + msg + seperator);
    }

    private static void bye() {
        String msg = "      Bye. Hope to see you again soon! ☆*:.｡. o(≧▽≦)o .｡.:*☆\n";
        System.out.println(seperator + msg + seperator);
    }

    private static void addToList(String str) {
        stringList[listCount] = str;
        listCount++;
        String msg = "      added: ";
        System.out.println(seperator + msg + str + "\n" + seperator);
    }
}
