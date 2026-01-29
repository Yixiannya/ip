package chappi.util;

/**
 * Contains all utility functions that do not belong to any other class.
 * Methods are to be used anywhere in Chappi's classes.
 */
public class Util {
    public Util() {}

    /**
     * Removes the specified prefix of a given String.
     *
     * @param input Input String to be trimmed.
     * @param prefix Prefix String to be removed from the input.
     * @return Trimmed input string.
     */
    public static String trimPrefix(String input, String prefix) {
        return input.substring(prefix.length());
    }
}
