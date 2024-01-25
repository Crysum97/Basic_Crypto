package iterative;

import engine.CryptoEngine;
import jdk.jshell.spi.ExecutionControl;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class VigereneTest {
    @ParameterizedTest
    @CsvSource({"HALLO", "WELT", "TEST", "SUPER"})
    public void testVigerene(String input) {
        System.out.printf("Input: %s\n", input);
        CryptoEngine engine = new Vigerene();
        try {
            assertEquals(input, engine.decrypt(engine.encrypt(input, "TEST"), "TEST"));
        } catch (NotImplementedException e) {
            fail();
        }
    }
}