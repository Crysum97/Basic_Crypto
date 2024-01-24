package iterative;

import engine.CaesarEngine;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CaesarTest {
    @ParameterizedTest
    @CsvSource({"TEST, 2", "HALLO, -2", "WELT, 26", "SUPER, 30"})
    public void testCaesar(String input, int key) {
        System.out.printf("Input: %8s\nKey  :%8d", input, key);
        CaesarEngine engine = new Caesar();
        assertEquals(input, engine.decrypt(engine.encrypt(input, key), key));
    }
}
