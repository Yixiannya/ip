public class Chappi {
    public static void main(String[] args) {
        // Messages
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String newline = "____________________________________________________________\n";
        String greeting = " Hello! I'm Chappi!\n" +
                " What can I do for you?\n";
        String goodbye = " Bye. Hope to see you again soon!\n";

        System.out.println("Hello from\n" + logo);
        System.out.println(newline + greeting + newline + goodbye + newline);
    }
}
