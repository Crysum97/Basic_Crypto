package iterative;

import engine.CryptoEngine;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class VigereneTest {
    @ParameterizedTest
    @CsvSource({"HAllO, MauS", "WELT, HALLO", "TEST, TEST", "SUPER, AUTO"})
    public void testVigerene(String input, String key) {
        System.out.printf("Input: %s\n", input);
        CryptoEngine engine = new Vigerene();
        try {
            assertEquals(input.toUpperCase(), engine.decrypt(engine.encrypt(input, key), key));
        } catch (NotImplementedException e) {
            fail();
        }
    }
}