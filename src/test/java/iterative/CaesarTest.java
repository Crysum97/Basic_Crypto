package iterative;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CaesarTest {
    @ParameterizedTest
    @CsvSource({"TEST, 2", "HALLO, -2", "WELT, 26", "SUPER, 30"})
    public void testCaesar(String input, int key) {
        System.out.printf("Input: %8s\nKey  :%8d", input, key);
        assertEquals(input, Caesar.decrypt(Caesar.encrypt(input, key), key));
    }
}
