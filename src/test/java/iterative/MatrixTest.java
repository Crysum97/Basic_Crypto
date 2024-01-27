package iterative;

import engine.CryptoEngine;
import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class MatrixTest {
    @ParameterizedTest
    @CsvSource({"Hallo", "Welt", "Test", "SUPER"})
    public void testMatrix(String input) {
        System.out.printf("Input: %s\n", input);
        CryptoEngine engine = new Matrix();
        try {
            assertTrue(engine.decrypt(engine.encrypt(input)).startsWith(input.toUpperCase()));
        } catch (ExecutionControl.NotImplementedException e) {
            fail();
        }
    }
}