package util;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tools {

    /**
     * Breaks a given {@link String} into a multi-line String by inserting line breaks
     * @param input {@link String} to break
     * @return multi-line {@link String}
     */
    public static String breakIntoLines(String input) {
        return IntStream.range(0, input.length())
                .mapToObj(index -> index != 0 && index % 30 == 0
                                ? "" + input.charAt(index) + "\n"
                                : "" + input.charAt(index)).collect(Collectors.joining());
    }

    /**
     * Converts a given {@link String} to uppercase and removes special and numeric characters
     * @param input {@link String} to transform
     * @return transformed {@link String}
     */
    public static String transformString(String input) {
        return input.toUpperCase().replaceAll("[^A-Z]", "");
    }
}
