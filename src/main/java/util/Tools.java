package util;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tools {

    public static String breakIntoLines(String input) {
        return IntStream.range(0, input.length())
                .mapToObj(index -> index != 0 && index % 30 == 0
                                ? "" + input.charAt(index) + "\n"
                                : "" + input.charAt(index)).collect(Collectors.joining());
    }

    public static String transformString(String input) {
        return input.toUpperCase().replaceAll("[^A-Z]", "");
    }
}
