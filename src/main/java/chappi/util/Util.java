package chappi.util;

public class Util {
    public Util() {}

    public static String trimPrefix(String input, String prefix) {
        return input.substring(prefix.length());
    }
}
